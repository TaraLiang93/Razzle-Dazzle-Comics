package com.data.api.createables;

import com.data.api.Createable;
import com.data.creation.Canvas;
import com.data.creation.Doodle;
import com.data.structure.Tag;
import com.google.appengine.labs.repackaged.org.json.JSONString;
import com.googlecode.objectify.Key;

import java.util.List;

/**
 * Created by Zhenya on 4/5/16.
 */
public class DoodleCreater extends Createable<Doodle> {
    String title;
    String description;
    JSONString canvas;

    /**
     * Default Constructor when nothing is known
     */
    public DoodleCreater(){
        this("");
    }

    /**
     * Constructor for DoodleCreater if title is known
     * @param title the title
     */
    public DoodleCreater(String title){
        this(title,"");
    }

    /**
     * Constructor for DoodleCreater if title and description is known
     * @param title the title
     * @param description the description
     */
    public DoodleCreater(String title, String description){
        this(title,description,null);
    }

    public DoodleCreater(String title, String description, JSONString canvas){
        this.title = title;
        this.description = description;
        this.canvas =canvas;
    }


    @Override
    protected Doodle getEntity() {
        Doodle doodle = new Doodle();

        // if title was passed in as parameter then set the title
        if( ! (this.title.equals("")) ){
            doodle.setTitle(title);
        }

        // if description was passed in as parameter then set the description
        if( ! (this.description.equals("")) ){
            doodle.setDescription(this.description);
        }

        if( this.canvas!=null){
            doodle.setCanvasJSON(this.canvas);
        }

        return doodle;
    }


}
