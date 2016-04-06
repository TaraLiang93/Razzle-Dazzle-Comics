package com.data.creation;

import com.data.UserData;
import com.data.api.DoodleQueries.GetEntityListFromKeyListCommand;
import com.data.api.Readable;
import com.data.structure.Tag;
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
public class Scribble {

//    public static final Logger logger = Logger.getLogger(Scribble.class.getName());


//    Key<UserData> userData;

    @Id
    Long scribbleId;

    @Index
    String title;

    @Index
    String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    List<Key<Page>> pageList;


    public Scribble(){
        pageList = new ArrayList<Key<Page>>();
    }

    public Scribble(String title){
        this.title = title;
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

    public List<Page> getPages(){
        List<Page> pages = new ArrayList<>();

        pages.add(new Page());
        pages.add(new Page());
        pages.add(new Page());

        return pages;
    }

    public void addPageToList( Key<Page> pageKey){
        pageList.add(pageKey);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Key<Scribble> getKey() {
        return Key.create(Scribble.class, scribbleId);
    }

    public List<Page> getPages(){
        Readable<Page> getPagesFromTagsKeysAbstracted = new GetEntityListFromKeyListCommand<>(getPageList());
        List<Page> pageList = getPagesFromTagsKeysAbstracted.fetch().getList();
        return pageList;
    }

}
