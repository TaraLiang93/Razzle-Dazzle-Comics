package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.UpdateTask;
import com.data.creation.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by drodrigues on 5/17/16.
 */
public class UpdatePageEditSummaryTask implements UpdateTask<Page> {


    private String summary;

    public UpdatePageEditSummaryTask(String summary){
        this.summary = summary;
    }

    @Override
    public List<Page> update(Container<Page> entity) throws UpdateException, FetchException, CreateException {

        Page page = entity.getResult();

        if(page == null) throw new FetchException("Could not get page to update with new summary!");

        if(page != null && !page.equals("")){
            page.setSummary(summary);
        }

        List<Page> pages = new ArrayList<>();
        pages.add(page);
        return pages;
    }
}
