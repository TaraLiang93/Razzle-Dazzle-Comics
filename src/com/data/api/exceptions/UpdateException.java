package com.data.api.exceptions;

/**
 * Created by drodrigues on 4/4/16.
 */
public class UpdateException extends Exception {


    public UpdateException() {
        this("There was an error updating an Entity");
    }

    public UpdateException(String message){
        super(message);
    }
}
