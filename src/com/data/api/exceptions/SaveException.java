package com.data.api.exceptions;

/**
 * Created by drodrigues on 4/4/16.
 */
public class SaveException extends Exception{

    public SaveException() {
        this("There was an error Fetching the Entity");
    }

    public SaveException(String message){
        super(message);
    }

    public SaveException(String msg, Throwable nest){
        super(msg, nest);
    }


}
