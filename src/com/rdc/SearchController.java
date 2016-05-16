package com.rdc;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.api.queries.external.GetGenresCommand;
import com.data.api.queries.external.GetSeriesFromSearch;
import com.data.api.queries.external.GetSeriesOfUserDataCommand;
import com.data.structure.Genre;
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
    public ModelAndView loadSearchResults(HttpServletRequest req, ModelMap map){


        String searchBar = req.getParameter("searchBar");
        Genre genre;
        map.put("searchTitle",searchBar);


        Readable<Series> seriesReadable = new GetSeriesFromSearch(searchBar);
        try {
            List<Series> seriesList = seriesReadable.fetch().getList();
            map.put("seriesResults", seriesList);
        }catch (FetchException ex){
            ex.printStackTrace();
        }

        Readable<Genre> genreReadable = new GetGenresCommand();
        try {
            List<Genre> genresList = genreReadable.fetch().getList();
            map.put("genres",genresList);
        } catch (FetchException e) {
            e.printStackTrace();
        }




        return new ModelAndView("searchPage");
    }
}
