package com.data.api.createables;

import com.data.api.exceptions.CreateException;
import com.data.api.interfaces.Createable;
import com.data.creation.Scribble;

/**
 * Created by Zhenya on 4/5/16.
 */
public class ScribbleCreater extends Createable<Scribble> {

    private String title;
    private String description;


    /**
     * Constructor for ScribbleCreater with title if it is known
     * Would be called when title is known
     * @param title the title of the Scribble to create
     */
    public ScribbleCreater( String title){
        this(title,null);
    }


    public ScribbleCreater(String title, String description){
        this.title = title;
        this.description = description;
    }

    /**
     * This will create the entity with the known fields and then return it
     * @return
     */
    @Override
    protected Scribble getEntity() throws CreateException{ //TODO : throw exceptions
        // if there is a title then create Scribble with the title
        // else if there was no title passed in create default Scribble

        Scribble scribble = new Scribble();

        if( !this.title.equals("") && this.title != null){
            scribble.setTitle(this.title);
        }
        else{
            throw new CreateException("ScribbleCreater title null or invalid");
        }

        if( !this.description.equals("") && this.description != null){
            scribble.setDescription(this.description);
        }
        return  scribble;

    }

}
