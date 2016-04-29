package com.data.api.createables.fillCommands;

import com.data.api.createables.PageCreater;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Createable;
import com.data.api.interfaces.FillDataCommand;
import com.data.creation.Page;
import com.data.creation.Scribble;
import com.googlecode.objectify.Key;
import com.model.PageModel;
import com.model.ScribbleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/5/16.
 */
public class ScribbleFillCommand implements FillDataCommand<Scribble>{
    private ScribbleModel model;


    public ScribbleFillCommand(ScribbleModel model){
        this.model = model;
    }


    /**
     * This method will fill the Scribble entity that is passed in
     * @param entity the entity that the Scribble fill command will fill
     */
    @Override
    public void fillEntity(Scribble entity) throws CreateException, FetchException {

        if(model == null || entity == null) throw new CreateException("Scribble Model should not be null");

        entity.setTitle(model.getTitle());
        entity.setDescription(model.getDescription());

        List<Key<Page>> pageList = new ArrayList<>();
        for( PageModel pageModel: model.getPages()){

            Createable<Page> pageCreater = new PageCreater();
            Page pageCreated = pageCreater.createEntity( new PageModelFillCommand(pageModel));
            pageList.add(pageCreated.getKey());
        }

        entity.setPageList(pageList);
    }
}
