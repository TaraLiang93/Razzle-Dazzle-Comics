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
 * Created by Zhenya on 5/16/16.
 */
public class UpdateUserDataRemoveSeriesTask implements UpdateTask<UserData> {

    Long seriesId;

    public UpdateUserDataRemoveSeriesTask( Long seriesId){
        this.seriesId = seriesId;
    }

    public UpdateUserDataRemoveSeriesTask( String seriesId ){
        this.seriesId = Long.parseLong(seriesId);
    }

    @Override
    public List<UserData> update(Container<UserData> entity) throws UpdateException, FetchException, CreateException {

        if( seriesId == null || entity == null){
            throw new UpdateException("UpdateUserDataAddSeriesTask seriesId or entity is null");
        }

        UserData userData = entity.getResult();
        Readable<Series> seriesReadable = new GetSeriesByIDCommand( seriesId );
        Series series = seriesReadable.fetch().getResult();
        //remove series
        userData.getSeriesList().remove(series.getKey());

        List<UserData> updateList = new ArrayList<>();
        updateList.add(userData);

        return updateList;
    }
}
