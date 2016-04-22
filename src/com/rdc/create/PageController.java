package com.rdc.create;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.queries.external.GetPageByIDCommand;
import com.data.creation.Page;
import com.model.WritePageModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
    public ModelAndView redirectWritePage(@RequestParam String pageID,
                                          @RequestParam String chapterID,
                                          @RequestHeader String referer,
                                          ModelMap map){

        map.put("chapterID", chapterID);
        Page page = null;
        try {
            Container<Page> pageReadable = new GetPageByIDCommand(pageID).fetch();
            page = pageReadable.getResult();
            map.put("page", page);
        } catch (FetchException e) {
            e.printStackTrace();
            return new ModelAndView(referer);
        }


        return new ModelAndView("WritePage");
    }

    @RequestMapping(value=SAVE_WRITE_PAGE, method= RequestMethod.POST)
    public ModelAndView saveWritePage(@ModelAttribute("writePageModel") WritePageModel model, ModelMap map){


        System.out.println(model);

        return new ModelAndView("chapterPage");
    }

}
