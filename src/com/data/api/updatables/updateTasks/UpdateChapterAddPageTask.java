package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.UpdateTask;
import com.data.api.queries.external.GetPageByIDCommand;
import com.data.creation.Chapter;
import com.data.creation.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/15/16.
 */
public class UpdateChapterAddPageTask implements UpdateTask<Chapter> {

    Long pageId;

    public UpdateChapterAddPageTask(Long pageId){
        this.pageId = pageId;
    }

    public UpdateChapterAddPageTask(String strPageId){
        try {
            this.pageId = Long.parseLong(strPageId);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public List<Chapter> update(Container<Chapter> entity) throws UpdateException, FetchException, CreateException {
        Chapter chapter = entity.getResult();

        Readable<Page> pageReader = new GetPageByIDCommand(pageId);
        Page page = pageReader.fetch().getResult();

        chapter.addPagesToPagesList(page.getKey());

        List<Chapter> chapterList = new ArrayList<>();
        chapterList.add(chapter);
        return chapterList;
    }
}
