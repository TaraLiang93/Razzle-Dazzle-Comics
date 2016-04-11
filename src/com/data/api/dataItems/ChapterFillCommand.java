package com.data.api.dataItems;

import com.data.api.FillDataCommand;
import com.data.creation.Chapter;
import com.google.appengine.api.datastore.Blob;

/**
 * Created by Zhenya on 4/9/16.
 */
public class ChapterFillCommand implements FillDataCommand<Chapter> {

    Blob chapterCover;
    String description;

    public ChapterFillCommand(Blob chapterCover){
        this.chapterCover = chapterCover;
    }

    public ChapterFillCommand(String description){
        this.description = description;
    }

    @Override
    public void fillEntity(Chapter entity) {
        if( this.chapterCover != null){
            entity.setChapterCover( this.chapterCover);
        }

        if(this.description != null){
            entity.setDescription(this.description);
        }

    }
}
