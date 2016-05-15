package com.data.api.createables;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Createable;
import com.data.structure.Genre;


/**
 * Created by Zhenya on 5/13/16.
 */
public class GenreCreater extends Createable<Genre> {
    String genreName;
    String imageSrc;

    public GenreCreater( String genreName){
        this.genreName = genreName;
    }

    public GenreCreater( String genreName, String imageSrc){
        this.genreName = genreName;
        this.imageSrc = imageSrc;
    }

    @Override
    protected Genre getEntity() throws CreateException, FetchException {
        Genre genre = new Genre();

        genre.setName(this.genreName);
        genre.setImageSrc(this.imageSrc);

        return genre;
    }
}
