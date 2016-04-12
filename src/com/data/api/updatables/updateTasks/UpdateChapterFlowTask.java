package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.UpdateTask;
import com.data.creation.Chapter;

import java.util.List;

/**
 * Created by Zhenya on 4/11/16.
 */
public class UpdateChapterFlowTask implements UpdateTask<Chapter> {

    public UpdateChapterFlowTask(){

    }


    @Override
    public List<Chapter> update(Container<Chapter> entity) throws UpdateException, FetchException {
        return null;
    }
}
