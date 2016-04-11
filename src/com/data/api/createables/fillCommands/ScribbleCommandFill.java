package com.data.api.createables.fillCommands;

import com.data.api.interfaces.FillDataCommand;
import com.data.creation.Page;
import com.data.creation.Scribble;
import com.googlecode.objectify.Key;

import java.util.List;

/**
 * Created by Zhenya on 4/5/16.
 */
public class ScribbleCommandFill implements FillDataCommand<Scribble>{
    List<Key<Page>> pageList;


    public ScribbleCommandFill( List<Key<Page>> pageList){
        this.pageList = pageList;
    } //TODO: Take Scribble Model


    /**
     * This method will fill the Scribble entity that is passed in
     * @param entity the entity that the ScribbleCommandFill will fill
     */
    @Override
    public void fillEntity(Scribble entity) {
        //set pageList of the scribble
        entity.setPageList( this.pageList);

    }
}
