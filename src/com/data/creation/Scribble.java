package com.data.creation;

import com.data.UserData;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by drodrigues on 3/30/16.
 */
@Entity
public class Scribble {

//    public static final Logger logger = Logger.getLogger(Scribble.class.getName());

//    @Parent
//    Key<UserData> userData;

    @Id
    Long scribbleId;
    List<Key<Page>> pageList;


    public Scribble(){
        pageList = new ArrayList<Key<Page>>();
    }

    public Long getScribbleId() {
        return scribbleId;
    }

    public void setScribbleId(Long scribbleId) {
        this.scribbleId = scribbleId;
    }

    public List<Key<Page>> getPageList() {
        return pageList;
    }

    public void setPageList(List<Key<Page>> pageList) {
        this.pageList = pageList;
    }
}
