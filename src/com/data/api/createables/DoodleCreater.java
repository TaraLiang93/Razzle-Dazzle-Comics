package com.data.api.createables;

import com.data.api.exceptions.CreateException;
import com.data.api.interfaces.Createable;
import com.data.creation.Doodle;

/**
 * Created by Zhenya on 4/5/16.
 */
public class DoodleCreater extends Createable<Doodle> {
    String title;
    String description;
    String canvas;



    /**
     * Constructor for DoodleCreater if title is known
     * @param title the title
     */
    public DoodleCreater(String title){
        this(title,null);
    }

    /**
     * Constructor for DoodleCreater if title and description is known
     * @param title the title
     * @param description the description
     */
    public DoodleCreater(String title, String description){
        this(title,description,null);
    }

    public DoodleCreater(String title, String description, String canvas){
        this.title = title;
        this.description = description;
        this.canvas =canvas;
    }


    @Override
    protected Doodle getEntity() throws CreateException{ //TODO : throw exceptions, do validation
        Doodle doodle = new Doodle();

        // if title was passed in as parameter then set the title
        if( this.title != null && !(this.title.equals("")) ){
            doodle.setTitle(title);
        }
        else{
            throw new CreateException();
        }

        // if description was passed in as parameter then set the description
        if( this.description != null && !(this.description.equals(""))  ){
            doodle.setDescription(this.description);
        }

        if(  this.canvas!=null ){
            doodle.setCanvasJSON(this.canvas);
        }

        return doodle;

    }


}
