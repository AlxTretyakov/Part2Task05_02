package inno.tretyakov.exceptions;

import inno.tretyakov.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class Error {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> exceptionErrorsHandler(Exception e) {
        ErrorDto errorDto = new ErrorDto("500", e.getMessage(), Arrays.toString(e.getStackTrace()));
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidRequest.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> invExceptionErrorsHandler(Exception e) {
        ErrorDto errorDto = new ErrorDto("400", e.getMessage(), Arrays.toString(e.getStackTrace()));
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoData.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> NoDataFoundException(Exception e) {
        ErrorDto errorDto = new ErrorDto("404", e.getMessage(), Arrays.toString(e.getStackTrace()));
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

}
