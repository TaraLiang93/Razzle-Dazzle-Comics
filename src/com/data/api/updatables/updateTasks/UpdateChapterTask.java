package com.data.api.updatables.updateTasks;

import com.data.api.createables.PageCreater;
import com.data.api.createables.SceneCreater;
import com.data.api.createables.fillCommands.PageFillCommand;
import com.data.api.createables.fillCommands.SceneFillCommand;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Createable;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.UpdateTask;
import com.data.api.queries.external.GetPageByIDCommand;
import com.data.api.queries.external.GetSceneByIDCommand;
import com.data.creation.Chapter;
import com.data.creation.Page;
import com.data.creation.Scene;
import com.googlecode.objectify.Key;
import com.model.ChapterModel;
import com.model.PageModel;
import com.model.SceneModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/10/16.
 */
public class UpdateChapterTask implements UpdateTask<Chapter> {

    ChapterModel chapterModel;

    public UpdateChapterTask( ChapterModel chapterModel){
        this.chapterModel = chapterModel;
    }


    @Override
    public List<Chapter> update(Container<Chapter> entity) throws UpdateException, FetchException, CreateException {
        if (chapterModel == null) {
            throw new UpdateException();
        }
        Chapter chapterToUpdate = entity.getResult();
        chapterToUpdate.setTitle(chapterModel.getTitle() );
        chapterToUpdate.setChapterString( chapterModel.getChapterString() );
        chapterToUpdate.setDescription( chapterModel.getDescription() );



        List<Key<Page>> pageList = new ArrayList<>();
        for( PageModel pageModel: chapterModel.getPageList()){

            Readable<Page> getPage = new GetPageByIDCommand(pageModel.getId());
            Page page = getPage.fetch().getResult();
            if(page != null){

                List<Key<Scene>> sceneList = new ArrayList<>();
                for(SceneModel sceneModel : pageModel.getScenes()){
                    Readable<Scene> getScene = new GetSceneByIDCommand(sceneModel.getId());
                    Scene scene = getScene.fetch().getResult(); //TODO : Probably should have it's own update task

                    if(scene != null){//We have the scene
                        scene.setSetting(sceneModel.getSetting());
                        scene.setTinyMCEText(sceneModel.getTinyMCEText());
                        scene.setIndex(sceneModel.getIndex());
                        sceneList.add(scene.getKey());
                    }
                    else{ //It's new
                        Createable<Scene> pageCreater = new SceneCreater(sceneModel);
                        Scene sceneCreated = pageCreater.createEntity(new SceneFillCommand());
                        sceneList.add(sceneCreated.getKey());
                    }
                }

                page.setSceneList(sceneList);
                pageList.add(page.getKey());

            }
            else{ // create page if it doesn't exist
                Createable<Page> pageCreater = new PageCreater(pageModel);
                Page pageCreated = pageCreater.createEntity( new PageFillCommand() );
                pageList.add(pageCreated.getKey());

            }

        }





        List<Chapter> listOfChapter = new ArrayList<>();
        listOfChapter.add(chapterToUpdate);
        return listOfChapter;
    }


}
