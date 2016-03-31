package com.data.creation;

import com.data.UserData;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by drodrigues on 3/30/16.
 */
@Entity
public class Scribble {

//    public static final Logger logger = Logger.getLogger(Scribble.class.getName());

    @Parent
    Key<UserData> userData;

    @Id
    Long scribbleId;
    List<Page> pageList;

    public Long getScribbleId() {
        return scribbleId;
    }

    public void setScribbleId(Long scribbleId) {
        this.scribbleId = scribbleId;
    }

    public List<Page> getPageList() {
        return pageList;
    }

    public void setPageList(List<Page> pageList) {
        this.pageList = pageList;
    }

}
