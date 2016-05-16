package com.rdc.read;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.api.queries.external.*;
import com.data.creation.Chapter;
import com.data.structure.Genre;
import com.data.structure.Series;
import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class ReadController {

    public static final String READCOMICS = "/read";
    public static final Integer GETMAXSERIES = 100;


    @RequestMapping(value = READCOMICS, method = RequestMethod.GET)
    public ModelAndView loadChapter(@RequestHeader String referer, ModelMap map) {

        Readable<Series> seriesReadable = new GetLatestReleasedSeriesCommand(4);
        try {
            List<Series> latestSeries = seriesReadable.fetch().getList();
            map.put("latestSeries",latestSeries);

            seriesReadable = new GetTopSeriesCommand(4);

            List<Series> popularSeries = seriesReadable.fetch().getList();
            map.put("popularSeries",popularSeries);

            seriesReadable = new GetSeriesCommand();
            List<Series> allSeries = seriesReadable.fetch().getList();
            HashSet<Series> randomSeries = new HashSet<>();

            while(randomSeries.size() < 5) {// grab series at random and then loop until there is 5 series in the map
                int index = (int) Math.floor(Math.random() * allSeries.size());
                randomSeries.add(allSeries.get(index));
            }

            map.put("randomSeries",randomSeries);



        } catch (FetchException e) {
            e.printStackTrace();
        }


        return new ModelAndView("readComics");
    }


    @RequestMapping(value ="/read/newReleases", method = RequestMethod.GET)
    public ModelAndView loadLatestComicsPage(ModelMap map) {


        Readable<Genre> genreReadable = new GetGenresCommand();
        List<Genre> genreList = null;
        try {
            genreList = genreReadable.fetch().getList();
            map.put("genres",genreList);
        } catch (FetchException e) {
            e.printStackTrace();
        }


        List<Chapter> chapters = new LinkedList<>();
        Readable<Series> seriesReadable = new GetLatestReleasedSeriesCommand(GETMAXSERIES/10);
        try {
            List<Series> series = seriesReadable.fetch().getList();
            map.put("series",series);
        } catch (FetchException e) {
            e.printStackTrace();
        }
        return new ModelAndView("latestRelease");
    }

    @RequestMapping(value ="/read/topComics", method = RequestMethod.GET)
    public ModelAndView loadTopComicPage(ModelMap map) {

        Readable<Genre> genreReadable = new GetGenresCommand();
        List<Genre> genreList = null;
        try {
            genreList = genreReadable.fetch().getList();
        } catch (FetchException e) {
            e.printStackTrace();
        }
        map.put("genres",genreList);

        Readable<Series> seriesReadable = new GetTopSeriesCommand(GETMAXSERIES);
        try {
            List<Series> series = seriesReadable.fetch().getList();

            if(series.size() > 0) {
                Series topSeries = series.get(0);
                series.remove(0);

                map.put("topSeries", topSeries);
            }
            map.put("series",series);
        } catch (FetchException e) {
            e.printStackTrace();
        }

        return new ModelAndView("top100Comics");
    }


    @RequestMapping(value ="/read/genres", method = RequestMethod.GET)
    public ModelAndView loadGenrePage(ModelMap map) {

        Readable<Genre> genreReadable = new GetGenresCommand();

        List<Genre> genres = null;
        try {
            genres = genreReadable.fetch().getList();
        } catch (FetchException e) {
            e.printStackTrace();
        }
        map.put("genres",genres);

        return new ModelAndView("genres");
    }

    @RequestMapping(value ="/read/genres/{genreName}", method = RequestMethod.POST)
    public ModelAndView loadGenreList(HttpServletRequest req, ModelMap map) {

        String genreID = req.getParameter("genreID");

        Readable<Genre> genreReadable = new GetGenreByIDCommand(genreID);

        try {
            Genre genre = genreReadable.fetch().getResult();
            map.put("currentGenre",genre);
        } catch (FetchException e) {
            e.printStackTrace();
        }

        return new ModelAndView("genresList");
    }


}
