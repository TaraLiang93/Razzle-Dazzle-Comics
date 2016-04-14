package com.data.api.updatables.updateTasks;

import com.data.api.interfaces.Updateable;
import com.data.structure.Series;

/**
 * Created by Zhenya on 4/13/16.
 */
public class UpdateSeriesAddChapterTask extends Updateable<Series> {

    Long chapterId;

    public UpdateSeriesAddChapterTask( Long chapterId){
        this.chapterId = chapterId;
    }

    public UpdateSeriesAddChapterTask( String strChapterId){
        try {
            this.chapterId = Long.parseLong(strChapterId);
        }
        catch( NumberFormatException ex){
            ex.printStackTrace();
        }
    }




}
