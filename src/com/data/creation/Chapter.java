package com.data.creation;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.api.queries.internal.GetEntityFromKeyCommand;
import com.data.api.queries.internal.GetEntityListFromKeyListCommand;
import com.data.structure.Flow;
import com.data.structure.TeamMember;
import com.google.appengine.api.datastore.Blob;
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
public class Chapter {
    @Id
    Long chapterId;

    String title;
    Blob chapterCover;
    String chapterString;
    String description;
    Date createdDate;
    Date lastModifiedDate;
    Boolean published;

    Key<Flow> theFlow;
    List<Key<PublishedPage>> publishedPageList;
    List<Key<TeamMember>> teamMemberList;
    List<Key<Page>> pageList;

    public Chapter(){
        this.title = "";
        this.chapterCover = null;
        this.chapterString = "";
        this.description = "";
        this.createdDate = new Date();
        this.lastModifiedDate = null;
    }

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

    public List<Key<PublishedPage>> getPublishedPageList() {
        return publishedPageList;
    }

    public void setPublishedPageList(List<Key<PublishedPage>> publishedPageList) {
        this.publishedPageList = publishedPageList;
    }

    public Key<Flow> getTheFlow() {
        return theFlow;
    }

    public void setTheFlow(Key<Flow> theFlow) {
        this.theFlow = theFlow;
    }

    public List<Key<TeamMember>> getTeamMemberList() {
        return teamMemberList;
    }

    public void setTeamMemberList(List<Key<TeamMember>> teamMemberList) {
        this.teamMemberList = teamMemberList;
    }

    public List<Key<Page>> getPageList() {
        return pageList;
    }

    public void setPageList(List<Key<Page>> pageList) {
        this.pageList = pageList;
    }


    public List<PublishedPage> getPublishedPages(){
        Readable<PublishedPage> getPublishedPagesFromPublishedPageKeysAbstracted = new GetEntityListFromKeyListCommand<>(getPublishedPageList());
        List<PublishedPage> publishedPagesList = null;

        try{
            publishedPagesList = getPublishedPagesFromPublishedPageKeysAbstracted.fetch().getList();

        }
        catch(FetchException ex){
            ex.printStackTrace();
        }
        return publishedPagesList;
    }

    public List<TeamMember> getTeamMembers(){
        Readable<TeamMember> getTeamMembersFromTeamMemberKeysAbstracted = new GetEntityListFromKeyListCommand<>(getTeamMemberList());
        List<TeamMember> teamMembersList;
        try{
            teamMembersList = getTeamMembersFromTeamMemberKeysAbstracted.fetch().getList();
        }
        catch(FetchException ex){
            teamMembersList = new ArrayList<>();
            ex.printStackTrace();
        }

        return teamMembersList;
    }

    public List<Page> getPages(){
        Readable<Page> getPagesFromPageKeysAbstracted = new GetEntityListFromKeyListCommand<>(getPageList());
        List<Page> pageList ;
        try{
            pageList = getPagesFromPageKeysAbstracted.fetch().getList();

        }
        catch(FetchException ex){
            pageList = new ArrayList<>();
            ex.printStackTrace();
        }

    return pageList;
    }

    public Key<Chapter> getKey(){
        return Key.create(Chapter.class, chapterId);
    }

    public void addPublishPageToPublishPageList( Key<PublishedPage> publishedPageKey){
        this.publishedPageList.add(publishedPageKey);
    }

    public void addTeamMemberToTeamMemberList( Key<TeamMember> teamMemberKey){
        this.teamMemberList.add(teamMemberKey);
    }

    public void addPagesToPagesList( Key<Page> pageKey){
        this.pageList.add(pageKey);
    }

    public Flow getFlow(){
        Readable<Flow> getFlowFromFlowKey = new GetEntityFromKeyCommand<>( theFlow );

        Flow flow = null;
        try{
            flow = getFlowFromFlowKey.fetch().getResult();
        }
        catch(FetchException ex){
            ex.printStackTrace();
        }

        return flow;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }
}
