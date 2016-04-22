package com.rdc.create;

import com.model.WritePageModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class PageController {

    public static final String LOAD_DRAW_PAGE ="/create/drawPage";
    public static final String LOAD_WRITE_PAGE ="/create/writePage";

    public static final String SAVE_WRITE_PAGE ="/create/writePage/save";


    @RequestMapping(value=LOAD_DRAW_PAGE, method= RequestMethod.GET)
    public ModelAndView redirectDrawPage( ModelMap map){


        return new ModelAndView("drawPage");
    }


    @RequestMapping(value=LOAD_WRITE_PAGE, method= RequestMethod.GET)
    public ModelAndView redirectWritePage(@RequestParam String chapterID, ModelMap map){

        map.put("chapterID", chapterID);

        return new ModelAndView("WritePage");
    }

    @RequestMapping(value=SAVE_WRITE_PAGE, method= RequestMethod.POST)
    public ModelAndView saveWritePage(@ModelAttribute("writePageModel") WritePageModel model, ModelMap map){




        return new ModelAndView("chapterPage");
    }

}
