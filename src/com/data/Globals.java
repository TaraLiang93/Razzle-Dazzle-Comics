package com.data;

import com.google.appengine.api.users.UserService;

import java.io.Serializable;

/**
 * Created by Jason on 4/8/16.
 */
public class Globals implements Serializable {

    private String status;
    private boolean accountCreated;

    public boolean isAccountCreated() {
        return accountCreated;
    }

    public void setAccountCreated(boolean accountCreated) {
        this.accountCreated = accountCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
