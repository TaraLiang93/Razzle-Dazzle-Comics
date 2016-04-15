package com.data.api.createables;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Createable;
import com.data.structure.Series;
import com.model.SeriesModel;

/**
 * Created by Zhenya on 4/14/16.
 */
public class SeriesCreater extends Createable<Series> {
    SeriesModel seriesModel;

    public SeriesCreater(SeriesModel seriesModel){
        this.seriesModel = seriesModel;
    }

    @Override
    protected Series getEntity() throws CreateException, FetchException {
        if( seriesModel == null){
            throw new CreateException();
        }
        Series series = new Series();
        series.setSeriesCover(seriesModel.getSeriesCover());
        series.setTitle( seriesModel.getTitle());
        series.setDescription(seriesModel.getDescription());
        series.setPublished(seriesModel.isPublished());

        return series;
    }
}
