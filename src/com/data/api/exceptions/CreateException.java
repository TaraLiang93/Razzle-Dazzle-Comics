package com.data.api.exceptions;

/**
 * Created by Zhenya on 4/11/16.
 */
public class CreateException extends Exception {

    public CreateException() {
        this("There was an error Creating the Entity");
    }

    public CreateException(String message){
        super(message);
    }

}
