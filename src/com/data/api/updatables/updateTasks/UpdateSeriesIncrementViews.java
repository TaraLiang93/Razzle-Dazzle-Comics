package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.UpdateTask;
import com.data.structure.Series;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 5/13/16.
 */
public class UpdateSeriesIncrementViews implements UpdateTask<Series> {

    String SeriesId;
    public UpdateSeriesIncrementViews(String SeriesID){
        this.SeriesId = SeriesID;
    }

    @Override
    public List<Series> update(Container<Series> entity) throws UpdateException, FetchException, CreateException {

        Series series = entity.getResult();
        series.incrementViews();

        List<Series> seriesList = new ArrayList<>();
        seriesList.add(series);
        return seriesList;
    }
}
