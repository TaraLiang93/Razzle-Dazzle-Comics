package com.data.api.queries.internal;

import com.data.UserData;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.structure.TeamMember;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.googlecode.objectify.Key;

/**
 * Created by Zhenya on 4/21/16.
 */
public class GetTeamMemberWithUserDataKeyCommand extends Readable<TeamMember> {
    Key<UserData> userDataKey;

    public GetTeamMemberWithUserDataKeyCommand(Key<UserData> userDataKey){
        this.userDataKey = userDataKey;
    }

    @Override
    protected Filter getFilter() throws FetchException {
        Filter filter;

        filter = new FilterPredicate("userDataKey",
                FilterOperator.EQUAL,
                this.userDataKey);

        return filter;
    }

    @Override
    protected Class<TeamMember> getType() {
        return TeamMember.class;
    }

}
