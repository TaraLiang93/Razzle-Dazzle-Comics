package com.rdc.create;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class PublishController {


    public static final String PUBLISH_PAGE="/create/publish/canvas";
    public static final String PUBLISH_UPLOADS="/create/publish/uploads";

    @RequestMapping(value=PUBLISH_PAGE, method= RequestMethod.GET)
    public ModelAndView publishChapter(@RequestParam String chapterID,
                                       HttpServletRequest req,
                                       ModelMap map){






        return new ModelAndView("homepage");
    }

    @RequestMapping(value=PUBLISH_UPLOADS, method= RequestMethod.GET)
    public ModelAndView publishUploads(@RequestParam String seriesID,
                                       @RequestParam String chapterTitle,
                                       @RequestParam String chapterStr,
                                       @RequestParam String artist,
                                       @RequestParam String author,
                                       @RequestParam String chapterDescr,
                                       HttpServletRequest req,
                                       ModelMap map){

//chapterIcon name of image

        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);

        BlobKey chapterIcon = blobs.remove("chapterIcon").get(0); //Remove the Chapter Icon, whats left is files numbered 1...n

        List<String> newList = new ArrayList<>(blobs.keySet());
        Collections.sort(newList);




        return new ModelAndView("homepage");
    }





}
