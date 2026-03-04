package com.joaopscalazans.restaurante_api.infra.exceptions;

public class BusinessException extends RuntimeException{

    public BusinessException(){
        
    }

    public BusinessException(String message){
        super(message);
    }

}
