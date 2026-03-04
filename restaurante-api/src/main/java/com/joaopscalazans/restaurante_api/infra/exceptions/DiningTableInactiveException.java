package com.joaopscalazans.restaurante_api.infra.exceptions;

public class DiningTableInactiveException extends RuntimeException {

    public DiningTableInactiveException(){
        
    }
    public DiningTableInactiveException(String message){
        super(message);
    }
}
