package com.data.api.createables;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Createable;
import com.data.creation.PublishedPage;

/**
 * Created by drodrigues on 4/27/16.
 */
public class PublishedPageCreater extends Createable<PublishedPage> {

    @Override
    protected PublishedPage getEntity() throws CreateException, FetchException {

        PublishedPage page = new PublishedPage();

        return page;
    }
}
