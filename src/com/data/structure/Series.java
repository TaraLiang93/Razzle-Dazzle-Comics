package com.data.structure;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.api.queries.internal.GetEntityListFromKeyListCommand;
import com.data.api.queries.internal.GetImgUrlFromBlobKey;
import com.data.creation.Chapter;
import com.google.appengine.api.blobstore.BlobKey;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by drodrigues on 3/30/16.
 */
@Entity
public class Series {

    @Ignore
    private static final String DEFAULT_IMG = "/img/profile_default.png";

    @Id
    Long seriesID;

    @Index
    Date createDate;

    BlobKey seriesCover;
    String title;
    String description;
    @Index
    int views;
//    Float rating;
    @Index
    boolean published;
    List<Key<Chapter>> chapterList;
    List<Key<Genre>> genreList;

    public Series(){
        chapterList = new ArrayList<>();
        genreList = new ArrayList<>();
        createDate = new Date();
        published = false;
        views = 0;
        title = "";
        description = "";
    }

    public Long getSeriesID() {
        return seriesID;
    }

    public void setSeriesID(Long seriesID) {
        this.seriesID = seriesID;
    }

    public BlobKey getSeriesCoverBlob() {
        return seriesCover;
    }

    public String getSeriesCover(){
        String url = (seriesCover == null)? DEFAULT_IMG : GetImgUrlFromBlobKey.getURL(seriesCover);
        return url;
    }

    public void setSeriesCover(BlobKey seriesCover) {
        this.seriesCover = seriesCover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public List<Key<Chapter>> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<Key<Chapter>> chapterList) {
        this.chapterList = chapterList;
    }

    public List<Key<Genre>> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Key<Genre>> genreList) {
        this.genreList = genreList;
    }

    public void addChapterToChapterList( Key<Chapter> chapterKey){
        this.chapterList.add(chapterKey);
    }

    public void addGenreToGenreList(Key<Genre> genreKey){
        this.genreList.add(genreKey);
    }

    public Key<Series> getKey(){
        return Key.create(Series.class, seriesID);
    }

    public List<Chapter> getChapters() {
        Readable<Chapter> getChapterFromChapterKeysAbstracted = new GetEntityListFromKeyListCommand<>(getChapterList());
        List<Chapter> chapterList = null;
        try {
            chapterList = getChapterFromChapterKeysAbstracted.fetch().getList();
        }
        catch (FetchException ex){
            chapterList = new ArrayList<>();
        }
        return chapterList;
    }

    public List<Genre> getGenres() {
        Readable<Genre> getGenreFromGenreKeysAbstracted = new GetEntityListFromKeyListCommand<>(getGenreList());
        List<Genre> genreList = null;
        try {
            genreList = getGenreFromGenreKeysAbstracted.fetch().getList();
        }
        catch (FetchException ex){
            genreList = new ArrayList<>();
        }
        return genreList;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void incrementViews(){
        this.views = this.views + 1;
    }

}
