package com.data.creation;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.api.queries.internal.GetEntityListFromKeyListCommand;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

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

    List<Key<Page>> pageList;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    List<Page> pages;

    public Scribble(){
        this("");
    }

    public Scribble(String title){
        this.title = title;
        pageList = new ArrayList<Key<Page>>();
        pages = new ArrayList<>();

        pages.add(new Page());
        pages.add(new Page());

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

    public List<Page> getPages2(){
        Readable<Page> getPagesFromTagsKeysAbstracted = new GetEntityListFromKeyListCommand<>(getPageList());
        List<Page> pageList = null;
        try {
            pageList = getPagesFromTagsKeysAbstracted.fetch().getList();
        }
        catch (FetchException ex){
            pageList = new ArrayList<>();
        }
        return pageList;
    }


    @Override
    public String toString() {
        return "Scribble{" +
                "scribbleId=" + scribbleId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", pages=\n" + pages +
                "\n}";
    }
}
