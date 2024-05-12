package inno.tretyakov.service;

import inno.tretyakov.workers.InstanceCreater;
import inno.tretyakov.workers.InstancePresents;
import inno.tretyakov.dto.InstanceDto;
import inno.tretyakov.dto.RespInstanceDto;
import inno.tretyakov.dto.RespInstanceIntDto;
import inno.tretyakov.interfaces.InstanceProcessable;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstanceService {

    @Autowired
    InstanceCreater instanceWorker;

    @Autowired
    InstancePresents instancePresents;

    @Autowired
    List<InstanceProcessable> instanceProcessableListExist;

    @Transactional
    public RespInstanceIntDto process(InstanceDto instanceDto) {

        RespInstanceDto respInstanceDto = new RespInstanceDto("", new ArrayList<>(), new ArrayList<>());

        if (instanceDto.getInstanceId() == null) {
            instanceWorker.process(instanceDto, respInstanceDto);
        } else {
            instancePresents.process(instanceDto, respInstanceDto);
        }

        RespInstanceIntDto instanceResponse = new RespInstanceIntDto(respInstanceDto);
        return instanceResponse;
    }


}
