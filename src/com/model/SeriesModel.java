package com.model;

import com.google.appengine.api.datastore.Blob;

import java.util.List;

/**
 * Created by Zhenya on 4/15/16.
 */
public class SeriesModel {

    private Long id;
    private Blob seriesCover;
    String title;
    String description;
    boolean published;
    List<ChapterModel> chapterModelList;
//    List<GenreModel> genreModelList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Blob getSeriesCover() {
        return seriesCover;
    }

    public void setSeriesCover(Blob seriesCover) {
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

    public List<ChapterModel> getChapterModelList() {
        return chapterModelList;
    }

    public void setChapterModelList(List<ChapterModel> chapterModelList) {
        this.chapterModelList = chapterModelList;
    }
}
