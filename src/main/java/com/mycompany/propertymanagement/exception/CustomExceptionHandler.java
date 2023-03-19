package com.mycompany.propertymanagement.exception;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(MethodArgumentNotValidException manv){
        List<ErrorModel> errorModelList = new ArrayList<>();
        ErrorModel errorModel;
        List<FieldError> fieldErrorList = manv.getBindingResult().getFieldErrors();

    for(FieldError fe: fieldErrorList){
        logger.debug("Inside the field validation: {}-{}",fe.getField(),fe.getDefaultMessage());
        logger.info("Inside the field validation: {}-{}",fe.getField(),fe.getDefaultMessage());
        errorModel = new ErrorModel();
        errorModel.setCode(fe.getField());
        errorModel.setMessage(fe.getDefaultMessage());
        errorModelList.add(errorModel);
    }
        logger.error("Handler exception is Thrown");
        return new ResponseEntity<List<ErrorModel>>(errorModelList, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BusinessException.class)
    public  ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException businessException){
        System.out.println("Business Exception is thrown");
        for(ErrorModel i : businessException.getErrors()){
            logger.debug("Business Exception is thrown:-level-debug {}-{}",i.getCode(),i.getMessage());
            logger.info("Business Exception is thrown:-level-info {}-{}",i.getCode(),i.getMessage());
            logger.warn("Business Exception is thrown:-level-warn {}-{}",i.getCode(),i.getMessage());
            logger.error("Business Exception is thrown:-level-error {}-{}",i.getCode(),i.getMessage());
        }
        return new ResponseEntity<List<ErrorModel>>(businessException.getErrors(), HttpStatus.BAD_REQUEST);
    }
}

