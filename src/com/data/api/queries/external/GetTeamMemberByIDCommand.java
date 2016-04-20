package com.data.api.queries.external;

import com.data.api.containers.ResultContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.structure.TeamMember;
import com.google.appengine.api.datastore.Query.Filter;
import com.googlecode.objectify.LoadResult;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 4/15/16.
 */
public class GetTeamMemberByIDCommand extends Readable{
    Long teamMemberId;

    public GetTeamMemberByIDCommand(Long teamMemberId){
        this.teamMemberId = teamMemberId;
    }

    public GetTeamMemberByIDCommand(String teamMemberStrId){
        try {
            this.teamMemberId = Long.parseLong(teamMemberStrId);
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
        return TeamMember.class;
    }

    @Override
    public Container fetch() throws  FetchException{ // TODO : do error validation, throw exceptions. Check OFY response codes
        if( this.teamMemberId == null){
            throw new FetchException("GetTeamMemberByIDCommand teamMemberId null");
        }
        LoadResult<TeamMember> LoadResultOfID = ofy().load().type(getType()).id(this.teamMemberId);
        ResultContainer<TeamMember> resultContainer = new ResultContainer<TeamMember>(LoadResultOfID);
        return resultContainer;
    }

}
