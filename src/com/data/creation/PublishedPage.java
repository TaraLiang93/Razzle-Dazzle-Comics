package com.data.creation;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.api.queries.internal.GetEntityFromKeyCommand;
import com.data.api.queries.internal.GetImgUrlFromBlobKey;
import com.google.appengine.api.blobstore.BlobKey;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by drodrigues on 3/30/16.
 */
@Entity
public class PublishedPage {

    @Id
    private Long publishedPageId;

    private PAGE_TYPE type;

    private Key<Canvas> canvas;

    private BlobKey image;

    private int index;


    public PublishedPage(){
        this(null, null, null, 0);
    }

    public PublishedPage(BlobKey image, String index){
        this(image, ((index == null)? -1 : Integer.parseInt(index)));
    }

    public PublishedPage(BlobKey img, int index){
        this(PAGE_TYPE.IMAGE_TYPE, null, img, index);
    }

    public PublishedPage(Canvas canvas, int index){
        this(PAGE_TYPE.CANVAS_TYPE, canvas, null, index);
    }

    public PublishedPage(PAGE_TYPE type, Canvas canvas, BlobKey image, int index){
        if(index == -1){
            System.err.println("There was an error parsing the index");
            index = 0;
        }
        this.type = type;
        this.canvas = (canvas != null) ? canvas.getKey() : null;
        this.image = image;
        this.index = index;
    }

    public String getImage() {
        String url = GetImgUrlFromBlobKey.getURL(image);
        return url;
    }

    public enum PAGE_TYPE{
        CANVAS_TYPE, IMAGE_TYPE
    }



    public BlobKey getImageBlob() {
        return image;
    }

    public void setImageBlob(BlobKey image) {
        this.image = image;
    }

    public Canvas getCanvas() {

        Readable<Canvas> getCanvas = new GetEntityFromKeyCommand<>(canvas);

        Canvas mCanvas = null;
        try{
            mCanvas = getCanvas.fetch().getResult();
        }
        catch(FetchException ex){
            ex.printStackTrace();
        }
        return mCanvas;
    }

    public void setCanvasKey(Canvas canvas) {
        this.canvas = canvas.getKey();
    }

    public Long getId() {
        return publishedPageId;
    }

    public void setId(Long publishedPageId) {
        this.publishedPageId = publishedPageId;
    }

    public PAGE_TYPE getType() {
        return type;
    }

    public void setType(PAGE_TYPE type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Key<PublishedPage> getKey(){
        return Key.create(PublishedPage.class, publishedPageId);
    }

}
