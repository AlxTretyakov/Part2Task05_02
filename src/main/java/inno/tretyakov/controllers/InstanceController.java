package inno.tretyakov.controllers;

import inno.tretyakov.dto.InstanceDto;
import inno.tretyakov.dto.RespInstanceIntDto;
import inno.tretyakov.service.InstanceService;
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
@RequestMapping("/instance/")
public class InstanceController {

    @Autowired
    InstanceService instanceService;

    @PostMapping("/create")
    ResponseEntity<RespInstanceIntDto> createInstanceRegister(@Valid @RequestBody InstanceDto csiDto) {
        RespInstanceIntDto r = instanceService.process(csiDto);
        return new ResponseEntity<>(r, HttpStatus.CREATED);
    }
}
