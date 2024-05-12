package inno.tretyakov.workers;

import inno.tretyakov.dto.AccountDto;
import inno.tretyakov.dto.InstanceDto;
import inno.tretyakov.dto.RespAccountIntDto;
import inno.tretyakov.dto.RespInstanceDto;
import inno.tretyakov.entity.Agreement;
import inno.tretyakov.entity.TppProduct;
import inno.tretyakov.entity.TppRefProductClass;
import inno.tretyakov.enums.AccountTypes;
import inno.tretyakov.exceptions.InvalidRequest;
import inno.tretyakov.exceptions.NoData;
import inno.tretyakov.interfaces.InstanceProcessable;
import inno.tretyakov.repository.AgreementRepository;
import inno.tretyakov.repository.TppProductRepository;
import inno.tretyakov.repository.TppRefProductClassRepository;
import inno.tretyakov.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class InstanceCreater implements InstanceProcessable {
    @Autowired
    TppProductRepository tppProductRepository;

    @Autowired
    AgreementRepository agreementRepository;

    @Autowired
    TppRefProductClassRepository tppRefProductClassRepository;

    @Autowired
    AccountService accountService;

    @Override
    public void process(InstanceDto instanceDto, RespInstanceDto responseInstanceDto) {
        List<TppProduct> tppProducts =  tppProductRepository.findFirstByNumber(instanceDto.getContractNumber());

        if (!tppProducts.isEmpty()) {
            throw new InvalidRequest("Номер договора "+ instanceDto.getContractNumber()+" уже существует! " + tppProducts.get(0).getId());
        }

        instanceDto.getInstanceArrangement().forEach(it -> {
            List<Agreement> agreements = agreementRepository.findFirstByNumber(it.getNumber());
            if(!agreements.isEmpty() & it.getNumber() !=null){
                System.out.println(it.getNumber());
                throw new InvalidRequest("Доп. соглашения номер "+it.getNumber()+" уже существует! " + agreements.get(0).getId());
            }
        });

        List<TppRefProductClass> tppRefProductClasses = tppRefProductClassRepository.findCatalog(instanceDto.getProductCode(), String.valueOf(AccountTypes.CL));
        if (tppRefProductClasses.isEmpty()) {
            throw new NoData("Продукта с кодом " + instanceDto.getProductCode() + " не найден!");
        }
        List<String> registryList = new ArrayList<>();
        tppRefProductClasses.forEach(it -> registryList.add(it.getInternal_id().toString()));
        responseInstanceDto.setRegisterId(registryList);

        TppProduct tppProduct = new TppProduct(null,
                new ArrayList<>(),
                Long.parseLong(responseInstanceDto.getRegisterId().get(0)),
                Long.parseLong(instanceDto.getMdmCode()),
                instanceDto.getProductType(),
                instanceDto.getContractNumber(),
                instanceDto.getPriority().longValue(),
                new Timestamp(instanceDto.getContractDate().getTime()),
                null,
                null,
                null,
                instanceDto.getInterestRatePenalty(),
                instanceDto.getMinimalBalance(),
                instanceDto.getThresholdAmount(),
                null,
                instanceDto.getRateType(),
                instanceDto.getTaxPercentageRate(),
                null,
                null);

        tppProductRepository.save(tppProduct);
        responseInstanceDto.setInstanceId(tppProduct.getId().toString());

        AccountDto accountDto = new AccountDto(
                Long.parseLong(responseInstanceDto.getInstanceId()),
                instanceDto.getRegisterType(),
                String.valueOf(AccountTypes.CL),
                instanceDto.getIsoCurrencyCode(),
                instanceDto.getBranchCode(),
                instanceDto.getUrgencyCode(),
                instanceDto.getMdmCode(),
                null,
                null,
                null,
                null);

        RespAccountIntDto respAccountIntDto = accountService.process(accountDto);

    }
}
