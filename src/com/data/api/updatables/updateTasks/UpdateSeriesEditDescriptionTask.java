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
 * Created by Zhenya on 4/27/16.
 */
public class UpdateSeriesEditDescriptionTask implements UpdateTask<Series> {

    String description;

    public UpdateSeriesEditDescriptionTask( String description ){

        this.description = description;

    }

    @Override
    public List<Series> update(Container<Series> entity) throws UpdateException, FetchException, CreateException {

        if(description == null || description.equals("")){
            throw new UpdateException("description null");
        }

        Series series = entity.getResult();
        series.setDescription(description);

        List<Series> seriesList = new ArrayList<>();
        seriesList.add(series);
        return seriesList;
    }
}
