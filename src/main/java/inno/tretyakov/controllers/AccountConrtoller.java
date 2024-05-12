package inno.tretyakov.controllers;

import inno.tretyakov.dto.AccountDto;
import inno.tretyakov.dto.RespAccountIntDto;
import inno.tretyakov.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/account/")
public class AccountConrtoller {

    @Autowired
    AccountService accountService;

    @PostMapping("/create")
    ResponseEntity<RespAccountIntDto> createProductRegister(@Valid @RequestBody AccountDto csaDto) {
        RespAccountIntDto r = accountService.process(csaDto);
        return new ResponseEntity<>(r, HttpStatus.CREATED);
    }

}

