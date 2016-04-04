package com.data.api.createables;

import com.data.api.Createable;
import com.data.structure.Tag;

/**
 * Created by drodrigues on 4/4/16.
 */
public class TagCreater extends Createable<Tag> {

    private String tagname;
    public TagCreater(String tag){this.tagname = tag;}

    @Override
    public Tag getEntity() {
        return new Tag();
    }

}
