package com.model;

import com.google.appengine.api.datastore.Blob;

import java.util.Date;
import java.util.List;

/**
 * Created by Zhenya on 4/15/16.
 */
public class ChapterModel {
    private Long chapterId;

    private  String title;
    private Blob chapterCover;
    private String chapterString;
    private String description;
    private Date createdDate;
    private Date lastModifiedDate;
    private Boolean published;

//    private FlowModel theFlow;
//    List<PublishedPageModel> publishedPageList;
//    List<TeamMemberModel> teamMemberList;
    List<PageModel> pageList;

    public ChapterModel(){}

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

    public Blob getChapterCover() {
        return chapterCover;
    }

    public void setChapterCover(Blob chapterCover) {
        this.chapterCover = chapterCover;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public List<PageModel> getPageList() {
        return pageList;
    }

    public void setPageList(List<PageModel> pageList) {
        this.pageList = pageList;
    }

}
