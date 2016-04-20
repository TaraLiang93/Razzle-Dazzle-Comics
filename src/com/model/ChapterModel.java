package com.model;

import java.util.List;

/**
 * Created by Zhenya on 4/15/16.
 */
public class ChapterModel {
    private Long chapterId;

    private  String title;
    private String chapterString;
    private String description;

//    private FlowModel theFlow;
//    List<PublishedPageModel> publishedPageList;
//    List<TeamMemberModel> teamMemberList;
    List<PageModel> pageList;

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
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
        return pageList;
    }

    public void setPageList(List<PageModel> pageList) {
        this.pageList = pageList;
    }
}
