package com.data.api.createables;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Createable;
import com.data.api.interfaces.Readable;
import com.data.api.queries.external.GetChapterByIDCommand;
import com.data.creation.Chapter;
import com.data.creation.Page;
import com.data.structure.FlowTask;

/**
 * Created by Zhenya on 4/13/16.
 */
public class PageCreater extends Createable<Page> {

    private String title;
    private String summary;
    private Long chapterId;

    public PageCreater(String chapterId){
        this(chapterId, null);
    }

    public PageCreater(String chapterId, String title){
        this(chapterId, null, null);
    }


    public PageCreater(String chapterId, String title, String summary){
        this.title = title;
        this.summary = summary;
        this.chapterId = Long.parseLong( chapterId);
    }

    public PageCreater(Long chapterId, String title, String summary){
        this.title = title;
        this.summary = summary;
        this.chapterId = chapterId;
    }


    @Override
    protected Page getEntity() throws CreateException, FetchException{

        Page page = new Page(title, summary);

        // Get the chapter
        // get the chapter's flow
        // set the page to the first flowtask of the flow that the chapter is following
        Readable<Chapter> chapterReadable = new GetChapterByIDCommand( this.chapterId);
        Chapter chapter = chapterReadable.fetch().getResult();

        FlowTask firstFlowTask = null;
        for(FlowTask flowTask : chapter.getFlow().getFlowTasks() ){
            if (flowTask.getIndex() == 0){
                firstFlowTask = flowTask;
            }
        }

        if( firstFlowTask != null){
           page.setFlowTask( firstFlowTask.getKey() );
        }
        else{
            throw new CreateException("could not find default flor for chapter");
        }

        return page;
    }
}
