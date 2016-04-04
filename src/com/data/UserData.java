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
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserData implements java.io.Serializable {

    @Id
    Long userid;

    @Index
    String userName;

    @Index
    String preferredEmail;

    @Index
    @Load
    Ref<Flow> flowId;

    @Serialize
    Blob userImage; // blob type for userImage can't be indexed

    @Index
    String nickName;

    String location;
    String description;

    List<Key<Series>> seriesList;
    List<Key<Scribble>> scribbleList;
    List<Key<Tag>> tagList;
    List<Key<Bookmark>> bookmarkList;
    List<Key<Doodle>> doodleList;

    public UserData() {

        seriesList = new ArrayList<Key<Series>>();
        scribbleList = new ArrayList<Key<Scribble>>();
        tagList = new ArrayList<Key<Tag>>();
        bookmarkList = new ArrayList<Key<Bookmark>>();
        doodleList = new ArrayList<Key<Doodle>>();
        userName = "";
        preferredEmail = "";
        nickName = "";
        location = "";
        description = "";

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
        return flowId.get();
    }

    public void setFlowId(Ref<Flow> flowId) {
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

    public List<Key<Series>> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<Key<Series>> seriesList) {
        this.seriesList = seriesList;
    }

    public List<Key<Scribble>> getScribbleList() {
        return scribbleList;
    }

    public void setScribbleList(List<Key<Scribble>> scribbleList) {
        this.scribbleList = scribbleList;
    }

    public List<Key<Tag>> getTagList() {
        return tagList;
    }

    public void setTagList(List<Key<Tag>> tagList) {
        this.tagList = tagList;
    }

    public List<Key<Bookmark>> getBookmarkList() {
        return bookmarkList;
    }

    public void setBookmarkList(List<Key<Bookmark>> bookmarkList) {
        this.bookmarkList = bookmarkList;
    }

    public List<Key<Doodle>> getDoodleList() {
        return doodleList;
    }

    public void setDoodleList(List<Key<Doodle>> doodleList) {
        this.doodleList = doodleList;
    }

    public void addDoodleToList( Key<Doodle> doodle){
        this.doodleList.add(doodle);
    }

    public Key<UserData> getKey() {
        return Key.create(UserData.class, userid);
    }

    public void addTagToList( Key<Tag> tag ){
        this.tagList.add(tag);
    }
}
