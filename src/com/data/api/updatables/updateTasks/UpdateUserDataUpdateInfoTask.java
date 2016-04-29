package com.data.api.updatables.updateTasks;

import com.data.UserData;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.UpdateTask;
import com.google.appengine.api.blobstore.BlobKey;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by drodrigues on 4/29/16.
 */
public class UpdateUserDataUpdateInfoTask implements UpdateTask<UserData> {

    private BlobKey key;
    private String nickName;
    private String location;
    private String description;

    public UpdateUserDataUpdateInfoTask(String nickName, String description, String location){
        this(nickName, description, location, null);
    }

    public UpdateUserDataUpdateInfoTask(String nickName, String description, String location, BlobKey key){
        this.key = key;
        this.description = description;
        this.nickName = nickName;
        this.location = location;
    }

    @Override
    public List<UserData> update(Container<UserData> entity) throws UpdateException, FetchException, CreateException {

        UserData data = null;

        if(entity == null) throw new FetchException("Error getting entity");
        else if((data = entity.getResult()) == null) throw new FetchException("Could not fetch UserData from Result");

        data.setDescription(description);
        data.setLocation(location);
        data.setNickName(nickName);

        if(key !=null)
            data.setUserImage(key);

        List<UserData> newData = new ArrayList<>();
        newData.add(data);

        return newData;
    }
}
