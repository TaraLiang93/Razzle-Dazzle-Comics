package com.data.api.createables.fillCommands;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.FillDataCommand;
import com.data.api.interfaces.Readable;
import com.data.api.queries.external.GetChapterByIDCommand;
import com.data.creation.Chapter;
import com.data.creation.Page;
import com.data.structure.FlowTask;

/**
 * Created by Zhenya on 4/13/16.
 */
public class PageFillCommand implements FillDataCommand<Page> {

    Long chapterId;
    public PageFillCommand(String chapterId){
        this.chapterId = Long.parseLong( chapterId );
    }


    public PageFillCommand(Long chapterId){
        this.chapterId = chapterId;
    }

    @Override
    public void fillEntity(Page entity) throws CreateException, FetchException {

        // Get the chapter
        // get the chapter's flow
        // set the page to the first flowtask of the flow that the chapter is following
        Readable<Chapter> chapterReadable = new GetChapterByIDCommand(chapterId);
        Chapter chapter = chapterReadable.fetch().getResult();

        entity.setIndex(chapter.getPageList().size()); //The index is the size of the next one. 1 element = size 1, means we are adding a second one

        FlowTask firstFlowTask = null;
        for(FlowTask flowTask : chapter.getFlow().getFlowTasks() ){
            if (flowTask.getIndex() == 0){
                firstFlowTask = flowTask;
            }
        }

        if( firstFlowTask != null){
            entity.setFlowTask( firstFlowTask.getKey() );
        }
        else{
            throw new CreateException("could not find default flor for chapter");
        }
    }
}
