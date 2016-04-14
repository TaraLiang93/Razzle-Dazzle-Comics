package com.data.api.createables;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Createable;
import com.data.structure.Series;
import com.google.appengine.api.users.User;

/**
 * Created by Zhenya on 4/14/16.
 */
public class SeriesCreater extends Createable<Series> {
    User user;

    @Override
    protected Series getEntity() throws CreateException, FetchException {
        return null;
    }
}
