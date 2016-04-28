package com.rdc.create;

import com.data.api.createables.SeriesCreater;
import com.data.api.createables.fillCommands.SeriesFillCommand;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.Updateable;
import com.data.api.queries.external.GetChaptersOfSeriesCommand;
import com.data.api.queries.external.GetSeriesByIDCommand;
import com.data.api.queries.external.GetUserDataByUserCommand;
import com.data.api.updatables.SeriesUpdater;
import com.data.api.updatables.UserDataUpdater;
import com.data.api.updatables.updateTasks.UpdateSeriesEditDescriptionTask;
import com.data.api.updatables.updateTasks.UpdateUserDataAddSeriesTask;
import com.data.creation.Chapter;
import com.data.structure.Series;
import com.google.appengine.api.blobstore.*;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
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
public class SeriesController {

    public static final String NEW_SERIES = "/create/series/new";

    @RequestMapping(value="/create/series/updateDescription", method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Boolean> updateSeriesDescr(@RequestParam String seriesID, @RequestParam String desc, HttpSession session){

        System.out.println("NEW DESCRIPTION: " + desc);
        Updateable<Series> seriesUpdateableDannyGangsta = new SeriesUpdater();
        Readable<Series> seriesReadableDannyGansta = new GetSeriesByIDCommand(seriesID);
        try {
            seriesUpdateableDannyGangsta.updateEntity( seriesReadableDannyGansta,
                    new UpdateSeriesEditDescriptionTask(desc));
        } catch (FetchException | UpdateException | CreateException e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity(true, HttpStatus.OK);
    }

    @RequestMapping(value="/create/series/updateSeriesImage", method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Boolean> updateSeriesImg(@RequestPart("imgSrc") MultipartFile imgSrc,
                                                                                      HttpSession session){
        System.out.println("New image: "+ imgSrc);

        return new ResponseEntity(true, HttpStatus.OK);

    }


    @RequestMapping(value=NEW_SERIES, method= RequestMethod.POST)
    public ModelAndView addSeries(@RequestParam String title,
                                  @RequestParam String author,
                                  @RequestParam String artist,
                                  @RequestParam String description,
                                  @RequestHeader String referer,
                                  HttpServletRequest req,
                                  ModelMap map){

        String redirect;

        System.out.println("Title : " + title);
        System.out.println("Author : " + author);
        System.out.println("Artist : " + artist);
        System.out.println("Description : " + description);



        System.out.println("Referer: " + referer);

        User user = UserServiceFactory.getUserService().getCurrentUser();


        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
        List<BlobKey> blobKeys = blobs.get("seriesImage");

        if (blobKeys == null) {
            System.out.println("Why you null BlobKeys?");
            redirect = referer; //Go back from whence thee came
        }
        else {

            if (blobKeys.size() == 0) {
                System.out.println("There should be a default Image here");
                redirect = referer;
            } else {
                BlobKey blobKey = blobKeys.get(0); // Get the first one
                BlobInfoFactory blobInfoFactory = new BlobInfoFactory(); //info about img
                BlobInfo info = blobInfoFactory.loadBlobInfo(blobKey);  //info about img
                System.out.println("Content Type : " + info.getContentType());  //info about img
                System.out.println("Image FileName : " + info.getFilename());   //info about img
                BlobKey key = info.getBlobKey();    //the actual blob key to the img for storage

                try {
                    Series series = new SeriesCreater(key, title, description, false).createEntity(new SeriesFillCommand(key));
                    new UserDataUpdater()
                            .updateEntity(
                                    new GetUserDataByUserCommand(user),
                                    new UpdateUserDataAddSeriesTask(series));
                    redirect = "forward:/create/series/load/" + series.getSeriesID();
                } catch (CreateException | FetchException| UpdateException e) {
                    redirect = referer;
                    e.printStackTrace();
                }
            }

        }
        return new ModelAndView(redirect);
    }
    @RequestMapping(value="/create/series/load/{id}", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loadSeries(@PathVariable String id, HttpSession session, ModelMap map){


        try {
            Readable<Series> getSeries = new GetSeriesByIDCommand(id);
            Series series = getSeries.fetch().getResult();


            BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
            map.put("uploadAction",blobstoreService.createUploadUrl(ChapterController.NEW_CHAPTER));


            Readable<Chapter> getChapters = new GetChaptersOfSeriesCommand(series.getSeriesID());
            List<Chapter> chapters = getChapters.fetch().getList();

            map.put("series", series);
            map.put("seriesTitle",series.getTitle());
            map.put("seriesDescription",series.getDescription());
            map.put("seriesIsPublished",series.isPublished());
            map.put("chapters", chapters);
        } catch (FetchException e) {
            e.printStackTrace();
        }

        return new ModelAndView("seriesPage");

    }

}
