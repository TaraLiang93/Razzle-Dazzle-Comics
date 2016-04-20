package com.data.api.updatables.updateTasks;

import com.data.api.createables.PageCreater;
import com.data.api.createables.fillCommands.PageFillCommand;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Createable;
import com.data.api.interfaces.UpdateTask;
import com.data.api.queries.external.GetPageByIDCommand;
import com.data.api.updatables.PageUpdater;
import com.data.creation.Page;
import com.data.creation.Scribble;
import com.model.PageModel;
import com.model.ScribbleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/8/16.
 */
public class UpdateScribbleTask implements UpdateTask<Scribble> {
    ScribbleModel scribbleModel;


    public UpdateScribbleTask(ScribbleModel scribbleModel){
        this.scribbleModel = scribbleModel;
    }

    /**
     *
     * @param entity the container of entity or entities to update
     * @return List<Scribble> a list of entities with updated fields so the
     *          Scribble Updater can call ofy().save().entities(returnedList)
     */
    @Override
    public List<Scribble> update(Container<Scribble> entity) throws UpdateException, FetchException, CreateException {

        Scribble scribbleToUpdate = entity.getResult();;
        if( scribbleToUpdate == null || scribbleModel == null) throw new UpdateException("Scribble Info cannot be null");

        scribbleToUpdate.setTitle(scribbleModel.getTitle());
        scribbleToUpdate.setDescription(scribbleModel.getDescription());

        for( PageModel pageModel: scribbleModel.getPages()){
            //check if Page with ID exists
            Readable<Page> getPage = new GetPageByIDCommand(pageModel.getId());
            Page page = getPage.fetch().getResult();
            if(page != null){

            if(pageModel.getId() == null || pageModel.getId().equals("")){ //New Page
                Createable<Page> pageCreater = new PageCreater(pageModel);
                Page pageCreated = pageCreater.createEntity(new PageFillCommand());
                scribbleToUpdate.getPageList().add(pageCreated.getKey());
            }
            else{//Update the old Page
                new PageUpdater()
                        .updateEntity(
                                new GetPageByIDCommand(pageModel.getId()),
                                new UpdatePageTask(pageModel)
                        );
            }
        }


        List<Scribble> scribbleList = new ArrayList<>();
        scribbleList.add(scribbleToUpdate);
        return scribbleList;
    }

}
