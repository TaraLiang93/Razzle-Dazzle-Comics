package com.data.creation;

import com.data.UserData;
import com.data.structure.FlowTask;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by drodrigues on 3/30/16.
 */
@Entity
public class Page {


    @Id
    private Long id;

    private String title;

    private String summary;

    private Date createDate;

    private Date drawnDate;

    private Date completeDate;

    private int numRevisions;

    private Key<FlowTask> flowTask;

    private Key<Page> nextRevision;

    private Key<Page> prevRevision;

    private Key<Canvas> layout;

    private List<Key<Scene>> sceneList;

    private List<Key<Comment>> commentList;

    private Key<UserData> createdBy;


    public Page(){

    }

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDrawnDate() {
        return drawnDate;
    }

    public void setDrawnDate(Date drawnDate) {
        this.drawnDate = drawnDate;
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

    public Key<FlowTask> getFlowTask() {
        return flowTask;
    }

    public void setFlowTask(Key<FlowTask> flowTask) {
        this.flowTask = flowTask;
    }

    public Key<Page> getNextRevision() {
        return nextRevision;
    }

    public void setNextRevision(Key<Page> nextRevision) {
        this.nextRevision = nextRevision;
    }

    public Key<Page> getPrevRevision() {
        return prevRevision;
    }

    public void setPrevRevision(Key<Page> prevRevision) {
        this.prevRevision = prevRevision;
    }

    public Key<Canvas> getLayout() {
        return layout;
    }

    public void setLayout(Key<Canvas> layout) {
        this.layout = layout;
    }

    public List<Key<Scene>> getSceneList() {
        return sceneList;
    }

    public void setSceneList(List<Key<Scene>> sceneList) {
        this.sceneList = sceneList;
    }

    public List<Scene> getScenes(){
        List<Scene> list = new ArrayList<>();
        list.add(new Scene("This is a fun setting!"));
        list.add(new Scene("This is a boring setting :("));
        list.add(new Scene("This is an awesome setting!"));
        list.add(new Scene("This scene invovles tara being bae."));
        return list;
    }

    public List<Key<Comment>> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Key<Comment>> commentList) {
        this.commentList = commentList;
    }

    public Key<UserData> getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Key<UserData> createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", createDate=" + createDate +
                ", drawnDate=" + drawnDate +
                ", completeDate=" + completeDate +
                ", numRevisions=" + numRevisions +
//                ", \nscenes=" + scene +
                "\n}\n\n";
    }

    public Key<Page> getKey() {
        return Key.create(Page.class, this.id);
    }
}
