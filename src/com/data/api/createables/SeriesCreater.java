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
    String author;
    String artist;

    public SeriesCreater(SeriesModel seriesModel){
        this.seriesModel = seriesModel;
    }

    public SeriesCreater(BlobKey seriesCover, String title, String description, Boolean isPublished){
        this.seriesCover = seriesCover;
        this.title =title;
        this.description = description;
        this.isPublished = isPublished;
    }

    public SeriesCreater(BlobKey seriesCover, String title, String description, Boolean isPublished, String author, String artist){
        this.seriesCover = seriesCover;
        this.title =title;
        this.description = description;
        this.isPublished = isPublished;
        this.author = author;
        this.artist = artist;
    }

    @Override
    protected Series getEntity() throws CreateException, FetchException {
        Series series = new Series();
        if( seriesModel != null) {
            series.setTitle(seriesModel.getTitle());
            series.setDescription(seriesModel.getDescription());
            series.setPublished(seriesModel.isPublished());
            series.setAuthor(seriesModel.getAuthor());
            series.setArtist(seriesModel.getArtist());

        }
        else{
            series.setSeriesCover(this.seriesCover);
            series.setTitle(this.title);
            series.setDescription(this.description);
            series.setPublished(this.isPublished);
            if(this.author != null && this.artist != null)
            {
                series.setAuthor(this.author);
                series.setArtist(this.artist);
            }
        }

        return series;
    }
}
