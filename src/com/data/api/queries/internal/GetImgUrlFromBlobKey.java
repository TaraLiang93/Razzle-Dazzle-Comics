package com.data.api.queries.internal;

import com.data.creation.Chapter;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;

/**
 * Created by drodrigues on 4/20/16.
 */
public class GetImgUrlFromBlobKey {

    public static String getURL(BlobKey key){
        if(key == null) return null;

        String returnVal = null;
        try{
            ImagesService imgServ = ImagesServiceFactory.getImagesService();
            ServingUrlOptions url = ServingUrlOptions.Builder.withBlobKey(key);
            returnVal = imgServ.getServingUrl(url);
        }
        catch(Throwable t){
            t.printStackTrace();
            returnVal = Chapter.DEFAULT_IMG;
        }


        System.out.println("Returning Img URL : " + returnVal);

        return returnVal;
    }
}
