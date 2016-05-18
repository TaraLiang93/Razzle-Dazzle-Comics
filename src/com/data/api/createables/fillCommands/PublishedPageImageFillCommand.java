package com.data.api.createables.fillCommands;

import com.data.api.exceptions.CreateException;
import com.data.api.interfaces.FillDataCommand;
import com.data.creation.PublishedPage;
import com.google.appengine.api.blobstore.BlobKey;

/**
 * Created by drodrigues on 4/28/16.
 */
public class PublishedPageImageFillCommand implements FillDataCommand<PublishedPage> {

    private BlobKey key;
    private int index;

    public PublishedPageImageFillCommand(BlobKey key, int index){
        this.key = key;
        this.index = index;
    }

    @Override
    public void fillEntity(PublishedPage entity) throws CreateException {

        if(entity == null) throw new CreateException("Entity cannot be null");
        else if(key == null || index < 0) throw new CreateException("Key and index are incorrect");

        entity.setIndex(index);
        entity.setImageBlob(key);
        entity.setType(PublishedPage.PAGE_TYPE.IMAGE_TYPE);
    }

}
