package com.data.api.queries.external;


import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.queries.internal.GetEntityListFromKeyListCommand;
import com.data.creation.Chapter;
import com.data.structure.TeamMember;
import com.google.appengine.api.datastore.Query.Filter;

/**
 * Created by Zhenya on 4/20/16.
 */
public class GetTeamMembersOfChapterCommand extends Readable {
    Long chapterId;

    public GetTeamMembersOfChapterCommand( Long chapterId){
        this.chapterId = chapterId;
    }

    public GetTeamMembersOfChapterCommand(String strChaptersId){
        try {
            this.chapterId = Long.parseLong(strChaptersId);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
    }

    // Not Used
    @Override
    protected Filter getFilter() throws FetchException {
        return null;
    }

    @Override
    protected Class getType() {
        return null;
    }

    @Override
    public Container fetch() throws FetchException{
        Readable<Chapter> chapterReadable = new GetChapterByIDCommand(chapterId);
        Chapter chapter = chapterReadable.fetch().getResult();

        Readable<TeamMember> teamMemberReadable = new GetEntityListFromKeyListCommand<>(chapter.getTeamMemberList());
        return teamMemberReadable.fetch();
    }
}
