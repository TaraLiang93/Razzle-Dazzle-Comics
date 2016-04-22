package com.data.api.createables.fillCommands;

import com.data.api.exceptions.CreateException;
import com.data.api.interfaces.FillDataCommand;
import com.data.structure.Series;
import com.google.appengine.api.blobstore.BlobKey;

/**
 * Created by Zhenya on 4/15/16.
 */
public class SeriesFillCommand implements FillDataCommand<Series> {

    BlobKey seriesCover;

    public SeriesFillCommand(BlobKey cover){
        this.seriesCover = cover;
    }

    @Override
    public void fillEntity(Series entity) throws CreateException {
        if(entity == null) throw new CreateException("Entity cannot be Null");

        if(seriesCover !=null){
            entity.setSeriesCover(seriesCover);
        }
    }



}
