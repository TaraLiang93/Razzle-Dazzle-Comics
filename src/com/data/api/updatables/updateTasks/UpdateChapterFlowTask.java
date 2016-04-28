package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.UpdateTask;
import com.data.api.queries.external.GetFlowByIDCommand;
import com.data.creation.Chapter;
import com.data.structure.Flow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/11/16.
 */
public class UpdateChapterFlowTask implements UpdateTask<Chapter> {
    Long flowIdLong;


    public UpdateChapterFlowTask(Long flowIdLong){
        this.flowIdLong = flowIdLong;
    }

    public UpdateChapterFlowTask(String strId){
        this.flowIdLong = Long.parseLong(strId);
    }


    @Override
    public List<Chapter> update(Container<Chapter> entity) throws UpdateException, FetchException {
        Chapter chapter = entity.getResult();
        if(this.flowIdLong == null){
            throw new UpdateException("UpdateChapterFlowTask flowIdLong is null");
        }
        else{
            Readable<Flow> loadFlow = new GetFlowByIDCommand(this.flowIdLong);
            Flow flow =  loadFlow.fetch().getResult();
            chapter.setTheFlow(flow.getKey());
        }

        List<Chapter> chapterList = new ArrayList<>();
        chapterList.add(chapter);

        return chapterList;
    }
}
