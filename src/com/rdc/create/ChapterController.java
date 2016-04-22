package com.rdc.create;

import com.data.api.createables.ChapterCreater;
import com.data.api.createables.fillCommands.ChapterFillCommand;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.Updateable;
import com.data.api.queries.external.GetChapterByIDCommand;
import com.data.api.queries.external.GetSeriesByIDCommand;
import com.data.api.updatables.ChapterUpdater;
import com.data.api.updatables.SeriesUpdater;
import com.data.api.updatables.updateTasks.UpdateChapterAddTeamMemberTask;
import com.data.api.updatables.updateTasks.UpdateChapterRemoveTeamMemberTask;
import com.data.api.updatables.updateTasks.UpdateSeriesAddChapterTask;
import com.data.creation.Chapter;
import com.google.appengine.api.blobstore.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class ChapterController {

    public static final String NEW_CHAPTER = "/create/chapter/new";
    public static final String LOAD_CHAPTER = "/create/chapter/load/{id}";
    public static final String LOAD_NEW_CHAPTER = "/create/chapter/new/load";


    @RequestMapping(value = LOAD_NEW_CHAPTER, method = RequestMethod.GET)
    public String getImageUploadUrl(ModelMap map) {
        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        map.put("uploadAction",blobstoreService.createUploadUrl(NEW_CHAPTER));
        return "newChapterModal";
    }

    @RequestMapping(value = LOAD_CHAPTER, method = RequestMethod.GET)
    public ModelAndView loadChapter(@PathVariable String id, @RequestHeader String referer, ModelMap map) {

        try {
            Container<Chapter> chapterContainer = new GetChapterByIDCommand(id).fetch();
            Chapter chapter = chapterContainer.getResult();
            map.put("chapter", chapter);
            map.put("chapterId", id);
        } catch (FetchException e) {
            e.printStackTrace();
            return new ModelAndView(referer);
        }

        return new ModelAndView("chapterPage");
    }

    @RequestMapping(value="/create/chapter/addMember", method = RequestMethod.POST)
    public boolean addTeam(@RequestParam String chapterID, @RequestParam String newMemeber, HttpSession session){
        try{
            Updateable<Chapter> chapterUpdater = new ChapterUpdater();
            Readable<Chapter> chapterReadable = new GetChapterByIDCommand(chapterID);
            chapterUpdater.updateEntity(chapterReadable, new UpdateChapterAddTeamMemberTask(newMemeber));
        } catch(UpdateException | FetchException | CreateException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @RequestMapping(value="/create/chapter/removeMember", method = RequestMethod.POST)
    public boolean removeTeamMember(@RequestParam String chapterID, @RequestParam String removeMember, HttpSession session){
        try{
            Updateable<Chapter> chapterUpdater = new ChapterUpdater();
            Readable<Chapter> chapterReadable = new GetChapterByIDCommand(chapterID);
            chapterUpdater.updateEntity(chapterReadable, new UpdateChapterRemoveTeamMemberTask(removeMember));
        } catch(UpdateException | FetchException | CreateException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @RequestMapping(value=NEW_CHAPTER, method= RequestMethod.POST)
    public ModelAndView addChapter(@RequestParam String chapterID,
                                   @RequestParam String title,
                                   @RequestParam String description,
                                   @RequestParam String flow,
                                   @RequestParam String seriesID,
                                   @RequestHeader String referer,
                                   HttpServletRequest req,
                                   ModelMap map){

        String redirect = "chapterPage";

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
                    Chapter chapter = new ChapterCreater(title, chapterID, description).createEntity(new ChapterFillCommand(key));
                    new SeriesUpdater()
                            .updateEntity(
                                          new GetSeriesByIDCommand(seriesID),
                                          new UpdateSeriesAddChapterTask(chapter));
                    map.put("chapter", chapter);
                    redirect="redirect:/create/chapter/load/" + chapter.getChapterId();
                } catch (CreateException | FetchException | UpdateException e) {
                    redirect = referer;
                    e.printStackTrace();
                }
            }

        }




        return new ModelAndView(redirect);
    }

    @RequestMapping(value="/create/chapter/updateChapterImage", method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Boolean> updateSeriesImg(@RequestPart("imgSrc") MultipartFile imgSrc,
                                                   HttpSession session){
        System.out.println("New image: "+ imgSrc);

        return new ResponseEntity(true, HttpStatus.OK);

    }

}
