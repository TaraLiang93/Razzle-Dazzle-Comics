package com.data.api.queries.external;

import com.data.UserData;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.queries.internal.GetEntityListFromKeyListCommand;
import com.data.creation.Chapter;
import com.data.creation.Doodle;
import com.google.appengine.api.datastore.Query.Filter;

/**
 * Created by Zhenya on 4/13/16.
 */
public class GetChaptersOfSeriesCommand extends Readable {

    Long seriesId;
    public GetChaptersOfSeriesCommand (Long seriesId){
        this.seriesId = seriesId;
    }

    public GetChaptersOfSeriesCommand(String strSeriesId){
        try {
            this.seriesId = Long.parseLong(strSeriesId);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected Filter getFilter() throws FetchException {
        return null;
    }

    @Override
    protected Class getType() {
        return Chapter.class;
    }

    @Override
    public Container fetch() throws FetchException{
        Readable<UserData> getUserData = new GetSeriesByIDCommand(user.getUserId());
        UserData userData = getUserData.fetch().getResult();
        Readable<Doodle> getDoodlesFromDoodleKeysAbtracted = new GetEntityListFromKeyListCommand<>(userData.getDoodleList());
        return getDoodlesFromDoodleKeysAbtracted.fetch();
    }

}
