package com.data.api.createables.fillCommands;

import com.data.api.interfaces.FillDataCommand;
import com.data.creation.Doodle;
import com.data.structure.Tag;
import com.google.appengine.labs.repackaged.org.json.JSONString;
import com.googlecode.objectify.Key;

import java.util.List;

/**
 * Created by Zhenya on 4/5/16.
 */
public class DoodleCommandFill implements FillDataCommand<Doodle>{ //TODO : RENAME THIS FILE NAMAND ALL THE OTHER CLASSES
    JSONString canvas;
    List<Key<Tag>> tagList; //TODO : remove

    public DoodleCommandFill(){
        this(null);
    }

    public DoodleCommandFill(JSONString canvas){
        this(canvas, null);
    }

    public DoodleCommandFill(JSONString canvas, List<Key<Tag>> tagList){
        this.canvas = canvas;
        this.tagList = tagList;
    }

    @Override
    public void fillEntity(Doodle entity) {
        if( this.canvas != null){
            entity.setCanvasJSON(this.canvas);
        }
        if ( this.tagList != null){
            entity.setTagList(this.tagList);
        }
    }
}
