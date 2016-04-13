package com.data.api.updatables.updateTasks;

import com.data.UserData;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.UpdateTask;
import com.data.api.queries.external.GetDoodlesByIDCommand;
import com.data.creation.Doodle;
import com.google.appengine.api.users.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/13/16.
 */
public class UpdateUserDataAddDoodlesTask implements UpdateTask<UserData> {
    Long doodleId;

    public UpdateUserDataAddDoodlesTask( Long doodleID){
        this.doodleId = doodleID;
    }

    public UpdateUserDataAddDoodlesTask(User user, String strId){
        try {
            this.doodleId = Long.parseLong(strId);
        }
        catch ( NumberFormatException ex){
            ex.printStackTrace();
        }

    }

    @Override
    public List<UserData> update(Container<UserData> entity) throws UpdateException, FetchException {
        if(  this.doodleId == null || entity == null){
            throw new UpdateException("UpdateUserDataAddDoodlesTask doodleId or entity is null");
        }

        UserData userData = entity.getResult();
        Readable<Doodle> getDoodleReadable = new GetDoodlesByIDCommand(doodleId);
        Doodle doodleToAdd = getDoodleReadable.fetch().getResult();
        userData.addDoodleToList(doodleToAdd.getKey());

        List<UserData> updateList = new ArrayList<>();
        updateList.add(userData);

        return updateList;
    }


}
