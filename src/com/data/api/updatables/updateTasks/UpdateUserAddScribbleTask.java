package com.data.api.updatables.updateTasks;

import com.data.UserData;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.UpdateTask;
import com.data.creation.Scribble;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by drodrigues on 4/13/16.
 */
public class UpdateUserAddScribbleTask implements UpdateTask<UserData> {

    private Scribble scribble;


    public UpdateUserAddScribbleTask(Scribble scribble){
        this.scribble = scribble;
    }

    @Override
    public List<UserData> update(Container<UserData> data) throws UpdateException, FetchException, CreateException {

        List<UserData> returnVal = null;

        if(scribble != null){


            UserData userData = data.getResult();

            if(userData != null){
                userData.addScribbleToList(scribble.getKey());
                returnVal = new ArrayList<>();
                returnVal.add(userData);
            }
        }
        else{
            throw new UpdateException("User and Scribble must be present!");
        }

        return returnVal;
    }
}
