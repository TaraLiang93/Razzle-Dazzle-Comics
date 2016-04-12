package com.data.api.createables.fillCommands;

import com.data.api.exceptions.CreateException;
import com.data.api.interfaces.FillDataCommand;
import com.data.creation.Chapter;
import com.data.structure.Flow;
import com.google.appengine.api.datastore.Blob;

/**
 * Created by Zhenya on 4/9/16.
 */
public class ChapterFillCommand implements FillDataCommand<Chapter> {

    Blob chapterCover;
    Flow flow;
    String description;

    public ChapterFillCommand(Blob chapterCover){
        this.chapterCover = chapterCover;
    }

    public ChapterFillCommand(String description){
        this.description = description;
    }

    public ChapterFillCommand(String description, Blob chapterCover){
        this.description = description;
        this.chapterCover = chapterCover;
    }

    @Override
    public void fillEntity(Chapter entity) throws CreateException{ // TODO : throw exceptions
        if( this.chapterCover != null){
            entity.setChapterCover( this.chapterCover);
        }
        else{
            //TODO : If chapter cover is null, load default chapter image in fill command
        }

        if(this.description != null){
            entity.setDescription(this.description);
        }

        if((this.description == null || this.description.equals("")) && chapterCover== null){
            throw new CreateException();
        }

    }
}
