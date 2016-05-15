package com.rdc.read;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.api.queries.external.GetChaptersOfSeriesCommand;
import com.data.api.queries.external.GetSeriesOfUserDataCommand;
import com.data.creation.Chapter;
import com.data.structure.Series;
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
public class ReadController {

    public static final String READCOMICS = "/read";


    @RequestMapping(value = READCOMICS, method = RequestMethod.GET)
    public ModelAndView loadChapter(@RequestHeader String referer, ModelMap map) {


        return new ModelAndView("readComics");
    }


    @RequestMapping(value ="/read/newReleases", method = RequestMethod.GET)
    public ModelAndView loadLatestComicsPage(ModelMap map) {


        List<String> genreList = new LinkedList<>();
        genreList.add("Action");
        genreList.add("Gays");
        genreList.add("Fun");
        genreList.add("School life");
        genreList.add("Crime");
        map.put("genres",genreList);

        List<Chapter> chapters = new LinkedList<>();
        Readable<Series> seriesReadable = new GetSeriesOfUserDataCommand(UserServiceFactory.getUserService().getCurrentUser());
        try {
            List<Series> series = seriesReadable.fetch().getList();
            for(Series s : series){
                for(Chapter c : s.getChapters()){
                    chapters.add(c);
                }
            }
            map.put("chapters",chapters);
        } catch (FetchException e) {
            e.printStackTrace();
        }
        return new ModelAndView("latestRelease");
    }

    @RequestMapping(value ="/read/topComics", method = RequestMethod.GET)
    public ModelAndView loadTopComicPage(ModelMap map) {

        List<String> genreList = new LinkedList<>();
        genreList.add("Action");
        genreList.add("Gays");
        genreList.add("Fun");
        genreList.add("School life");
        genreList.add("Crime");
        map.put("genres",genreList);

        List<Chapter> chapters = new LinkedList<>();
        Readable<Series> seriesReadable = new GetSeriesOfUserDataCommand(UserServiceFactory.getUserService().getCurrentUser());
        try {
            List<Series> series = seriesReadable.fetch().getList();

            Series topSeries = series.get(0);
            series.remove(0);

            map.put("topSeries",topSeries);
            map.put("series",series);
        } catch (FetchException e) {
            e.printStackTrace();
        }

        return new ModelAndView("top100Comics");
    }


    @RequestMapping(value ="/read/genres", method = RequestMethod.GET)
    public ModelAndView loadGenrePage(ModelMap map) {

        return new ModelAndView("genres");
    }

    @RequestMapping(value ="/read/genres/{genreName}", method = RequestMethod.GET)
    public ModelAndView loadGenreList(@PathVariable String genreName, ModelMap map) {

        return new ModelAndView("genresList");
    }


}
