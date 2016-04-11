package com.data.api.createables;

import com.data.api.interfaces.Createable;
import com.data.creation.Chapter;
import com.data.structure.Flow;
import com.google.appengine.api.datastore.Blob;

/**
 * Created by Zhenya on 4/9/16.
 */
public class ChapterCreater extends Createable<Chapter> {

    String title;
    String chapterString;
    String description;
    Flow flow; //TODO : Remove flow, put into Fill Command
    Blob chapterCover; // TODO : Remove from here, load from Fill command

    public ChapterCreater(String title, String chapterString, Flow flow){
        this(title, chapterString, null, flow);
    }
    public ChapterCreater(String title, String chapterString, String description, Flow flow){
        this(title,chapterString,description,flow,null);
    }

    public ChapterCreater(String title, String chapterString, String description, Flow flow, Blob chapterCover){
        this.title = title;
        this.chapterString = chapterString;
        this.description = description;
        this.flow = flow;
        this.chapterCover = chapterCover; //TODO : If chapter cover is null, load default chapter image in fill command

        // Image service factory makeImageFromFileName
        // blob takes byte array
        // use image service
        //
    }

    @Override
    protected Chapter getEntity() { // TODO : throw exceptions, do validation

        Chapter chapter = new Chapter();

        if( this.title != null) {
            chapter.setTitle(this.title);
        }

        if( this.chapterString != null) {
            chapter.setChapterString(this.chapterString);
        }

        if( this.description != null) {
            chapter.setDescription(this.description);
        }

        if( this.flow != null) {
            chapter.setTheFlow(flow.getKey());
        }

        if( this.chapterCover != null){
            chapter.setChapterCover(this.chapterCover);
        }

        return chapter;

    }
}
