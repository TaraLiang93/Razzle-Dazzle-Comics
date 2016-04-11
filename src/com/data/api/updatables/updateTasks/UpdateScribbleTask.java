package com.data.api.updatables.updateTasks;

import com.data.api.interfaces.Container;
import com.data.api.interfaces.UpdateTask;
import com.data.creation.Page;
import com.data.creation.Scribble;
import com.googlecode.objectify.Key;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/8/16.
 */
public class UpdateScribbleTask implements UpdateTask<Scribble> {
    String title;
    String description;
    List<Key<Page>> pageList;


    public UpdateScribbleTask(String title, String description, List<Key<Page>> pageList){
        this.title = title;
        this.description = description;
        this.pageList = pageList;
    }

    /**
     *
     * @param entity the container of entity or entities to update
     * @return List<Scribble> a list of entities with updated fields so the
     *          Scribble Updater can call ofy().save().entities(returnedList)
     */
    @Override
    public List<Scribble> update(Container<Scribble> entity) {
        Scribble scribbleToUpdate = entity.getResult();
        scribbleToUpdate.setTitle(this.title);
        scribbleToUpdate.setDescription(this.description);
        scribbleToUpdate.setPageList(this.pageList);

        List<Scribble> scribbleList = new ArrayList<>();
        scribbleList.add(scribbleToUpdate);
        return scribbleList;
    }

}
