package com.data.creation;

import com.data.UserData;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.api.queries.internal.GetEntityFromKeyCommand;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by drodrigues on 3/30/16.
 */
@Entity
public class Comment {

    @Id
    private Long id;


    private String comment;
    private int index;
    private Key<UserData> userData;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Key<UserData> getUserData() {
        return userData;
    }

    public void setUserData(Key<UserData> userData) {
        this.userData = userData;
    }

    public UserData getUser(){
        Readable<UserData> userData = new GetEntityFromKeyCommand<>(getUserData());
        UserData data = null;
        try {
            data = userData.fetch().getResult();
        }
        catch (FetchException ex){
            ex.printStackTrace();
        }
        return data;
    }

    public Key<Comment> getKey() {
        return Key.create(Comment.class, this.id);
    }

}
