package com.data.api.queries.external;


import com.data.api.containers.EntityContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.creation.Chapter;
import com.data.creation.Page;
import com.data.creation.Scene;
import com.data.structure.Series;
import com.google.appengine.api.datastore.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by drodrigues on 5/17/16.
 */
public class GetSortedScenesFromChapterCommand extends Readable {

    private String chapterID;

    public GetSortedScenesFromChapterCommand(String chapterID){
        this.chapterID = chapterID;
    }

    @Override
    protected Query.Filter getFilter() throws FetchException {
        return null;
    }

    @Override
    protected Class getType() {
        return Series.class;
    }

    @Override
    public Container fetch() throws FetchException{

        Container<Chapter> getChapter = new GetChapterByIDCommand(chapterID).fetch();
        Chapter chapter = getChapter.getResult();

        List<Scene> sceneList = new ArrayList<>();

        Container<Page> getPages = new GetPagesInDoneFlowTaskCommand(chapterID).fetch();
        List<Page> pages = getPages.getList();

        Collections.sort(pages, new Comparator<Page>() {
            @Override
            public int compare(Page o1, Page o2) {
                return o1.getIndex() - o2.getIndex();
            }
        });

        for(Page page : pages){

            List<Scene> scenes = page.getScenes();
            Collections.sort(scenes, new Comparator<Scene>() {
                @Override
                public int compare(Scene o1, Scene o2) {
                    return o1.getIndex() - o2.getIndex();
                }
            });

            for(Scene scene : scenes){
                sceneList.add(scene);
            }

        }

        Container<List<Scene>> sceneContainer  = new EntityContainer<List<Scene>>(sceneList);

        return sceneContainer;

    }
}
