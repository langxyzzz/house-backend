package com.langxy.house.exception;

import com.langxy.house.commons.HttpResp;
import com.langxy.house.commons.HttpRespEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public HttpResp<Object> systemException(Exception e) {
        log.error("sys exception:{}", e.getMessage());
        e.printStackTrace();
        return HttpResp.error(HttpRespEnum.SYS_EXC.getCode(), HttpRespEnum.SYS_EXC.getMsg());
    }

    @ExceptionHandler(value = CustomerException.class)
    public HttpResp<Object> customerException(CustomerException e) {
        return HttpResp.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = BindException.class)
    public HttpResp<Object> validatorException(BindException e) {
        List<String> errorMsg = e.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        return HttpResp.error(HttpRespEnum.PARAM_EXC.getCode(), errorMsg.toString());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public HttpResp<Object> validatorException(MethodArgumentNotValidException e) {
        List<String> errorMsg = e.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        return HttpResp.error(HttpRespEnum.PARAM_EXC.getCode(), errorMsg.get(0));
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public HttpResp<Object> validatorException(ConstraintViolationException e) {
        List<String> errorMsg = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        return HttpResp.error(HttpRespEnum.PARAM_EXC.getCode(), errorMsg.get(0));
    }


}
