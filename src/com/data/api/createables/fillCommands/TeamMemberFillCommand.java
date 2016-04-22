package com.data.api.createables.fillCommands;

import com.data.api.exceptions.CreateException;
import com.data.api.interfaces.FillDataCommand;
import com.data.structure.TeamMember;

/**
 * Created by Zhenya on 4/21/16.
 */
public class TeamMemberFillCommand implements FillDataCommand<TeamMember> {

    public  TeamMemberFillCommand(){}

    @Override
    public void fillEntity(TeamMember entity) throws CreateException {
        // TODO: no work right now
    }


}
