package inno.tretyakov.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PrivatePropertiesDto implements Serializable {
    private String key;
    private String value;
    private String name;
}
