package com.data.api.createables.fillCommands;

import com.data.api.exceptions.CreateException;
import com.data.api.interfaces.FillDataCommand;

/**
 * Created by Zhenya on 4/15/16.
 */
public class SeriesFillCommand implements FillDataCommand<SeriesFillCommand> {

    public SeriesFillCommand(){

    }

    @Override
    public void fillEntity(SeriesFillCommand entity) throws CreateException {
        //TODO: Nothing right now cause creater takes model and we will alreadys?
    }

}
