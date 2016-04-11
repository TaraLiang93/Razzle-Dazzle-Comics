package com.data.api.createables;

import com.data.UserData;
import com.data.api.Createable;
import com.data.creation.Doodle;
import com.data.creation.Scribble;
import com.data.structure.Bookmark;
import com.data.structure.Flow;
import com.data.structure.Series;
import com.data.structure.Tag;
import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.users.User;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;

import java.util.List;

/**
 * Created by Zhenya on 4/5/16.
 */
public class UserDataCreater extends Createable<UserData>{
    String username;
    String nickName;
    String UserDataId;

    public UserDataCreater(User user){
        this.username = user.getEmail();
        this.nickName = user.getNickname();
        this.UserDataId = user.getUserId();
    }

    public UserDataCreater(String username, String nickName, String UserDataId){
        this.username = username;
        this.nickName = nickName;
        this.UserDataId = UserDataId;
    }


    @Override
    protected UserData getEntity() {
        UserData userData = new UserData();
        // if username was provided as a parameter then set it
        userData.setUserName(this.username);
        userData.setNickName(this.nickName);
        userData.setUserid(this.UserDataId);
        return userData;
    }

}
