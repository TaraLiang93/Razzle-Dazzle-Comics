package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.UpdateTask;
import com.data.creation.Comment;
import com.data.creation.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by drodrigues on 4/27/16.
 */
public class UpdatePageAddCommentTask implements UpdateTask<Page> {

    private Comment comment;

    public UpdatePageAddCommentTask(Comment comment){
        this.comment = comment;
    }

    @Override
    public List<Page> update(Container<Page> entity) throws UpdateException, FetchException, CreateException {

        Page page = entity.getResult();

        if(page == null) throw new FetchException("Could not get page to update with new comment!");

        page.getCommentList().add(comment.getKey());

        List<Page> pages = new ArrayList<>();

        pages.add(page);

        return pages;
    }
}
