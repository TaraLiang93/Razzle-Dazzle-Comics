package com.data.api.updatables.updateTasks;

import com.data.UserData;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.UpdateTask;
import com.data.api.queries.external.GetSeriesByIDCommand;
import com.data.structure.Series;

import java.util.ArrayList;
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
        if(  this.seriesId == null || entity == null){
            throw new UpdateException("UpdateUserDataAddDoodlesTask doodleId or entity is null");
        }

        UserData userData = entity.getResult();

        //get series entity
        Readable<Series> getSeriesReadable = new GetSeriesByIDCommand(seriesId);
        Series seriesToAdd = getSeriesReadable.fetch().getResult();

        //updateUserData
        userData.addSeriesToList(seriesToAdd.getKey());

        List<UserData> updateList = new ArrayList<>();
        updateList.add(userData);

        return updateList;
    }

}
