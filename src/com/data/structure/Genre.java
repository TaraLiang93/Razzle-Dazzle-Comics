package com.data.structure;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.api.queries.external.GetGenreByIDCommand;
import com.data.api.queries.internal.GetEntityFromKeyCommand;
import com.data.api.queries.internal.GetEntityListFromKeyListCommand;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by drodrigues on 3/30/16.
 */
@Entity
public class Genre {
    @Id
    Long id;

    @Index
    String name;

    String imageSrc;

    List<Key<Series>> seriesList;


    public Genre(){
        seriesList = new ArrayList<>();
    }

    public List<Key<Series>> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<Key<Series>> seriesList) {
        this.seriesList = seriesList;
    }

    public void addSeriesToSeriesList( Key<Series> seriesKey){
        this.seriesList.add(seriesKey);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Key<Genre> getKey(){
        return Key.create(Genre.class, id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageSrc() { return imageSrc; }

    public void setImageSrc(String imageSrc) { this.imageSrc = imageSrc; }

    public List<Series> getSeries(){
        List<Series> seriesList;

        Readable<Series> seriesReadable = new GetEntityListFromKeyListCommand<>(this.seriesList);

        try {
            seriesList = seriesReadable.fetch().getList();
        } catch (FetchException e) {
            seriesList = new ArrayList<>();
        }


        return seriesList;
    }
}
