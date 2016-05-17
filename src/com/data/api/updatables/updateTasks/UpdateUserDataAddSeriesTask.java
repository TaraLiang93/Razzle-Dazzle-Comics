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

    Series series = null;
    Long seriesID = null;

    public UpdateUserDataAddSeriesTask ( Series series){
        this.series = series;
    }

    public UpdateUserDataAddSeriesTask (String seriesID){
        try{
            this.seriesID = Long.parseLong( seriesID);
        }
        catch ( NumberFormatException e){
            e.printStackTrace();
        }
    }

    public UpdateUserDataAddSeriesTask( Long seriesID){
        this.seriesID = seriesID;
    }


    @Override
    public List<UserData> update(Container<UserData> entity) throws UpdateException, FetchException, CreateException {

        if( (this.series == null && seriesID == null) || entity == null){
            throw new UpdateException("UpdateUserDataAddSeriesTask seriesId or entity is null");
        }

        UserData userData = entity.getResult();

        if(userData == null)throw new UpdateException("User must have Data to continue!");

        if( series != null) {
            //updateUserData
            userData.addSeriesToList(series.getKey());

        }
        else{
            Readable<Series> seriesReadable = new GetSeriesByIDCommand( seriesID );
            Series series = seriesReadable.fetch().getResult();
            userData.addSeriesToList(series.getKey());
        }


        List<UserData> updateList = new ArrayList<>();
        updateList.add(userData);

        return updateList;
    }

}
