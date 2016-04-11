package com.data.structure;

import com.data.UserData;
import com.data.api.DoodleQueries.internal.GetEntityFromKeyCommand;
import com.data.api.Readable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;


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

    public Tag(String name){
        this.name = name;
    }

    public Tag(String name, Key<UserData> userData){
        this.name = name;
        this.userData = userData;
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

    public UserData getUserData() {
        Readable<UserData> getUserDataFromKey = new GetEntityFromKeyCommand(this.userData);
        return getUserDataFromKey.fetch().getResult();
    }

    public void setUserData(Key<UserData> userData) {
        this.userData = userData;
    }


    public Key<Tag> getKey() {
        return Key.create(Tag.class, tagId);
    }


}
