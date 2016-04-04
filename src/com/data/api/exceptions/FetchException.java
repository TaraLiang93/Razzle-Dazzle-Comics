package com.data.api.exceptions;

/**
 * Created by drodrigues on 4/4/16.
 */
public class FetchException extends Exception {

    public FetchException() {
        this("There was an error Fetching the Entity");
    }

    public FetchException(String message){
        super(message);
    }
}
