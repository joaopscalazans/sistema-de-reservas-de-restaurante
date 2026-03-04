package com.joaopscalazans.restaurante_api.infra.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(){

    }
    public EntityNotFoundException(String message){
        super(message);
    }

}
