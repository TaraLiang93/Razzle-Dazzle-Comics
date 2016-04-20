package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.UpdateTask;
import com.data.creation.Chapter;
import com.data.structure.Series;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/13/16.
 */
public class UpdateSeriesAddChapterTask implements UpdateTask<Series> {

    Chapter chapterToAdd;

    public UpdateSeriesAddChapterTask(Chapter chapter){
        this.chapterToAdd = chapter;
    }


    @Override
    public List<Series> update(Container<Series> entity) throws UpdateException, FetchException {
        Series series = entity.getResult();

        series.addChapterToChapterList(chapterToAdd.getKey());

        List<Series> updateList = new ArrayList<>();
        updateList.add(series);

        return updateList;
    }



}
