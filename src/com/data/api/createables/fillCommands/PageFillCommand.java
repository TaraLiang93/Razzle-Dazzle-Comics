package com.data.api.createables.fillCommands;

import com.data.api.exceptions.CreateException;
import com.data.api.interfaces.FillDataCommand;
import com.data.creation.Page;

/**
 * Created by Zhenya on 4/13/16.
 */
public class PageFillCommand implements FillDataCommand<Page> {

    public PageFillCommand(){

    }

    @Override
    public void fillEntity(Page entity) throws CreateException {
        //TODO: Nothing yet?
    }
}
