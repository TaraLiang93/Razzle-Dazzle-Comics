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
 * Created by Zhenya on 4/15/16.
 */
public class UpdateSeriesToggleVisibilityTask implements UpdateTask<Series> {

    public UpdateSeriesToggleVisibilityTask(){
        //take no parameters just toggle published boolean.
    }

    @Override
    public List<Series> update(Container<Series> entity) throws UpdateException, FetchException, CreateException {
        Series series = entity.getResult();
        series.setPublished( !series.isPublished() ); // flip the value of is published

        List<Series> seriesList = new ArrayList<>();
        return seriesList;
    }
}
