package com.data.api.createables;

import com.data.UserData;
import com.data.api.exceptions.CreateException;
import com.data.api.interfaces.Createable;
import com.google.appengine.api.users.User;

/**
 * Created by Zhenya on 4/5/16.
 */
public class UserDataCreater extends Createable<UserData>{
    String username;
    String nickName;
    String UserDataId;

    User user;

    public UserDataCreater(User user){ //TODO : Do error Validation
        this.user = user;
    }

    public UserDataCreater(String username, String nickName, String UserDataId){
        this.username = username;
        this.nickName = nickName;
        this.UserDataId = UserDataId;
        //TODO : load default profile image
    }


    @Override
    protected UserData getEntity() throws CreateException { //TODO : Throw exceptions

        UserData userData = new UserData();

        if( user == null ){
            throw new CreateException("UserDataCreater user is null");
        }
        // if username was provided as a parameter then set it
        if( this.username != null && !(this.username.equals("")) ) {
            userData.setUserName(this.username);
        }

        if( this.nickName != null && !(this.nickName.equals("")) ) {
            userData.setNickName(this.nickName);
        }

        if( this.UserDataId != null && !(this.UserDataId.equals("")) ) {
            userData.setUserid(this.UserDataId);
        }

        return userData;
    }

}
