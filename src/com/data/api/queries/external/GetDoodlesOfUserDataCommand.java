package com.data.api.queries.external;

import com.data.UserData;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.queries.internal.GetEntityListFromKeyListCommand;
import com.data.creation.Doodle;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.users.User;

/**
 * Created by Zhenya on 4/13/16.
 */
public class GetDoodlesOfUserDataCommand extends Readable {
    User user;
    public GetDoodlesOfUserDataCommand( User user){
        this.user = user;
    }

    @Override
    protected Filter getFilter() throws FetchException {
        return null;
    }

    @Override
    protected Class getType() {
        return Doodle.class;
    }

    @Override
    public Container fetch() throws FetchException{
        Readable<UserData> getUserData = new GetUserDataByIDCommand(user.getUserId());
        UserData userData = getUserData.fetch().getResult();
        Readable<Doodle> getDoodlesFromDoodleKeysAbtracted = new GetEntityListFromKeyListCommand<>(userData.getDoodleList());
        return getDoodlesFromDoodleKeysAbtracted.fetch();
    }

}
