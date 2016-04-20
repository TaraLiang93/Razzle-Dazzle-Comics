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
import com.data.creation.Chapter;
import com.data.creation.Page;
import com.model.ChapterModel;
import com.model.PageModel;

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
        Chapter chapterToUpdate = entity.getResult(); // get the chapter
        if (chapterToUpdate == null || chapterModel == null) {
            throw new UpdateException( "chapter info is null");
        }

        chapterToUpdate.setTitle(chapterModel.getTitle() );
        chapterToUpdate.setChapterCover(chapterModel.getChapterCover() );
        chapterToUpdate.setChapterString( chapterModel.getChapterString() );
        chapterToUpdate.setDescription( chapterModel.getDescription() );
        chapterToUpdate.setLastModifiedDate( chapterModel.getLastModifiedDate());
        chapterToUpdate.setPublished( chapterModel.getPublished() );

        // make sure the chapterModelPageList isn't null
        if( chapterModel.getPageList() == null){
            throw new UpdateException("Update Chapter Task chapter model is null");
        }


        for( PageModel pageModel: chapterModel.getPageList()){

            if(pageModel.getId() == null || pageModel == null){ // create page if it doesn't exist

                Createable<Page> pageCreater = new PageCreater(pageModel);
                Page pageCreated = pageCreater.createEntity( new PageFillCommand() );
                chapterToUpdate.addPagesToPagesList(pageCreated.getKey());


            }
            else{ // update old Page
                new PageUpdater()
                        .updateEntity(
                                new GetPageByIDCommand(pageModel.getId()),
                                new UpdatePageTask(pageModel)
                        );
            }


        }





        List<Chapter> listOfChapter = new ArrayList<>();
        listOfChapter.add(chapterToUpdate);
        return listOfChapter;
    }


}
