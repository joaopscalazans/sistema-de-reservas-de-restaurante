package com.joaopscalazans.restaurante_api.infra.exceptions;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException(){
        
    }

    public UnauthorizedException(String message){
        super(message);
    }

}
