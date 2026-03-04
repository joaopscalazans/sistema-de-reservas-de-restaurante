package com.joaopscalazans.restaurante_api.infra.exceptions;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.joaopscalazans.restaurante_api.dto.ExceptionDTO;

import jakarta.servlet.http.HttpServletRequest;

//Primeira vez fazendo com auxilio de IA
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDTO> argumentNotValidHandler(MethodArgumentNotValidException e,HttpServletRequest request){
        String error = e.getBindingResult().getFieldErrors().stream()
        .map(err -> err.getField()+":"+err.getDefaultMessage())
        .collect(Collectors.joining(","));
        return this.createResponse(HttpStatus.BAD_REQUEST, "ARGUMENT_NOT_VALID", error, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDTO> userNotFoundHandler(EntityNotFoundException e,HttpServletRequest request){
        return this.createResponse(HttpStatus.NOT_FOUND, "NOT_FOUND", e.getMessage(), request);
    }


    @ExceptionHandler(DiningTableInactiveException.class)
     public ResponseEntity<ExceptionDTO> diningTableInactiveHandler(DiningTableInactiveException e, HttpServletRequest request){
        return this.createResponse(HttpStatus.BAD_REQUEST, "DINING_TABLE_INACTIVE", e.getMessage(), request);
    }
    //

 @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionDTO> businessHandler(BusinessException e, HttpServletRequest request){
        return this.createResponse(HttpStatus.BAD_REQUEST, "BUSINESS_ERROR", e.getMessage(), request);
    }
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionDTO> unauthorizedHandler(UnauthorizedException e, HttpServletRequest request){
        return this.createResponse(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", e.getMessage(), request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> internalServerErrorHandler(Exception e,HttpServletRequest request){
        e.printStackTrace();
        return this.createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", "Ocorreu um erro inesperado no servidor", request);
    }

    private ResponseEntity<ExceptionDTO> createResponse(HttpStatus httpStatus,String error,String message,HttpServletRequest request){
        ExceptionDTO dto = new ExceptionDTO(
            LocalDateTime.now(),
            httpStatus.value(),
            error,
            message,
            request.getRequestURI()
        );
        return ResponseEntity.status(httpStatus).body(dto);
    }
}
