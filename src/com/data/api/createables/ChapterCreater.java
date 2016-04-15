package com.data.api.createables;

import com.data.api.exceptions.CreateException;
import com.data.api.interfaces.Createable;
import com.data.creation.Chapter;

/**
 * Created by Zhenya on 4/9/16.
 */
public class ChapterCreater extends Createable<Chapter> {

    String title;
    String chapterString;
    String description;


    public ChapterCreater(String title, String chapterString){
        this(title, chapterString, null);
    }


    public ChapterCreater(String title, String chapterString, String description){
        this.title = title;
        this.chapterString = chapterString;
        this.description = description;


        // Image service factory makeImageFromFileName
        // blob takes byte array
        // use image service
    }

    @Override
    protected Chapter getEntity() throws CreateException{ // TODO : throw exceptions, do validation

        Chapter chapter = new Chapter();

        if( this.title == null || this.title.equals("") ||
                this.chapterString == null || this.chapterString.equals("")){
            chapter.setTitle(this.title);
            chapter.setDescription(this.description);
        }
        else{
            throw new CreateException();
        }

        if( this.description != null &&  !(this.description.equals(""))) {
            chapter.setDescription(this.description);
        }


        return chapter;

    }
}
