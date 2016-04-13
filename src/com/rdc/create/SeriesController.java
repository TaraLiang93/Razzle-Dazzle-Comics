package com.rdc.create;

import com.model.ScribbleModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class SeriesController {

    @RequestMapping(value="/create/series/updateDescription", method= RequestMethod.POST)
    public ModelAndView updateSeriesDescr(@RequestParam String desc, HttpSession session){

        System.out.println("NEW DESCRIPTION: " + desc);


        return new ModelAndView("homepage");
    }

    @RequestMapping(value="/create/series/updateSeriesImage", method=RequestMethod.GET)
    public ModelAndView updateSeriesImg(@RequestParam String imgSrc, HttpSession session){
        System.out.println("New image: "+ imgSrc);
        return new ModelAndView("homepage");

    }

}
