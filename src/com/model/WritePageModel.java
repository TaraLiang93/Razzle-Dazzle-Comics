package com.model;

import java.util.List;

/**
 * Created by drodrigues on 4/21/16.
 */
public class WritePageModel {

    private String chapterID;

    private List<PageModel> pages;

    public WritePageModel(){
    }

    public String getChapterID() {
        return chapterID;
    }

    public void setChapterID(String chapterID) {
        this.chapterID = chapterID;
    }

    public List<PageModel> getPages() {
        return pages;
    }

    public void setPages(List<PageModel> pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "WritePageModel{" +
                "chapterID='" + chapterID + '\'' +
                ", pages=" + pages +
                '}';
    }
}
