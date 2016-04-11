package com.data.api.updatables.updateTasks;

import com.data.api.interfaces.Container;
import com.data.api.interfaces.UpdateTask;
import com.data.creation.Chapter;
import com.data.creation.Page;
import com.data.creation.PublishedPage;
import com.data.structure.Flow;
import com.data.structure.TeamMember;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/10/16.
 */
public class UpdateChapterTask implements UpdateTask<Chapter> {
    PublishedPage publishedPage;
    Flow flow;
    TeamMember teamMember;
    Page page;

    public UpdateChapterTask(PublishedPage publishedPage){
        this.publishedPage = publishedPage;
    }

    public UpdateChapterTask(Flow flow){
        this.flow = flow;
    }

    public UpdateChapterTask(TeamMember teamMember){
        this.teamMember = teamMember;
    }

    public UpdateChapterTask(Page page){
        this.page = page;
    }

    @Override
    public List<Chapter> update(Container<Chapter> entity) {
        Chapter chapter = entity.getResult();

        if( this.publishedPage != null){
            chapter.addPublishPageToPublishPageList(this.publishedPage.getKey());
        }

        if( this.flow != null){
            chapter.setTheFlow(flow.getKey());
        }

        if(this.teamMember != null){
            chapter.addTeamMemberToTeamMemberList(this.teamMember.getKey());
        }

        if( this.page != null){
            chapter.addPagesToPagesList( this.page.getKey());
        }
        List<Chapter> listOfChapter = new ArrayList<>();
        listOfChapter.add(chapter);
        return listOfChapter;
    }


}
