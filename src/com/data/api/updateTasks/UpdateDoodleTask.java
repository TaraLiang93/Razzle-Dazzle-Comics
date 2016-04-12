package com.data.api.updateTasks;

import com.data.api.Container;
import com.data.api.UpdateTask;
import com.data.api.updatables.DoodleUpdater;
import com.data.creation.Canvas;
import com.data.creation.Doodle;
import com.data.structure.Tag;
import com.googlecode.objectify.Key;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/8/16.
 */
public class UpdateDoodleTask implements UpdateTask<Doodle> {
    String title;
    String description;
    String canvasJSON;
//    List<Key<Tag>> taglist;

    public UpdateDoodleTask(String title, String description, String CanvasJSON){
        this.title = title;
        this.description = description;
        this.canvasJSON = canvasJSON;
    }

    @Override
    public List<Doodle> update(Container<Doodle> entity) {
        Doodle doodleToUpdate = entity.getResult();
        doodleToUpdate.setTitle(this.title);
        doodleToUpdate.setDescription(this.description);
//        doodleToUpdate.getCanvas().setJSONString(canvasJSON); // get canvas object set JSOn string
//        doodleToUpdate.setTagList( taglist);

        // add the doodleToUpdate to a Doodle list because the return type expects a list of Doodles
        List<Doodle> doodleList = new ArrayList<>();
        doodleList.add(doodleToUpdate);


        return doodleList;
    }
}
