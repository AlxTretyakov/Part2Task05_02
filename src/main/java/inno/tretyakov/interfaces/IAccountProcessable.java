package inno.tretyakov.interfaces;

import inno.tretyakov.dto.AccountDto;
import inno.tretyakov.dto.RespAccountDto;

public interface IAccountProcessable {
    void process(AccountDto accountDto, RespAccountDto responseAccountDto);
}
