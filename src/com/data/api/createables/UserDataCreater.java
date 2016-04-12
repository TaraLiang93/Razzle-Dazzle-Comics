package com.data.api.createables;

import com.data.UserData;
import com.data.api.exceptions.CreateException;
import com.data.api.interfaces.Createable;
import com.google.appengine.api.users.User;

/**
 * Created by Zhenya on 4/5/16.
 */
public class UserDataCreater extends Createable<UserData>{
    User user;

    public UserDataCreater(User user){ //TODO : Do error Validation
        this.user = user;
    }



    @Override
    protected UserData getEntity() throws CreateException { //TODO : Throw exceptions

        UserData userData = new UserData();
        //TODO : load default profile image
        if( user == null ){
            throw new CreateException("UserDataCreater user is null");
        }

        userData.setUserid(user.getUserId());
        userData.setNickName(user.getNickname());
        userData.setUserName( user.getEmail());
        

        return userData;
    }

}
