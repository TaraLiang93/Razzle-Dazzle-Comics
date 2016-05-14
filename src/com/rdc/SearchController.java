package com.rdc;

import com.data.api.queries.external.GetSeriesOfUserDataCommand;
import com.data.structure.Series;
import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class SearchController {

    @RequestMapping(value="/read/search", method= RequestMethod.POST)
    public ModelAndView loadIdeaFactory(HttpServletRequest req, ModelMap map){


        String searchBar = req.getParameter("searchBar");

        map.put("searchTitle",searchBar);

        try {
            GetSeriesOfUserDataCommand seriesFromUser =
                    new GetSeriesOfUserDataCommand(UserServiceFactory.getUserService().getCurrentUser());
            List<Series> seriesList = seriesFromUser.fetch().getList();
            map.put("seriesResults",seriesList);

            List<String> genresList = new LinkedList<>();
            genresList.add("Action");
            genresList.add("Supernatural");
            genresList.add("Kids");
            genresList.add("School Life");
            genresList.add("Yaoi");
            map.put("genres",genresList);
        } catch (Exception e) {
            e.printStackTrace();
        }



        return new ModelAndView("searchPage");
    }
}
