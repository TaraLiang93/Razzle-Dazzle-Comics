package com.data.api.queries.external;

import com.data.UserData;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.users.User;

/**
 * Created by Zhenya on 4/11/16.
 */
public class GetUserDataByUserCommand extends Readable<UserData> {
    User user;
    public GetUserDataByUserCommand(User user){
        this.user = user;
    }

    @Override
    protected Filter getFilter() throws FetchException {
        if( user == null){
            throw new FetchException("GetUserDataByUserCommand user null");
        }
        Filter filter = new FilterPredicate("userid",
                FilterOperator.EQUAL,
                this.user.getUserId());
        return filter;
    }

    @Override
    protected Class<UserData> getType() {
        return UserData.class;
    }



}
