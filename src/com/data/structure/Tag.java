package com.data.structure;

import com.data.UserData;
import com.data.creation.Doodle;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by drodrigues on 3/30/16.
 */
@Entity
public class Tag {

    @Parent
    Key<UserData> userData;

    @Id
    Long tagId;

    @Index
    String name;


    public Tag(){
        name = "";
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Key<UserData> getUserData() {
        return userData;
    }

    public void setUserData(Key<UserData> userData) {
        this.userData = userData;
    }


    public Key<Tag> getKey() {
        return Key.create(Tag.class, tagId);
    }


}
