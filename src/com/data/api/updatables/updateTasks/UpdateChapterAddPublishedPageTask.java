package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.UpdateTask;
import com.data.creation.Chapter;
import com.data.creation.PublishedPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by drodrigues on 4/28/16.
 */
public class UpdateChapterAddPublishedPageTask implements UpdateTask<Chapter> {

    private PublishedPage page;

    public UpdateChapterAddPublishedPageTask(PublishedPage page){
        this.page = page;
    }

    @Override
    public List<Chapter> update(Container<Chapter> entity) throws UpdateException, FetchException, CreateException {

        Chapter chapter = null;

        if(entity == null) throw new FetchException("Error fetching Entity");
        else if(page == null) throw new FetchException("There must be a published page to be added");
        else if((chapter = entity.getResult()) == null) throw new FetchException("There was not chapter in the result");

        chapter.getPublishedPageList().add(page.getKey());

        List<Chapter> chapList = new ArrayList<>();
        chapList.add(chapter);
        return chapList;
    }
}
