package com.data;


/**
 * Created by tara on 2/14/16.
 */

import com.data.creation.Doodle;
import com.data.creation.Scribble;
import com.data.structure.Bookmark;
import com.data.structure.Flow;
import com.data.structure.Series;
import com.data.structure.Tag;
import com.google.appengine.api.datastore.Blob;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Serialize;

import java.util.List;

@Entity
public class UserData implements java.io.Serializable {

    @Id
    Long userid;

    @Index˚
    String userName;
    @Index
    String preferredEmail;
    @Index
    Flow flowId;

    @Serialize
    Blob userImage; // blob type for userImage can't be indexed

    String nickName;
    String location;
    String description;
    List<Series> seriesList;
    List<Scribble> scribbleList;
    List<Tag> tagList;
    List<Bookmark> bookmarkList;
    List<Doodle> doodleList;

    public UserData() {
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPreferredEmail() {
        return preferredEmail;
    }

    public void setPreferredEmail(String preferredEmail) {
        this.preferredEmail = preferredEmail;
    }

    public Flow getFlowId() {
        return flowId;
    }

    public void setFlowId(Flow flowId) {
        this.flowId = flowId;
    }

    public Blob getUserImage() {
        return userImage;
    }

    public void setUserImage(Blob userImage) {
        this.userImage = userImage;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Series> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<Series> seriesList) {
        this.seriesList = seriesList;
    }

    public List<Scribble> getScribbleList() {
        return scribbleList;
    }

    public void setScribbleList(List<Scribble> scribbleList) {
        this.scribbleList = scribbleList;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public List<Bookmark> getBookmarkList() {
        return bookmarkList;
    }

    public void setBookmarkList(List<Bookmark> bookmarkList) {
        this.bookmarkList = bookmarkList;
    }

    public List<Doodle> getDoodleList() {
        return doodleList;
    }

    public void setDoodleList(List<Doodle> doodleList) {
        this.doodleList = doodleList;
    }

}
