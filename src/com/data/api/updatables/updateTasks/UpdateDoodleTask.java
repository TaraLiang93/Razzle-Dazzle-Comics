package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.UpdateTask;
import com.data.creation.Doodle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/8/16.
 */
public class UpdateDoodleTask implements UpdateTask<Doodle> {
    String title;
    String description;
    String canvasJSON;

    public UpdateDoodleTask(String title, String description, String canvasJSON){
        this.title = title;
        this.description = description;
        this.canvasJSON = canvasJSON;
    }



    @Override
    public List<Doodle> update(Container<Doodle> entity) throws UpdateException, FetchException{
        Doodle doodleToUpdate = entity.getResult();

        if( doodleToUpdate == null){
            throw new UpdateException("UpdateDoodleTask doodleToUpdate null");
        }

        doodleToUpdate.setTitle(this.title);
        doodleToUpdate.setDescription(this.description);

        try {
            doodleToUpdate.setCanvasJSON(this.canvasJSON);
        } catch (CreateException e) {
            e.printStackTrace();
        }

        // add the doodleToUpdate to a Doodle list because the return type expects a list of Doodles
        List<Doodle> doodleList = new ArrayList<>();
        doodleList.add(doodleToUpdate);

        return doodleList;
    }
}
