package com.data.api.queries.external;

import com.data.UserData;
import com.data.api.containers.ResultContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.users.User;
import com.googlecode.objectify.LoadResult;

import static com.googlecode.objectify.ObjectifyService.ofy;

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

    @Override
    public Container fetch() throws  FetchException{ // TODO : do error validation, throw exceptions. Check OFY response codes
        if(user == null){
            throw new FetchException("user is null");
        }
        String userID = user.getUserId();

        if( userID == null || userID.equals("") ){
            throw new FetchException("GetUserDataByUserCommand userID null");
        }

        LoadResult<UserData> LoadResultOfID = ofy().load().type(getType()).id(userID);

        ResultContainer<UserData> resultContainer = new ResultContainer<>(LoadResultOfID);

        return resultContainer;

    }


}
