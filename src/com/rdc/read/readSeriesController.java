package com.rdc.read;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.api.queries.external.GetSeriesByIDCommand;
import com.data.structure.Series;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Jason on 5/14/16.
 */

@Controller
public class readSeriesController {

    @RequestMapping(value ="/read/{series}", method = RequestMethod.GET)
    public ModelAndView loadSeries(@RequestParam String seriesID, ModelMap map) {

        Readable<Series> seriesReadable = new GetSeriesByIDCommand(seriesID);
        try {
            Series series = seriesReadable.fetch().getResult();
            map.put("series",series);
            map.put("chapters",series.getChapters());
        } catch (FetchException e) {
            e.printStackTrace();
        }


        return new ModelAndView("comicSeries");
    }
}
