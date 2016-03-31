package com.data.creation;

import com.data.UserData;
import com.data.structure.Tag;
import com.google.appengine.labs.repackaged.org.json.JSONString;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

import java.util.List;

/**
 * Created by drodrigues on 3/30/16.
 */
@Entity
public class Doodle {

    @Parent
    Key<UserData> userData;

    @Id
    Long doodleId;

    Canvas canvas;

    @Index
    String title;

    @Index
    String description;

    @Index
    List<Tag> tagList;

    public Long getDoodleId() {
        return doodleId;
    }

    public void setDoodleId(Long doodleId) {
        this.doodleId = doodleId;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
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

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

}
