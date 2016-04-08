package com.model;

import com.data.UserData;
import com.data.creation.Canvas;
import com.data.creation.Comment;

import java.util.Date;
import java.util.List;

/**
 * Created by drodrigues on 4/8/16.
 */
public class PageModel {

    private Long id;

    private String title;

    private String summary;

    private Date createDate;

    private Date drawnDate;

    private Date completeDate;

    private int numRevisions;

    private Canvas layout;

    private List<SceneModel> scenes;

    private List<Comment> comments;

    private UserData createdBy;


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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getDrawnDate() {
        return drawnDate;
    }

    public void setDrawnDate(Date drawnDate) {
        this.drawnDate = drawnDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public int getNumRevisions() {
        return numRevisions;
    }

    public void setNumRevisions(int numRevisions) {
        this.numRevisions = numRevisions;
    }

    public Canvas getLayout() {
        return layout;
    }

    public void setLayout(Canvas layout) {
        this.layout = layout;
    }

    public List<SceneModel> getScenes() {
        return scenes;
    }

    public void setScenes(List<SceneModel> scenes) {
        this.scenes = scenes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public UserData getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserData createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "PageModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", createDate=" + createDate +
                ", drawnDate=" + drawnDate +
                ", completeDate=" + completeDate +
                ", numRevisions=" + numRevisions +
                ", \nscenes=" + scenes +
                "\n}";
    }
}
