package com.data.api.createables;

import com.data.UserData;
import com.data.api.interfaces.Createable;
import com.data.structure.Tag;
import com.googlecode.objectify.Key;

/**
 * Created by drodrigues on 4/4/16.
 */
public class TagCreater extends Createable<Tag> {
    /**
     * things you know when you construct it can be called and passed into TagCreater
     * Fields that need to be filled in later will be passed to the TagCommandFill as parameters
     */
    private String tagname;
    private Key<UserData> userDataKey;

    public TagCreater(String tag, Key<UserData> userDataKey ){
        this.userDataKey = userDataKey;
        this.tagname = tag;
    }

    @Override
    public Tag getEntity() {
        return new Tag(tagname, userDataKey);
    }

}
