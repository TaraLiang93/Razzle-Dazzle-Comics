package com.data.api.queries.external;

import com.data.UserData;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.queries.internal.GetEntityListFromKeyListCommand;
import com.data.structure.Series;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.users.User;

/**
 * Created by Zhenya on 4/15/16.
 */
public class GetSeriesOfUserDataCommand extends Readable {
    User user;

    public GetSeriesOfUserDataCommand( User user){
        this.user = user;
    }

    @Override
    protected Filter getFilter() throws FetchException {
        return null;
    }

    @Override
    protected Class getType() {
        return Series.class;
    }

    @Override
    public Container fetch() throws FetchException{

        Readable<UserData> getUserData = new GetUserDataByIDCommand(user.getUserId());
        UserData userData = getUserData.fetch().getResult();

        Readable<Series> getSeriesFromSeriesKeysAbtracted = new GetEntityListFromKeyListCommand<>( userData.getSeriesList() );

        return getSeriesFromSeriesKeysAbtracted.fetch();

    }

}
