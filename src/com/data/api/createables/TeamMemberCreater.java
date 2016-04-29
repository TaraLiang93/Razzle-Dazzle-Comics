package com.data.api.createables;

import com.data.UserData;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Createable;
import com.data.api.interfaces.Readable;
import com.data.api.queries.internal.GetEntityFromKeyCommand;
import com.data.structure.TeamMember;
import com.googlecode.objectify.Key;

/**
 * Created by Zhenya on 4/20/16.
 */
public class TeamMemberCreater extends Createable<TeamMember> {
    Key<UserData> userDataKey; // this is okay because TeamMembers will always be created internally

    public TeamMemberCreater( Key<UserData> userDataKey){
        this.userDataKey = userDataKey;
    }

    @Override
    protected TeamMember getEntity() throws CreateException, FetchException {
        TeamMember teamMember = new TeamMember();
        if(teamMember == null || userDataKey == null){
            throw  new CreateException("team member or user data key is null");
        }

        teamMember.setUserDataKey(userDataKey);

        Readable<UserData> userDataReadable = new GetEntityFromKeyCommand( userDataKey );
        UserData userData = userDataReadable.fetch().getResult();

        teamMember.setUserStringId( userData.getUserId());

        return teamMember;
    }
}
