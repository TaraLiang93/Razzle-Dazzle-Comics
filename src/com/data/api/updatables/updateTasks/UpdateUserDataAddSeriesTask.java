package com.data.api.updatables.updateTasks;

import com.data.UserData;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.UpdateTask;
import com.data.structure.Series;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/15/16.
 */
public class UpdateUserDataAddSeriesTask implements UpdateTask<UserData> {

    Series series;

    public UpdateUserDataAddSeriesTask ( Series series){
        this.series = series;

    }

    @Override
    public List<UserData> update(Container<UserData> entity) throws UpdateException, FetchException, CreateException {

        if(this.series == null || entity == null){
            throw new UpdateException("UpdateUserDataAddSeriesTask seriesId or entity is null");
        }

        UserData userData = entity.getResult();

        if(userData == null)throw new UpdateException("User must have Data to continue!");

        //updateUserData
        userData.addSeriesToList(series.getKey());

        List<UserData> updateList = new ArrayList<>();
        updateList.add(userData);

        return updateList;
    }

}
