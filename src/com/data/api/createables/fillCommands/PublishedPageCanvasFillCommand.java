package com.data.api.createables.fillCommands;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.FillDataCommand;
import com.data.api.queries.external.GetChapterByIDCommand;
import com.data.creation.Canvas;
import com.data.creation.Chapter;
import com.data.creation.PublishedPage;

/**
 * Created by drodrigues on 4/27/16.
 */
public class PublishedPageCanvasFillCommand implements FillDataCommand<PublishedPage>{

    private String chapterID;
    private Canvas canvas;

    @Override
    public void fillEntity(PublishedPage entity) throws CreateException {

        if(canvas == null || chapterID == null) throw new CreateException("ChapterID and Canvas required!");
        else if(entity == null) throw new CreateException("Error fetching Published Page Entity");

        try {
            Container<Chapter> getChapter = new GetChapterByIDCommand(chapterID).fetch();
            entity.setCanvasKey(canvas);
            entity.setIndex(0);
        } catch (FetchException e) {
            e.printStackTrace();
        }

    }
}
