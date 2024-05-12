package inno.tretyakov.service;

import inno.tretyakov.workers.IAccountWorker;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import inno.tretyakov.dto.AccountDto;
import inno.tretyakov.dto.RespAccountDto;
import inno.tretyakov.dto.RespAccountIntDto;

@Service
public class AccountService {

//    @Autowired
//    public List<AccountServiceable> accountServiceableList;

    @Autowired
    public IAccountWorker accountWorker;

    @Transactional
    public RespAccountIntDto process(AccountDto csaDto) {
        RespAccountDto rd = new RespAccountDto("");

        accountWorker.process(csaDto, rd);
//        for (AccountServiceable pr : accountServiceableList) {
//            pr.process(csaDto, rd);
//        }

        RespAccountIntDto accountResponse = new RespAccountIntDto(rd);
        return accountResponse;
    }
}
