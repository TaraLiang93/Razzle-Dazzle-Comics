package com.data.api.dataItems;

import com.data.api.Createable;
import com.data.api.FillDataCommand;
import com.data.creation.Canvas;
import com.data.creation.Doodle;
import com.data.creation.Scribble;
import com.data.structure.Tag;
import com.google.appengine.labs.repackaged.org.json.JSONString;
import com.googlecode.objectify.Key;

import java.util.List;

/**
 * Created by Zhenya on 4/5/16.
 */
public class DoodleCommandFill implements FillDataCommand<Doodle>{
    JSONString canvas;
    List<Key<Tag>> tagList;

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
