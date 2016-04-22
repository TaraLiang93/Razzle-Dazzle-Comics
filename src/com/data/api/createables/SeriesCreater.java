package com.data.api.createables;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Createable;
import com.data.structure.Series;
import com.google.appengine.api.blobstore.BlobKey;
import com.model.SeriesModel;

/**
 * Created by Zhenya on 4/14/16.
 */
public class SeriesCreater extends Createable<Series> {
    SeriesModel seriesModel;
    BlobKey seriesCover;
    String title;
    String description;
    Boolean isPublished;

    public SeriesCreater(SeriesModel seriesModel){
        this.seriesModel = seriesModel;
    }

    public SeriesCreater(BlobKey seriesCover, String title, String description, Boolean isPublished){
        this.seriesCover = seriesCover;
        this.title =title;
        this.description = description;
        this.isPublished = isPublished;
    }

    @Override
    protected Series getEntity() throws CreateException, FetchException {
        Series series = new Series();
        if( seriesModel != null) {
            series.setTitle(seriesModel.getTitle());
            series.setDescription(seriesModel.getDescription());
            series.setPublished(seriesModel.isPublished());
        }
        else{
            series.setSeriesCover(this.seriesCover);
            series.setTitle(this.title);
            series.setDescription(this.description);
            series.setPublished(this.isPublished);
        }

        return series;
    }
}
