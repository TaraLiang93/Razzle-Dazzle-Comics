package com.rdc.create;

import com.data.UserData;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.queries.external.GetPageByIDCommand;
import com.data.creation.*;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.appengine.repackaged.com.google.gson.*;
import com.model.WritePageModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    public static final String LOAD_TASK="/create/page/loadTask";

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

    @RequestMapping(value=LOAD_TASK, method=RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> loadPageTask(@RequestParam String pageID){

        System.out.println("Load Task --> Found PageID : " + pageID);

        User user = UserServiceFactory.getUserService().getCurrentUser();
        JsonObject root = null;
        try {
            Container<Page> pageContainer = new GetPageByIDCommand(pageID).fetch();
            Page page = pageContainer.getResult();

            root = new JsonObject();
            root.addProperty("title", page.getTitle());
            root.addProperty("summary", page.getSummary());
            root.addProperty("createDate", page.getCreateDate().toString());

            List<Comment> comments = page.getComments();
            JsonArray commentList = new JsonArray();

            Collections.sort(comments, new Comparator<Comment>() {
                @Override
                public int compare(Comment o1, Comment o2) {//Sort comments ascending by index
                    return o1.getIndex() - o2.getIndex();
                }
            });

            for(Comment comm : comments){
                JsonObject obj = new JsonObject();
                UserData userdata = comm.getUser();
                String username = (userdata.getUserName().equals(user.getEmail()))? "Me" : userdata.getUserName();
                obj.addProperty("user", username);
                obj.addProperty("comment", comm.getComment());
                commentList.add(obj);
            }
            root.add("comments", commentList);


            JsonArray dialogueList = new JsonArray();
            JsonArray canvasList = new JsonArray();

            for(Scene scene : page.getScenes()){
                for(Dialogue dialogue : scene.getDialogues()){
                    dialogueList.add(dialogue.getDialogue());
                }
                Canvas canvas = scene.getCanvas();
                if(canvas != null){
                    canvasList.add(canvas.getCanvasImage());
                }
            }

            root.add("dialogues", dialogueList);
            root.add("canvases", canvasList);


            return new ResponseEntity<String>(root.toString(), HttpStatus.OK);

        } catch (FetchException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
        }

    }


    @RequestMapping(value="/create/drawPage/save", method=RequestMethod.POST)
    public ModelAndView saveDrawPage(HttpServletRequest req, HttpSession session){

//        String drawing = req.getParameter("drawing");
        String drawings[] = req.getParameterValues("drawing");
//        drawing = drawing.repalceAll("");

//        String drawingData[] =  gson.fromJson(drawing,String[].class);
//
//        System.out.println("drawingData = " + drawingData);
//
//        try {
//            JSONObject jsonObj = new JSONObject(drawing);
//            JSONArray json = new JSONArray(drawing);
//            System.out.println("json = " + json);
//            JsonArray
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


//

        System.out.println();



        return new ModelAndView("forward:/" );
    }
}
