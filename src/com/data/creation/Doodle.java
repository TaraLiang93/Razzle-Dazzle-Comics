package com.data.creation;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.api.queries.internal.GetEntityFromKeyCommand;
import com.data.api.queries.internal.GetEntityListFromKeyListCommand;
import com.data.structure.Tag;
import com.google.appengine.labs.repackaged.org.json.JSONString;
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
public class Doodle {


//    Key<UserData> userData;

    @Id
    @Index
    Long doodleId;

    Key<Canvas> canvas;

    @Index
    String title;

    @Index
    String description;

    @Index
    List<Key<Tag>> tagList;

    public Doodle(){
        this(null);
    }

    public Doodle(String title) {
        this(title, null);
    }

    public Doodle(String title, String description) {
        this(title, description, new ArrayList<Key<Tag>>());
    }

    public Doodle(String title, String description, List<Key<Tag>> tagList) {
        this(title, description, tagList, null);
    }

    public Doodle(String title, String description, List<Key<Tag>> tagList, Key<Canvas> canvas) {
        this.tagList = tagList;
        this.canvas = canvas;
        this.title = title;
        this.description = description;
    }


    public Long getDoodleId() {
        return doodleId;
    }

    public void setDoodleId(Long doodleId) {
        this.doodleId = doodleId;
    }

    public Key<Canvas> getCanvasKey() {
        return canvas;
    }

    public void setCanvasKey( Key<Canvas> canvasKey) {
        this.canvas = canvasKey;
    }

    /**
     * Get the Canvas from the canvas key
     * @return the canvas object
     */
   public Canvas getCanvas() {
       Readable<Canvas> getCanvasFromKey = new GetEntityFromKeyCommand<>(canvas); // canvas is the canvas key
       Canvas retrievedCanvas = null;
       try {
           retrievedCanvas = getCanvasFromKey.fetch().getResult();
       }
       catch (FetchException ex){
           System.out.println("Null canvas");
           ex.printStackTrace();
       }
       return retrievedCanvas;
   }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Key<Tag>> getTagList() {
        return tagList;
    }

    public void setTagList(List<Key<Tag>> tagList) {
        this.tagList = tagList;
    }

    public  void addTagToList( Key<Tag> tag){
        this.tagList.add(tag);
    }

    // get the Key of a Doodle
    public Key<Doodle> getKey() {
        return Key.create(Doodle.class, doodleId);
    }

    public List<Tag> getTags(){

        Readable<Tag> getTagsFromTagsKeysAbstracted = new GetEntityListFromKeyListCommand<>(getTagList());
        List<Tag> tagList = null;
        try {
            tagList = getTagsFromTagsKeysAbstracted.fetch().getList();
        }
        catch (FetchException ex){
            tagList = new ArrayList<>();
        }
        return tagList;
    }

    // set the canvas object internally given a JSONString
    public void setCanvasJSON(JSONString canvas){
        Canvas canvas1 = new Canvas(canvas);
        this.canvas = canvas1.getKey();
    }


}
