package com.data.structure;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by drodrigues on 3/30/16.
 */
@Entity
public class Tag {

//    @Parent
//    Key<UserData> userData;

    @Id
    Long tagId;

    @Index
    String name;

    public Tag(){}

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

}
