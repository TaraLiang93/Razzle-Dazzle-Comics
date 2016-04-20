package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.UpdateTask;
import com.data.api.queries.external.GetTeamMemberByIDCommand;
import com.data.creation.Chapter;
import com.data.structure.TeamMember;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/15/16.
 */
public class UpdateChapterAddTeamMemberTask implements UpdateTask<Chapter> {
    Long teamMemberId;

    public UpdateChapterAddTeamMemberTask(Long teamMember){
        this.teamMemberId = teamMember;
    }

    public UpdateChapterAddTeamMemberTask(String strTeamMemberId){
        try {
            this.teamMemberId = Long.parseLong(strTeamMemberId);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public List<Chapter> update(Container<Chapter> entity) throws UpdateException, FetchException, CreateException {
        Chapter chapter = entity.getResult();
        if( this.teamMemberId == null){
            throw new UpdateException("UpdateChapterAddTeamMemberTask teamMemberId null");
        }
        else{
            Readable<TeamMember> getTeamMember = new GetTeamMemberByIDCommand(this.teamMemberId);
            TeamMember teamMember = getTeamMember.fetch().getResult();
            chapter.addTeamMemberToTeamMemberList(teamMember.getKey());
        }

        List<Chapter> chapterList = new ArrayList<>();
        chapterList.add(chapter);

        return chapterList;

    }
}
