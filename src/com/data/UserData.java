package com.data;


/**
 * Created by tara on 2/14/16.
 */

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.api.queries.internal.GetEntityListFromKeyListCommand;
import com.data.api.queries.internal.GetImgUrlFromBlobKey;
import com.data.creation.Doodle;
import com.data.creation.Scribble;
import com.data.structure.Bookmark;
import com.data.structure.Flow;
import com.data.structure.Series;
import com.data.structure.Tag;
import com.google.appengine.api.blobstore.BlobKey;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserData implements java.io.Serializable {

    @Ignore
    private static final String DEFAULT_IMG = "/img/profile_default.png";


    @Id @Index
    String userId;

    @Index
    String userName; // username is the email in googles User object


//    @Index
//    @Load
//    Ref<Flow> flowId;


    @Serialize
    BlobKey userImage; // blob type for userImage can't be indexed

    @Index
    String nickName;

    String location;
    String description;

    List<Key<Flow>> flowList;
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
        userId = "";
        userName = "";
        nickName = "";
        location = "";
        description = "";

        //TODO: Get default flows
        //TODO: Do a query for default flows
        List<Key<Flow>> defaultFlowkeys = new ArrayList<Key<Flow>>(); //TODO : Move flow list inside creating UserData
        this.setFlowList(defaultFlowkeys);

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userid) {
        this.userId = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Key<Flow>> getFlowList() {
        return flowList;
    }

    public void setFlowList(List<Key<Flow>> flowList) {
        this.flowList = flowList;
    }

    public BlobKey getUserImageBlob() {
        return userImage;
    }

    public String getUserImage(){
        String url = (userImage == null)? DEFAULT_IMG : GetImgUrlFromBlobKey.getURL(userImage);
        return url;
    }

    public void setUserImage(BlobKey userImage) {
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

    public List<Series> getSeries(){
        Readable<Series> getSeries = new GetEntityListFromKeyListCommand<>(getSeriesList());
        List<Series> series = null;
        try {
            series = getSeries.fetch().getList();
        }
        catch (FetchException ex){
            series = new ArrayList<>();
        }
        return series;
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
        return Key.create(UserData.class, userId);
    }

    public void addTagToList( Key<Tag> tag ){
        this.tagList.add(tag);
    }

    public List<Scribble> getScribbles(){

        Readable<Scribble> getScribblesFromScribbleKeysAbstracted = new GetEntityListFromKeyListCommand<>(getScribbleList());
        List<Scribble> scribbleList = null;
        try {
            scribbleList = getScribblesFromScribbleKeysAbstracted.fetch().getList();
        }
        catch (FetchException ex){
            scribbleList = new ArrayList<>();
        }
        System.out.println("Gotten scribbles");
        return scribbleList;
    }

    public List<Doodle> getDoodles(){
        Readable<Doodle> getDoodlesFromDoodleKeysAbtracted = new GetEntityListFromKeyListCommand<>(getDoodleList());
        List<Doodle> doodleList = null;
        try {
            doodleList = getDoodlesFromDoodleKeysAbtracted.fetch().getList();
        }
        catch (FetchException ex){
            doodleList = new ArrayList<>();
        }
        return doodleList;
    }

    public List<Tag> getTags(){
        Readable<Tag> getTagsFromTagsKeysAbstracted = new GetEntityListFromKeyListCommand<>(getTagList());
        List<Tag> tagList = null;
        try {
            tagList = getTagsFromTagsKeysAbstracted.fetch().getList();
        }
        catch (FetchException ex){
            tagList = new ArrayList<>();
        }
        return tagList;
    }

    public List<Flow> getFlows(){
        Readable<Flow> getFlowsFromFlowKeysAbstracted = new GetEntityListFromKeyListCommand<>(getFlowList());
        List<Flow> flowList = null;
        try {
            flowList = getFlowsFromFlowKeysAbstracted.fetch().getList();
        }
        catch (FetchException ex){
            flowList = new ArrayList<>();
        }
        return flowList;
    }

    public void addScribbleToList( Key<Scribble> scribble ){
        this.scribbleList.add(scribble);
    }

    public void addFlowToList( Key<Flow> flow ) {
        this.flowList.add(flow);
    }

    public void addSeriesToList( Key<Series> seriesKey){
        this.seriesList.add(seriesKey);
    }
}
