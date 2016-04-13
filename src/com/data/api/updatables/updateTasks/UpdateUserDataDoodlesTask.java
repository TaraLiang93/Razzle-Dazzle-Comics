package com.data.api.updatables.updateTasks;

import com.data.UserData;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.UpdateTask;

import java.util.List;

/**
 * Created by Zhenya on 4/13/16.
 */
public class UpdateUserDataDoodlesTask implements UpdateTask<UserData> {


    @Override
    public List<UserData> update(Container<UserData> entity) throws UpdateException, FetchException {
        return null;
    }


}
