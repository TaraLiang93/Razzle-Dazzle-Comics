package com.data.api.queries.external;

import com.data.UserData;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

/**
 * Created by Zhenya on 4/20/16.
 */
public class GetUserDataByUserNameCommand extends Readable<UserData> {
    String userName;
    public GetUserDataByUserNameCommand( String userName){
        this.userName = userName;
    }

    @Override
    protected Filter getFilter() throws FetchException {
        Filter filter = new FilterPredicate("userName",
                FilterOperator.EQUAL,
                userName);
        return filter;
    }

    @Override
    protected Class<UserData> getType() {
        return UserData.class;
    }


}
