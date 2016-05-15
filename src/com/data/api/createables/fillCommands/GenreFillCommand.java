package com.data.api.createables.fillCommands;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.FillDataCommand;
import com.data.structure.Genre;

/**
 * Created by Zhenya on 5/14/16.
 */
public class GenreFillCommand implements FillDataCommand<Genre> {

    public GenreFillCommand(){

    }

    @Override
    public void fillEntity(Genre entity) throws CreateException, FetchException {
        //TODO: nothing yet?
    }
}
