package inno.tretyakov.workers;

import inno.tretyakov.dto.AccountDto;
import inno.tretyakov.dto.RespAccountDto;
import inno.tretyakov.entity.Account;
import inno.tretyakov.entity.AccountPool;
import inno.tretyakov.entity.TppProductRegister;
import inno.tretyakov.entity.TppRefProductRegisterType;
import inno.tretyakov.enums.ProductRegisterStatus;
import inno.tretyakov.exceptions.InvalidRequest;
import inno.tretyakov.exceptions.NoData;
import inno.tretyakov.interfaces.IAccountProcessable;
import inno.tretyakov.repository.AccountPoolRepository;
import inno.tretyakov.repository.AccountRepository;
import inno.tretyakov.repository.TppProductRegisterRepository;
import inno.tretyakov.repository.TppRefProductRegisterTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IAccountWorker implements IAccountProcessable {
    @Autowired
    TppProductRegisterRepository tppProductRegisterRepository;
    @Autowired
    TppRefProductRegisterTypeRepository tppRefProductRegisterTypeRepository;
    @Autowired
    AccountPoolRepository accountPoolRepository;
    @Autowired
    AccountRepository accountRepository;

    @Override
    public void process(AccountDto accountDto, RespAccountDto responseAccountDto) {

        List<TppProductRegister> tppProductRegisters = tppProductRegisterRepository.findReg(accountDto.getInstanceId(), accountDto.getRegistryTypeCode());
        if (!tppProductRegisters.isEmpty()) {
            throw new InvalidRequest("Тип регистра с кодом "+ accountDto.getRegistryTypeCode()+" уже существует!");
        }

        List<TppRefProductRegisterType> tppRefProductRegisterTypes = tppRefProductRegisterTypeRepository.findFirstByValue(accountDto.getRegistryTypeCode());
        if (tppRefProductRegisterTypes.isEmpty()) {
            throw new NoData("Продукт с кодом  " + accountDto.getRegistryTypeCode() + " не найден!");
        }

        System.out.println(accountDto);
        List<AccountPool> accountPools = accountPoolRepository.getPool(accountDto.getBranchCode(), accountDto.getCurrencyCode(), accountDto.getMdmCode(), accountDto.getPriorityCode(), accountDto.getRegistryTypeCode());
        if (accountPools.isEmpty()) {
            throw new NoData("Не найден пул!");
        }

        List<Account> accList = accountRepository.findAccount(accountPools.get(0).getId());
        if (accList.isEmpty()) {
            throw new NoData("Нет подходящего счета в пуле! " + accountPools.get(0).getId());
        }

        Account account = accList.get(0);
        account.setBussy(true);
        accountRepository.save(account);

        TppProductRegister tppProductRegister = new TppProductRegister();
        tppProductRegister.setProductId(accountDto.getInstanceId());
        List<TppRefProductRegisterType> lt = tppRefProductRegisterTypeRepository.findFirstByValue(accountDto.getRegistryTypeCode());
        tppProductRegister.setType(lt.get(0));
        tppProductRegister.setAccount(account.getId());
        tppProductRegister.setCurrency_code(accountDto.getCurrencyCode());
        tppProductRegister.setState(String.valueOf(ProductRegisterStatus.OPEN));
        tppProductRegister.setAccount_number(account.getAccount_number());
        tppProductRegisterRepository.save(tppProductRegister);
        responseAccountDto.setAccountId(tppProductRegister.getId().toString());

    }
}
