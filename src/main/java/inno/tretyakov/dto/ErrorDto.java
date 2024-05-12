package inno.tretyakov.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ErrorDto implements Serializable {
    private String errorCode;
    private String errorText;
    private String errorStack;

    public ErrorDto(String errorCode, String errorText, String errorStack) {
        this.errorCode = errorCode;
        this.errorText = errorText;
        this.errorStack = errorStack;
    }
}
