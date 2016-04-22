package com.data.api.queries.internal;

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

        ImagesService imgServ = ImagesServiceFactory.getImagesService();
        ServingUrlOptions url = ServingUrlOptions.Builder.withBlobKey(key);
        String imgUrl = imgServ.getServingUrl(url);

        System.out.println("Returning Img URL : " + imgUrl);

        return imgUrl;
    }
}
