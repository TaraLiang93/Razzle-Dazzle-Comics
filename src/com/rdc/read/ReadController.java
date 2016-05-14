package com.rdc.read;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;

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


        LinkedList<String> genreList = new LinkedList<>();
        genreList.add("Action");
        genreList.add("Gays");
        genreList.add("Fun");
        genreList.add("School life");
        genreList.add("Crime");
        map.put("genre",genreList);
        return new ModelAndView("latestRelease");
    }


}
