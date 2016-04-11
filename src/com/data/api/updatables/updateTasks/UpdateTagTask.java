package com.data.api.updatables.updateTasks;

import com.data.api.interfaces.Container;
import com.data.api.interfaces.UpdateTask;
import com.data.structure.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by drodrigues on 4/4/16.
 */
public class UpdateTagTask implements UpdateTask<Tag> {

    private String name;

    public UpdateTagTask(String name){
        this.name = name;
    }

    @Override
    public List<Tag> update(Container<Tag> entity) {
        List<Tag> resultSet = new ArrayList<>();

        Tag tag = entity.getResult();
        tag.setName(name);

        resultSet.add(tag);

        return resultSet;
    }
}
