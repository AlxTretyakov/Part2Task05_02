package inno.tretyakov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class RespInstanceIntDto implements Serializable {
    RespInstanceDto data;
}
