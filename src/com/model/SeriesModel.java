package com.model;

import java.util.List;

/**
 * Created by Zhenya on 4/15/16.
 */
public class SeriesModel {

    private Long id;
    String title;
    String description;
    boolean published;
    String author;
    String artist;
    List<ChapterModel> chapterModelList;
//    List<GenreModel> genreModelList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getArtist() { return artist; }

    public void setArtist(String artist) { this.artist = artist; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public List<ChapterModel> getChapterModelList() {
        return chapterModelList;
    }

    public void setChapterModelList(List<ChapterModel> chapterModelList) {
        this.chapterModelList = chapterModelList;
    }
}
