package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.UpdateTask;
import com.data.creation.Chapter;
import com.google.appengine.api.blobstore.BlobKey;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by drodrigues on 5/13/16.
 */
public class UpdateChapterEditInfoTask implements UpdateTask<Chapter> {


    private BlobKey key;
    private String title, chapterString, description;


    public UpdateChapterEditInfoTask(String title, String chapterString, String description, BlobKey key){
        this.title = title;
        this.chapterString = chapterString;
        this.description = description;
        this.key = key;
    }

    @Override
    public List<Chapter> update(Container<Chapter> entity) throws UpdateException, FetchException, CreateException {

        Chapter chapter = entity.getResult();

        if (chapter == null){
            throw new UpdateException("chapter is null");
        }

        chapter.setTitle(title);
        chapter.setChapterString(chapterString);
        chapter.setDescription(description);


        if(key !=null){
            chapter.setChapterCover(key);
        }

        List<Chapter> chapterList = new ArrayList<>();
        chapterList.add(chapter);

        return chapterList;
    }
}
