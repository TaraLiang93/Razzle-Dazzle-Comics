package com.rdc.create;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.api.queries.external.GetSeriesOfUserDataCommand;
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

    @RequestMapping(value="/create/projectAdminPage", method= RequestMethod.GET)
    public ModelAndView loadPublishPage(HttpSession session, ModelMap map){


        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        map.put("uploadAction",blobstoreService.createUploadUrl(SeriesController.NEW_SERIES));

        try {

            Readable<Series> getUserSeries = new GetSeriesOfUserDataCommand(UserServiceFactory.getUserService().getCurrentUser());
            List<Series> seriesList = getUserSeries.fetch().getList();
            map.put("series",seriesList);

        } catch (FetchException e) {
            e.printStackTrace();
        }


        return new ModelAndView("projectadminpage");
    }



}
