package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.UpdateTask;
import com.data.structure.Series;
import com.google.appengine.api.blobstore.BlobKey;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by drodrigues on 5/13/16.
 */
public class UpdateSeriesChangeImageTask implements UpdateTask<Series> {

    private BlobKey key;

    public UpdateSeriesChangeImageTask(BlobKey key){
        this.key = key;
    }

    @Override
    public List<Series> update(Container<Series> entity) throws UpdateException, FetchException, CreateException {
        Series series = entity.getResult();

        if (series == null){
            throw new UpdateException("Series is null");
        }


        if(key !=null){
            series.setSeriesCover(key);
        }

        List<Series> seriesList = new ArrayList<>();
        seriesList.add(series);

        return seriesList;
    }
}
