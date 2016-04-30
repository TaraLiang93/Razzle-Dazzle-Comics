package com.rdc.create;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.queries.external.*;
import com.data.creation.Chapter;
import com.data.creation.Doodle;
import com.data.creation.Scribble;
import com.data.structure.Series;
import com.data.structure.TeamMember;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class CreateController {

    public static final String CHAPTER_HOME = "/create";


    @RequestMapping(value = CHAPTER_HOME, method = RequestMethod.GET)
    public ModelAndView loadChapter(@RequestHeader String referer, ModelMap map) {
        User user = UserServiceFactory.getUserService().getCurrentUser();

        if(user == null) return new ModelAndView("/logout");

        List<Doodle> doodles = new LinkedList<>();
        Readable<Doodle> getUserDoodles = new GetDoodlesOfUserDataCommand(user);
        try {
            List<Doodle> doodleList = getUserDoodles.fetch().getList();
            map.put("doodles", doodleList);
        } catch (FetchException e) {
            e.printStackTrace();
        }

        List<Scribble> scribbles = new LinkedList<>();
        Readable<Scribble> getUserScribbles = new GetScribblesOfUserDataCommand(user);
        try {
            List<Scribble> scribbleList = getUserScribbles.fetch().getList();
            map.put("scribbles", scribbleList);
        } catch (FetchException e) {
            e.printStackTrace();
        }


        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        map.put("uploadAction",blobstoreService.createUploadUrl(SeriesController.NEW_SERIES));

        try {

            Readable<Series> getUserSeries = new GetSeriesOfUserDataCommand(UserServiceFactory.getUserService().getCurrentUser());
            List<Series> seriesList = getUserSeries.fetch().getList();
            map.put("series",seriesList);

        } catch (FetchException e) {
            e.printStackTrace();
        }


        return new ModelAndView("createHome");
    }
}
