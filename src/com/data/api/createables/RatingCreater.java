package com.data.api.createables;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Createable;
import com.data.api.interfaces.Readable;
import com.data.api.queries.external.GetSeriesByIDCommand;
import com.data.structure.Rating;
import com.data.structure.Series;
import com.google.appengine.api.users.User;

/**
 * Created by Zhenya on 5/13/16.
 */
public class RatingCreater extends Createable<Rating> {
    int rating;
    String seriesId;
    User user;

    public RatingCreater(User user, int rating, String seriesId){
        this.user = user;
        this.rating =rating;
        this.seriesId = seriesId;
    }

    @Override
    protected Rating getEntity() throws CreateException, FetchException {
        Rating theRating = new Rating();
        theRating.setRating(rating);
        theRating.setSeriesId(seriesId);

        Readable<Series> seriesReadable = new GetSeriesByIDCommand(seriesId);
        theRating.setSeriesKey( seriesReadable.fetch().getResult().getKey() );

        return theRating;
    }
}
