package com.data.api.createables.fillCommands;

import com.data.api.interfaces.FillDataCommand;
import com.data.structure.Tag;

/**
 * Created by drodrigues on 4/4/16.
 */
public class TagCommandFill implements FillDataCommand<Tag> {
    String name;



    /**
     * constructor that takes in no tag nem will do nothing
     */
    public TagCommandFill(){
        //If tag command fill is not constructed with a name parameter then we don't change the name
       this("");
    }

    /**
     * Constructor for to the command to fill in a Tag with a name parameter
     * @param name this is the name of the Tag which is a property that needs to be set
     */
    public TagCommandFill(String name){
        this.name = name;
    }

    /**
     * Every Fill Command will know how to fill in its Entity
     * The TagCommandFill object represents the command which will fill in a tag
     * thus TagCommandFill will know how to fill in a Tag Entity
     * @param entity
     */
    @Override
    public void fillEntity(Tag entity) {
        // if name equals "" that means we won't set the name
        // if name does not equal "" it means that a name parameter was passed in on construction and we need
        // to set the name
        if( !(this.name.equals("")) ){
            entity.setName(this.name);
        }
    }
}
