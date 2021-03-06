package com.rdc.create;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.api.queries.external.GetGenresCommand;
import com.data.api.queries.external.GetSeriesOfUserDataCommand;
import com.data.structure.Genre;
import com.data.structure.Series;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class ProjectAdminPageController {

    public static final String LOAD_ADMIN_PAGE = "/create/projectAdminPage";

    @RequestMapping(value=LOAD_ADMIN_PAGE, method= {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView loadProjectAdminPage(HttpSession session, ModelMap map){


        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        map.put("uploadAction",blobstoreService.createUploadUrl(SeriesController.NEW_SERIES));

        try {

            Readable<Series> getUserSeries = new GetSeriesOfUserDataCommand(UserServiceFactory.getUserService().getCurrentUser());
            List<Series> seriesList = getUserSeries.fetch().getList();
            map.put("series",seriesList);

        } catch (FetchException e) {
            e.printStackTrace();
        }

        Readable<Genre> genreReadable = new GetGenresCommand();
        try {
            List<Genre> genres = genreReadable.fetch().getList();
            map.put("genres",genres);
        } catch (FetchException e) {
            e.printStackTrace();
        }


        return new ModelAndView("projectadminpage");
    }



}
