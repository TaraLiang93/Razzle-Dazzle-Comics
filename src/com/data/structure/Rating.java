package com.data.structure;

import com.data.UserData;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by drodrigues on 3/30/16.
 */
@Entity
public class Rating {
    @Id
    Long id;

    @Index
    int rating;

    Key<Series> seriesKey;
    Key<UserData> userDataKey;
    String seriesId;
    String userDataId;

    public Rating(){

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Key<Series> getSeriesKey() {
        return seriesKey;
    }

    public void setSeriesKey(Key<Series> seriesKey) {
        this.seriesKey = seriesKey;
    }

    public Key<UserData> getUserDataKey() {
        return userDataKey;
    }

    public void setUserDataKey(Key<UserData> userDataKey) {
        this.userDataKey = userDataKey;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getUserDataId() {
        return userDataId;
    }

    public void setUserDataId(String userDataId) {
        this.userDataId = userDataId;
    }

    public Key<Rating> getKey(){
        return Key.create(Rating.class, id);
    }

}
