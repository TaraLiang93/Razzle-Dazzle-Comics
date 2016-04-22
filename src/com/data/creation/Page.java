package com.data.creation;

import com.data.UserData;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.api.queries.internal.GetEntityListFromKeyListCommand;
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

// String page;
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
        this(null);
    }

    public Page(String summary) {
        this(summary, new Date()); //Creation date is obv today!
    }

    public Page( String summary, Date createDate) {
        this(summary, createDate, null);
    }

    public Page(String summary, Date createDate, Date drawnDate) {
        this(summary, createDate, drawnDate, null);
    }

    public Page( String summary, Date createDate, Date drawnDate, Date completeDate) {
        this(summary, createDate, drawnDate, completeDate, 0);
    }

    public Page(String summary, Date createDate, Date drawnDate, Date completeDate, int numRevisions) {
        this(summary, createDate, drawnDate, completeDate, numRevisions,null);
    }

    public Page( String summary, Date createDate, Date drawnDate, Date completeDate, int numRevisions,
                Key<FlowTask> flowTask) {
        this( summary, createDate, drawnDate, completeDate, numRevisions, flowTask, null);
    }

    public Page( String summary, Date createDate, Date drawnDate, Date completeDate, int numRevisions,
                Key<FlowTask> flowTask, Key<Page> nextRevision) {
        this( summary, createDate, drawnDate, completeDate, numRevisions, flowTask, nextRevision, null);
    }

    public Page(String summary, Date createDate, Date drawnDate, Date completeDate, int numRevisions,
                Key<FlowTask> flowTask, Key<Page> nextRevision, Key<Page> prevRevision) {
        this(summary, createDate, drawnDate, completeDate, numRevisions, flowTask, nextRevision, prevRevision, null);
    }

    public Page( String summary, Date createDate, Date drawnDate, Date completeDate, int numRevisions,
                Key<FlowTask> flowTask, Key<Page> nextRevision, Key<Page> prevRevision, Key<Canvas> layout) {
        this(summary, createDate, drawnDate, completeDate, numRevisions, flowTask, nextRevision, prevRevision, layout, new ArrayList<Key<Scene>>());
    }

    public Page( String summary, Date createDate, Date drawnDate, Date completeDate, int numRevisions,
                Key<FlowTask> flowTask, Key<Page> nextRevision, Key<Page> prevRevision, Key<Canvas> layout,
                List<Key<Scene>> sceneList) {
        this(summary, createDate, drawnDate, completeDate, numRevisions, flowTask, nextRevision, prevRevision, layout, sceneList, new ArrayList<Key<Comment>>());
    }

    public Page(String summary, Date createDate, Date drawnDate, Date completeDate, int numRevisions,
                Key<FlowTask> flowTask, Key<Page> nextRevision, Key<Page> prevRevision, Key<Canvas> layout,
                List<Key<Scene>> sceneList, List<Key<Comment>> commentList) {
        this(summary,createDate,drawnDate,completeDate,numRevisions,flowTask,nextRevision,prevRevision,layout, sceneList, commentList, null);
    }

    public Page(String summary, Date createDate, Date drawnDate, Date completeDate, int numRevisions,
                Key<FlowTask> flowTask, Key<Page> nextRevision, Key<Page> prevRevision, Key<Canvas> layout,
                List<Key<Scene>> sceneList, List<Key<Comment>> commentList, Key<UserData> createdBy) {
        this.summary = summary;
        this.createDate = createDate;
        this.drawnDate = drawnDate;
        this.completeDate = completeDate;
        this.numRevisions = numRevisions;
        this.flowTask = flowTask;
        this.nextRevision = nextRevision;
        this.prevRevision = prevRevision;
        this.layout = layout;
        this.sceneList = sceneList;
        this.commentList = commentList;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        Readable<Scene> getPagesFromTagsKeysAbstracted = new GetEntityListFromKeyListCommand<>(getSceneList());
        List<Scene> scenes = null;
        try {
            scenes = getPagesFromTagsKeysAbstracted.fetch().getList();
        }
        catch (FetchException ex){
            scenes = new ArrayList<>();
        }
        return scenes;
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
                ", summary='" + summary + '\'' +
                ", createDate=" + createDate +
                ", drawnDate=" + drawnDate +
                ", completeDate=" + completeDate +
                ", numRevisions=" + numRevisions +
                "\n}\n\n";
    }

    public Key<Page> getKey() {
        return Key.create(Page.class, this.id);
    }
}
