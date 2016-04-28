package com.rdc.create;

import com.data.api.createables.ChapterCreater;
import com.data.api.createables.PublishedPageCreater;
import com.data.api.createables.fillCommands.ChapterFillCommand;
import com.data.api.createables.fillCommands.PublishedPageImageFillCommand;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.queries.external.GetSeriesByIDCommand;
import com.data.api.updatables.ChapterUpdater;
import com.data.api.updatables.SeriesUpdater;
import com.data.api.updatables.updateTasks.UpdateChapterAddPublishedPageTask;
import com.data.api.updatables.updateTasks.UpdateSeriesAddChapterTask;
import com.data.creation.Chapter;
import com.data.creation.PublishedPage;
import com.data.structure.Series;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @RequestMapping(value=PUBLISH_UPLOADS, method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView publishUploads(@RequestParam String seriesID,
                                       @RequestParam String chapterTitle,
                                       @RequestParam String chapterStr,
                                       @RequestParam String artist,
                                       @RequestParam String author,
                                       @RequestParam String chapterDescr,
                                       @RequestHeader String referer,
                                       HttpServletRequest req,
                                       ModelMap map){

        User user = UserServiceFactory.getUserService().getCurrentUser();

        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);

        BlobKey chapterIcon = blobs.remove("chapterIcon").get(0); //Remove the Chapter Icon, whats left is files numbered 1...n

        List<String> newList = new ArrayList<>(blobs.keySet());
        Collections.sort(newList);

        try{

            Chapter newChapter = new ChapterCreater(user, chapterTitle, chapterStr, chapterDescr).createEntity(new ChapterFillCommand(chapterIcon));

            for(int i=0; i <newList.size();i++){
                String key = newList.get(i);
                BlobKey img = blobs.get(key).get(0);
                PublishedPage newPage = new PublishedPageCreater().createEntity(new PublishedPageImageFillCommand(img, i));
                new ChapterUpdater().updateEntity(newChapter, new UpdateChapterAddPublishedPageTask(newPage));
            }

            Container<Series> getSeries = new GetSeriesByIDCommand(seriesID).fetch();
            Series series = getSeries.getResult();
            new SeriesUpdater().updateEntity(series, new UpdateSeriesAddChapterTask(newChapter));

            System.out.println("Chapter successfully created and Chapter Added");

            map.put("series", series);

        } catch (CreateException | FetchException | UpdateException e) {
            e.printStackTrace();
            return new ModelAndView("redirect:"+referer);
        }



        return new ModelAndView("redirect:/create/series/load/" + seriesID);
    }





}
