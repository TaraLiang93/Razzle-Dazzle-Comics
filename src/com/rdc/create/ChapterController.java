package com.rdc.create;

import com.data.api.createables.ChapterCreater;
import com.data.api.createables.fillCommands.ChapterFillCommand;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.queries.external.GetSeriesByIDCommand;
import com.data.api.updatables.SeriesUpdater;
import com.data.api.updatables.updateTasks.UpdateSeriesAddChapterTask;
import com.data.creation.Chapter;
import com.google.appengine.api.blobstore.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class ChapterController {

    public static final String NEW_CHAPTER = "/create/chapter/new";
    public static final String LOAD_NEW_CHAPTER = "/create/chapter/new/load";


    @RequestMapping(value = LOAD_NEW_CHAPTER, method = RequestMethod.GET)
    public String getImageUploadUrl(ModelMap map) {
        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        map.put("uploadAction",blobstoreService.createUploadUrl(NEW_CHAPTER));
        return "newChapterModal";
    }


    @RequestMapping(value=NEW_CHAPTER, method= RequestMethod.POST)
    public ModelAndView addChapter(@RequestParam String id,
                                   @RequestParam String title,
                                   @RequestParam String description,
                                   @RequestParam String flow,
                                   @RequestParam String seriesID,
                                   @RequestHeader String referer,
                                   HttpServletRequest req,
                                   ModelMap map){

        String redirect = "testAddChapter";

        System.out.println("ID : " + id);
        System.out.println("Title : " + title);
        System.out.println("Description : " + description);
        System.out.println("Flow : " + flow);
        System.out.println("Series ID : " + seriesID);

        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
        List<BlobKey> blobKeys = blobs.get("chapterImage");

        if (blobKeys == null) {
            System.out.println("Why you null BlobKeys?");
            redirect = referer; //Go back from whence thee came
        }
        else {
            if(blobKeys.size() == 0){
                System.out.println("There should be a default Image here");
                redirect = referer;
            }
            else{

                    BlobKey blobKey = blobKeys.get(0); // Get the first one
                    BlobInfoFactory blobInfoFactory = new BlobInfoFactory();
                    BlobInfo info = blobInfoFactory.loadBlobInfo(blobKey);
                    System.out.println("Content Type : "+info.getContentType());
                    System.out.println("Image FileName : "+info.getFilename());
                    BlobKey key = info.getBlobKey();

                try {
                    Chapter chapter = new ChapterCreater(title, id, description).createEntity(new ChapterFillCommand(key));
                    new SeriesUpdater()
                            .updateEntity(
                                          new GetSeriesByIDCommand(seriesID),
                                          new UpdateSeriesAddChapterTask(chapter));
                    map.put("chapterCover", chapter.getChapterCover());
                } catch (CreateException e) {
                    redirect = referer;
                    e.printStackTrace();
                } catch (FetchException e) {
                    redirect = referer;
                    e.printStackTrace();
                } catch (UpdateException e) {
                    redirect = referer;
                    e.printStackTrace();
                }
            }

        }




        return new ModelAndView(redirect);
    }

}
