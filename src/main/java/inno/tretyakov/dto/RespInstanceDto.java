package inno.tretyakov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class RespInstanceDto implements Serializable {
    String instanceId;
    List<String> registerId;
    List<String> supplementaryAgreementId;
}
