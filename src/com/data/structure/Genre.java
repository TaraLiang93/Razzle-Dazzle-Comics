package com.data.structure;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.ArrayList;
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
}
