package com.data.api.updatables.updateTasks;

import com.data.UserData;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.UpdateTask;

import java.util.List;

/**
 * Created by Zhenya on 4/15/16.
 */
public class UpdateUserDataAddSeriesTask implements UpdateTask<UserData> {

    Long seriesId;

    public UpdateUserDataAddSeriesTask ( Long seriesId){
        this.seriesId = seriesId;

    }

    public UpdateUserDataAddSeriesTask( String strId){
        try {
            this.seriesId = Long.parseLong(strId);
        }
        catch ( NumberFormatException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public List<UserData> update(Container<UserData> entity) throws UpdateException, FetchException, CreateException {
        return null;
    }

}
