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

    Long chapterIdToAdd;

    public UpdateSeriesAddChapterTask(Long chapterIdToAdd){
        this.chapterIdToAdd = chapterIdToAdd;
    }

    public UpdateSeriesAddChapterTask(String chapterIdToAdd){
        try {
            this.chapterIdToAdd = Long.parseLong(chapterIdToAdd);
        }
        catch ( NumberFormatException ex){
            ex.printStackTrace();
        }
    }

    // just to make the error go away in chapter controller
    public UpdateSeriesAddChapterTask(Chapter chapter){
        chapterIdToAdd = chapter.getChapterId();
    }


    @Override
    public List<Series> update(Container<Series> entity) throws UpdateException, FetchException {
        Series series = entity.getResult();

        Readable<Chapter> chapterReadable = new GetChapterByIDCommand(chapterIdToAdd);
        Chapter chapter = chapterReadable.fetch().getResult();

        series.addChapterToChapterList( chapter.getKey() );

        List<Series> updateList = new ArrayList<>();
        updateList.add(series);

        return updateList;
    }



}
