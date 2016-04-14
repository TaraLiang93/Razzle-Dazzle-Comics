package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.UpdateTask;
import com.data.api.queries.external.GetChapterByIDCommand;
import com.data.creation.Chapter;
import com.data.structure.Series;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/13/16.
 */
public class UpdateSeriesAddChapterTask implements UpdateTask<Series> {

    Long chapterId;

    public UpdateSeriesAddChapterTask( Long chapterId){
        this.chapterId = chapterId;
    }

    public UpdateSeriesAddChapterTask( String strChapterId){
        try {
            this.chapterId = Long.parseLong(strChapterId);
        }
        catch( NumberFormatException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public List<Series> update(Container<Series> entity) throws UpdateException, FetchException {
        Series series = entity.getResult();
        Readable<Chapter> getChapterReadable = new GetChapterByIDCommand(chapterId);
        Chapter chapterToAdd = getChapterReadable.fetch().getResult();
        series.addChapterToChapterList(chapterToAdd.getKey());

        List<Series> updateList = new ArrayList<>();
        updateList.add(series);

        return updateList;
    }



}
