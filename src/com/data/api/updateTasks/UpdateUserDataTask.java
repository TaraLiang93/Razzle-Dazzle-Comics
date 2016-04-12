package com.data.api.updateTasks;

import com.data.UserData;
import com.data.api.Container;
import com.data.api.UpdateTask;
import com.data.creation.Doodle;
import com.data.creation.Scribble;
import com.data.structure.Bookmark;
import com.data.structure.Flow;
import com.data.structure.Series;
import com.data.structure.Tag;
import com.google.appengine.api.datastore.Blob;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Serialize;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/8/16.
 */
public class UpdateUserDataTask implements UpdateTask<UserData> {

    String nickName;
    String location;
    String description;

    Blob userImage;
    Flow newFlow;
    Series newSeries;
    Scribble newScribble;
    Bookmark newBookMark;
    Doodle newDoodle;
//    List<Key<Tag>> tagList;

    /**
     * Update the nickName, location and description
     * @param nickName
     * @param location
     * @param description
     */
    public UpdateUserDataTask( String nickName, String location, String description){
        this.nickName = nickName;
        this.location = location;
        this.description = description;
    }

    public UpdateUserDataTask( Flow newFlow){
        this.newFlow = newFlow;
    }

    public UpdateUserDataTask( Series newSeries){
        this.newSeries = newSeries;
    }

    public UpdateUserDataTask( Scribble newScribble){
        this.newScribble = newScribble;
    }

    public UpdateUserDataTask( Bookmark newBookMark){
        this.newBookMark = newBookMark;
    }

    public UpdateUserDataTask( Doodle newDoodle){
        this.newDoodle = newDoodle;
    }



    @Override
    public List<UserData> update(Container<UserData> entity) {

        UserData userData = entity.getResult();
        if( this.nickName != null){
            userData.setNickName(this.nickName);
        }
        if( this.location != null){
            userData.setLocation(this.location);
        }
        if( this.description != null){
            userData.setDescription(this.description);
        }
//        if( this.newFlow != null){
//            userData.addFlowToList(this.newFlow.getKey());
//        }
//        if( this.newSeries != null){
//            userData.addSeries(this.newSeries.getKey());
//        }

        if( this.newScribble != null){
            userData.addScribbleToList( this.newScribble.getKey());
        }

//        if(this.newBookMark != null){
//            userData.addBookMarkToList(this.newBookMark.getKey());
//        }

        if( this.newDoodle != null){
            userData.addDoodleToList( this.newDoodle.getKey());
        }

        List<UserData> userDataList = new ArrayList<>();
        userDataList.add(userData);

        return userDataList;
    }


}
