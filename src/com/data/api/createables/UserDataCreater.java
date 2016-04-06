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
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;

import java.util.List;

/**
 * Created by Zhenya on 4/5/16.
 */
public class UserDataCreater extends Createable<UserData>{
    String username;
    String preferredEmail;
    String nickName;
    String location;
    String description;

    /**
     * Default constructor with no parameters
     */
    public UserDataCreater(){
        this("");
    }

    public UserDataCreater(String username){
        this(username, "");
    }

    public UserDataCreater(String username, String preferredEmail){
        this(username, preferredEmail, "");
    }

    public UserDataCreater(String username, String preferredEmail, String nickName){
        this(username, preferredEmail, nickName, "");
    }

    public UserDataCreater(String username, String preferredEmail, String nickName, String location){
        this(username, preferredEmail, nickName, location, "");
    }

    public UserDataCreater(String username, String preferredEmail, String nickName, String location, String description){
        this.username = username;
        this.preferredEmail = preferredEmail;
        this.nickName = nickName;
        this.location = location;
        this.description = description;
    }


    @Override
    protected UserData getEntity() {
        UserData userData = new UserData();
        // if username was provided as a parameter then set it
        if( !(this.username.equals("")) ){
            userData.setUserName(this.username);
        }
        if( !(this.preferredEmail.equals("")) ){
            userData.setPreferredEmail(this.preferredEmail);
        }
        if( !(this.nickName.equals("")) ){
            userData.setNickName(this.nickName);
        }
        if( !(this.location.equals("")) ){
            userData.setLocation(this.location);
        }
        if( !(this.description.equals("")) ){
            userData.setDescription(this.description);
        }

        return userData;
    }

}
