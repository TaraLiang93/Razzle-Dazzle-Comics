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
 * Created by Zhenya on 4/20/16.
 */
public class UpdateChapterRemoveTeamMemberTask implements UpdateTask<Chapter> {
    Long teamMemberId;

    public UpdateChapterRemoveTeamMemberTask( Long teamMemberId){
        this.teamMemberId = teamMemberId;
    }

    public UpdateChapterRemoveTeamMemberTask( String strTeamMemberId){
        try {
            this.teamMemberId = Long.parseLong(strTeamMemberId);
        }
        catch ( NumberFormatException ex){
            ex.printStackTrace();
        }
    }


    @Override
    public List<Chapter> update(Container<Chapter> entity) throws UpdateException, FetchException, CreateException {
        Chapter chapter = entity.getResult();

        if (chapter == null || teamMemberId == null){
            throw new UpdateException("chapter or teamMember ID is null");
        }

        //get TeamMember
        Readable<TeamMember> teamMemberReadable = new GetTeamMemberByIDCommand(teamMemberId);
        TeamMember teamMember = teamMemberReadable.fetch().getResult();

        chapter.getTeamMemberList().remove( teamMember.getKey());
        // TODO: Make sure this actually removes a key from List<Key>


        List<Chapter> chapterList = new ArrayList<>();
        chapterList.add(chapter);

        return chapterList;
    }

}
