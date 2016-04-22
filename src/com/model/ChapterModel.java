package com.model;

import java.util.List;

/**
 * Created by Zhenya on 4/15/16.
 */
public class ChapterModel {
    private String chapterId;
    private String title;
    private String chapterString;
    private String description;
    private List<PageModel> pages;

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChapterString() {
        return chapterString;
    }

    public void setChapterString(String chapterString) {
        this.chapterString = chapterString;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PageModel> getPageList() {
        return pages;
    }

    public void setPageList(List<PageModel> pageList) {
        this.pages = pageList;
    }

}
