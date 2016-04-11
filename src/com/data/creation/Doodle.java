package com.data.creation;

import com.data.api.DoodleQueries.internal.GetEntityFromKeyCommand;
import com.data.api.DoodleQueries.internal.GetEntityListFromKeyListCommand;
import com.data.api.Readable;
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
        title = "";
        description = "";
        tagList = new ArrayList<Key<Tag>>();
        //TODO:create a new canvas
//        Canvas canvas = new Canvas();
//        canvas = canvas.getKey();
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
       Canvas retrievedCanvas = getCanvasFromKey.fetch().getResult();
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
        List<Tag> tagList = getTagsFromTagsKeysAbstracted.fetch().getList();
        return tagList;
    }

    // set the canvas object internally given a JSONString
    public void setCanvasJSON(JSONString canvas){
        Canvas canvas1 = new Canvas(canvas);
        this.canvas = canvas1.getKey();
    }


}
