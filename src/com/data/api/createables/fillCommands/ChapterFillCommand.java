package com.data.api.createables.fillCommands;

import com.data.api.interfaces.FillDataCommand;
import com.data.creation.Chapter;
import com.google.appengine.api.datastore.Blob;

/**
 * Created by Zhenya on 4/9/16.
 */
public class ChapterFillCommand implements FillDataCommand<Chapter> {

    Blob chapterCover;
    String description;

    public ChapterFillCommand(Blob chapterCover){ //TODO : validation, what if need both Blob and String???
        this.chapterCover = chapterCover;
    }

    public ChapterFillCommand(String description){
        this.description = description;
    }

    @Override
    public void fillEntity(Chapter entity) { // TODO : throw exceptions
        if( this.chapterCover != null){
            entity.setChapterCover( this.chapterCover);
        }

        if(this.description != null){
            entity.setDescription(this.description);
        }

    }
}
