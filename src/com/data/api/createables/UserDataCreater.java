package com.data.api.createables;

import com.data.UserData;
import com.data.api.interfaces.Createable;
import com.google.appengine.api.users.User;

/**
 * Created by Zhenya on 4/5/16.
 */
public class UserDataCreater extends Createable<UserData>{
    String username;
    String nickName;
    String UserDataId;

    public UserDataCreater(User user){ //TODO : Do error Validation
        this.username = user.getEmail();
        this.nickName = user.getNickname();
        this.UserDataId = user.getUserId();
    }

    public UserDataCreater(String username, String nickName, String UserDataId){
        this.username = username;
        this.nickName = nickName;
        this.UserDataId = UserDataId;
        //TODO : load default profile image
    }



    @Override
    protected UserData getEntity() { //TODO : Throw exceptions
        UserData userData = new UserData();
        // if username was provided as a parameter then set it
        userData.setUserName(this.username);
        userData.setNickName(this.nickName);
        userData.setUserid(this.UserDataId);
        return userData;
    }

}
