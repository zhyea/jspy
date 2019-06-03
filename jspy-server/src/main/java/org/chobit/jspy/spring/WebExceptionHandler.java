package org.chobit.jspy.spring;


import org.chobit.jspy.exceptions.JSpyArgsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(JSpyArgsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleIllegalArgs() {
    }
}
