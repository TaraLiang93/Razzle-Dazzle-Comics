package com.rdc.create;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.queries.external.GetPageByIDCommand;
import com.data.creation.Page;
import com.model.WritePageModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class PageController {

    public static final String LOAD_DRAW_PAGE ="/create/drawPage/load";
    public static final String LOAD_WRITE_PAGE ="/create/writePage/load";
    public static final String SAVE_WRITE_PAGE ="/create/writePage/save";

    public static final String ADD_COMMENT="/create/page/comment/add";
    public static final String EDIT_SUMMARY="/create/page/editSummary";

    public static final String MOVE_NEXT="/create/page/moveNext";
    public static final String MOVE_PREV="/create/page/movePrev";

    @RequestMapping(value=LOAD_DRAW_PAGE, method= RequestMethod.GET)
    public ModelAndView redirectDrawPage( ModelMap map){


        return new ModelAndView("drawPage");
    }


    @RequestMapping(value=LOAD_WRITE_PAGE, method= RequestMethod.GET)
    public ModelAndView redirectWritePage(@RequestParam String pageID,
                                          @RequestParam String chapterID,
                                          @RequestHeader String referer,
                                          ModelMap map){

       // map.put("chapterID", chapterID);
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


    @RequestMapping(value=ADD_COMMENT, method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Boolean> addComment(@RequestParam String comment,
                                              @RequestParam String pageID){

        System.out.println("Found Comment : " + comment);
        System.out.println("Found PageID : " + pageID);



        return new ResponseEntity(true, HttpStatus.OK);

    }

    @RequestMapping(value=EDIT_SUMMARY, method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Boolean> editSummary(@RequestParam String summary,
                                              @RequestParam String pageID){

        System.out.println("Found Summary : " + summary);
        System.out.println("Found PageID : " + pageID);



        return new ResponseEntity(true, HttpStatus.OK);

    }


    @RequestMapping(value=MOVE_NEXT, method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Boolean> moveNextTask(@RequestParam String pageID){

        System.out.println("Move Next Task --> Found PageID : " + pageID);

        return new ResponseEntity(true, HttpStatus.OK);

    }

    @RequestMapping(value=MOVE_PREV, method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Boolean> movePrevTask(@RequestParam String pageID){

        System.out.println("Move Previous Task --> Found PageID : " + pageID);



        return new ResponseEntity(true, HttpStatus.OK);

    }
}
