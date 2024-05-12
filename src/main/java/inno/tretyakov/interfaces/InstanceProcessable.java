package inno.tretyakov.interfaces;

import inno.tretyakov.dto.InstanceDto;
import inno.tretyakov.dto.RespInstanceDto;

public interface InstanceProcessable {
    void process(InstanceDto instanceDto, RespInstanceDto responseInstanceDto);
}
