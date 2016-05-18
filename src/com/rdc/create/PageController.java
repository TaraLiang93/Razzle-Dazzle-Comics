package com.rdc.create;

import com.data.UserData;
import com.data.api.createables.*;
import com.data.api.createables.fillCommands.*;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Createable;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.Updateable;
import com.data.api.queries.external.*;
import com.data.api.updatables.*;
import com.data.api.updatables.updateTasks.*;
import com.data.creation.*;
import com.data.structure.Flow;
import com.data.structure.FlowTask;
import com.data.structure.FlowType;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.appengine.repackaged.com.google.gson.JsonArray;
import com.google.appengine.repackaged.com.google.gson.JsonElement;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import com.model.WritePageModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

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

    public static final String ADD_TASK="/create/page/add";
    public static final String LOAD_TASK="/create/page/loadTask";

    private final SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

    @RequestMapping(value=LOAD_DRAW_PAGE, method={RequestMethod.GET , RequestMethod.POST})
    public ModelAndView redirectDrawPage(HttpServletRequest req,ModelMap map){

        String chapterID = req.getParameter("chapterID");
        String pageID = req.getParameter("pageID");

        Readable<Chapter> chapterReadable = new GetChapterByIDCommand(chapterID);
        Readable<Page> pageReadable = new GetPageByIDCommand(pageID);

        try {
            Page page = pageReadable.fetch().getResult();
            map.put("page",page);
            map.put("scenes",page.getScenes());
            map.put("firstScene",page.getScenes().get(0));
            map.put("chapterID",chapterID);
        } catch (FetchException e) {
            e.printStackTrace();
        }

        return new ModelAndView("drawPage");
    }


    @RequestMapping(value=LOAD_WRITE_PAGE, method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView redirectWritePage(@RequestParam String pageID,
                                          @RequestParam String chapterID,
                                          @RequestHeader String referer,
                                          ModelMap map){

       // map.put("chapterID", chapterID);
        Page page = null;
        try {
            Container<Page> pageReadable = new GetPageByIDCommand(pageID).fetch();
            page = pageReadable.getResult();

            List<Page> pages = new ArrayList<>();   // Supports generic page loading multiple pages on writing
            pages.add(page);                        //
            map.put("pages", pages);
            map.put("chapterID", chapterID);
        } catch (FetchException e) {
            e.printStackTrace();
            return new ModelAndView(referer);
        }


        return new ModelAndView("WritePage");
    }

    @RequestMapping(value=SAVE_WRITE_PAGE, method= RequestMethod.POST)
    public ModelAndView saveWritePage(@ModelAttribute("writePageModel") WritePageModel model, ModelMap map){
        System.out.println("WriteModel : " + model);


        try {
            Container<Page> getPages = new GetPageByIDCommand(model.getPages().get(0).getId()).fetch();
            Page page = getPages.getResult();
            new PageUpdater().updateEntity(page, new UpdateWritePageTask(model));
            map.put("id", model.getChapterID());
        } catch (FetchException | UpdateException | CreateException e) {
            e.printStackTrace();
            return new ModelAndView("redirect:"+ProjectAdminPageController.LOAD_ADMIN_PAGE);
        }



        return new ModelAndView("redirect:/create/chapter/load/" + model.getChapterID());
    }


    @RequestMapping(value=ADD_COMMENT, method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Boolean> addComment(@RequestParam String comment,
                                              @RequestParam String pageID){

        System.out.println("Found Comment : " + comment);
        System.out.println("Found PageID : " + pageID);

        User user = UserServiceFactory.getUserService().getCurrentUser();

        try {
            Comment comEnt = new CommentCreater(comment).createEntity(new CommentFillCommand(user, pageID));

            new PageUpdater().updateEntity(
                    new GetPageByIDCommand(pageID),
                    new UpdatePageAddCommentTask(comEnt)
            );

        } catch (CreateException | UpdateException | FetchException e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity(true, HttpStatus.OK);

    }

    @RequestMapping(value=EDIT_SUMMARY, method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Boolean> editSummary(@RequestParam String summary,
                                              @RequestParam String pageID){

        System.out.println("Found Summary : " + summary);
        System.out.println("Found PageID : " + pageID);

        try {
            new PageUpdater().updateEntity(new GetPageByIDCommand(pageID), new UpdatePageEditSummaryTask(summary));
        } catch (FetchException | UpdateException | CreateException e) {
            e.printStackTrace();
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(true, HttpStatus.OK);

    }


    @RequestMapping(value=MOVE_NEXT, method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Boolean> moveNextTask(@RequestParam String pageID){

        System.out.println("Move Next Task --> Found PageID : " + pageID);

        try {
            new PageUpdater().updateEntity(new GetPageByIDCommand(pageID), new UpdatePageMoveToNextFlowTask());
            System.out.println("Successfully Moved Page Next!");
        } catch (FetchException | UpdateException | CreateException e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity(true, HttpStatus.OK);

    }

    @RequestMapping(value=MOVE_PREV, method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Boolean> movePrevTask(@RequestParam String pageID){

        System.out.println("Move Previous Task --> Found PageID : " + pageID);

        try {
            new PageUpdater().updateEntity(new GetPageByIDCommand(pageID), new UpdatePageMoveToPreviousFlowTask());
            System.out.println("Successfully Moved Page Previous!");
        } catch (FetchException | UpdateException | CreateException e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }

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
            root.addProperty("id", pageID);
            root.addProperty("title", page.getTitle());
            root.addProperty("summary", page.getSummary());
            root.addProperty("createDate", formatter.format(page.getCreateDate()));
            root.addProperty("author", user.getNickname());

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


            JsonArray sceneList = new JsonArray();
            JsonArray canvasList = new JsonArray();

            for(Scene scene : page.getScenes()){
                JsonArray dialogueList = new JsonArray();
                for(Dialogue dialogue : scene.getDialogues()){
                    dialogueList.add(dialogue.getDialogue());
                }
                Canvas canvas = scene.getCanvas();
                if(canvas != null){
                    canvasList.add(canvas.getCanvasImage());
                }

                JsonObject obj = new JsonObject();
                obj.add("dialogue", dialogueList);
                sceneList.add(obj);
            }

            root.add("scenes", sceneList);
            root.add("canvases", canvasList);


            return new ResponseEntity<String>(root.toString(), HttpStatus.OK);

        } catch (FetchException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
        }

    }


    @RequestMapping(value=ADD_TASK, method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addTask(@RequestParam String chapterID,
                                          @RequestParam String title,
                                          @RequestParam String summary,
                                          HttpServletRequest req){

        System.out.println("Add Task --> Found chapterID : " + chapterID);
        System.out.println("Add Task --> Found Title : " + title);
        System.out.println("Add Task --> Found Summary : " + summary);

        try {
            Container<Chapter> getChap = new GetChapterByIDCommand(chapterID).fetch();
            Chapter chapter = getChap.getResult();

            if(chapter == null){ //Make sure the chapter exists before we try to create the page
                throw new CreateException("The chapter must exist before adding the new page");
            }

            Page page = new PageCreater(title, summary).createEntity(new PageFillCommand(chapterID));

            new ChapterUpdater().updateEntity(new GetChapterByIDCommand(chapterID), new UpdateChapterAddPageTask(page.getId()));

            System.out.println("Successfully Added --> Page ID: " + page.getId());
            return new ResponseEntity<String>(""+ page.getId(), HttpStatus.OK);
        } catch (CreateException | FetchException | UpdateException e ) {
            e.printStackTrace();
            return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
        }


    }











    public static void createDefaultFlow() throws CreateException,FetchException,UpdateException{
        /**
         * Test: Create Default Flow, add flowTasks and assign FlowTypes
         */

        Createable<Flow> defaultFlowCreater = new FlowCreater( "defaultFlow");
        Flow defaultFlow = defaultFlowCreater.createEntity( new FlowFillCommand() );

        //Create the FlowTypes
        Createable<FlowType> flowTypeCreateable = new FlowTypeCreater("Write");
        FlowType writeFlowType = flowTypeCreateable.createEntity( new FlowTypeFillCommand( ) );

        //Create the FlowTypes
        Createable<FlowType> flowTypeCreateable2 = new FlowTypeCreater("PreDraw");
        FlowType preDrawFlowType = flowTypeCreateable2.createEntity( new FlowTypeFillCommand( ) );

        //Create the FlowTypes
        Createable<FlowType> flowTypeCreateable3 = new FlowTypeCreater("Draw");
        FlowType drawFlowType = flowTypeCreateable3.createEntity( new FlowTypeFillCommand( ) );

        //Create the FlowTypes
        Createable<FlowType> flowTypeCreateable4 = new FlowTypeCreater("Review");
        FlowType reviewFlowType = flowTypeCreateable4.createEntity( new FlowTypeFillCommand( ) );

        //Create the FlowTypes
        Createable<FlowType> flowTypeCreateable5 = new FlowTypeCreater("Done");
        FlowType doneFlowType = flowTypeCreateable5.createEntity( new FlowTypeFillCommand( ) );


        //Create FlowTask0
        Createable<FlowTask> flowTaskCreateable0 = new FlowTaskCreater( "Write Task", 0);
        FlowTask flowTask0 = flowTaskCreateable0.createEntity( new FlowTaskFillCommand());
        //Update FlowTask with correct Type
        Updateable<FlowTask> flowTaskUpdateable0 = new FlowTaskUpdater();
        Readable<FlowTask> flowTaskReadable0 = new GetFlowTaskByIDCommand(flowTask0.getFlowTaskId());
        flowTaskUpdateable0.updateEntity(flowTaskReadable0, new UpdateFlowTaskSetTypeTask(writeFlowType.getFlowTypeId()));
//            flowTask0.setFlowTypeKey(writeFlowType.getKey());

        //Create FlowTask1
        Createable<FlowTask> flowTaskCreateable1 = new FlowTaskCreater( "PreDraw Task", 1);
        FlowTask flowTask1 = flowTaskCreateable1.createEntity( new FlowTaskFillCommand());
        //Update FlowTask with correct Type
        Updateable<FlowTask> flowTaskUpdateable1 = new FlowTaskUpdater();
        Readable<FlowTask> flowTaskReadable1 = new GetFlowTaskByIDCommand(flowTask1.getFlowTaskId());
        flowTaskUpdateable1.updateEntity(flowTaskReadable1, new UpdateFlowTaskSetTypeTask(preDrawFlowType.getFlowTypeId()));
//            flowTask1.setFlowTypeKey( preDrawFlowType.getKey() );

        //Create FlowTask2
        Createable<FlowTask> flowTaskCreateable2 = new FlowTaskCreater( "Draw Task", 2);
        FlowTask flowTask2 = flowTaskCreateable2.createEntity( new FlowTaskFillCommand());
        //Update FlowTask with correct Type
        Updateable<FlowTask> flowTaskUpdateable2 = new FlowTaskUpdater();
        Readable<FlowTask> flowTaskReadable2 = new GetFlowTaskByIDCommand(flowTask2.getFlowTaskId());
        flowTaskUpdateable2.updateEntity(flowTaskReadable2, new UpdateFlowTaskSetTypeTask(drawFlowType.getFlowTypeId()));
//            flowTask2.setFlowTypeKey( drawFlowType.getKey() ); // how do we want to set the Flow Type?

        //Create FlowTask3
        Createable<FlowTask> flowTaskCreateable3 = new FlowTaskCreater( "Review Task", 3);
        FlowTask flowTask3 = flowTaskCreateable3.createEntity( new FlowTaskFillCommand());
        //Update FlowTask with correct Type
        Updateable<FlowTask> flowTaskUpdateable3 = new FlowTaskUpdater();
        Readable<FlowTask> flowTaskReadable3 = new GetFlowTaskByIDCommand( flowTask3.getFlowTaskId() );
        flowTaskUpdateable3.updateEntity( flowTaskReadable3, new UpdateFlowTaskSetTypeTask(reviewFlowType.getFlowTypeId()));
//            flowTask3.setFlowTypeKey( reviewFlowType.getKey() );


        //Create FlowTask4
        Createable<FlowTask> flowTaskCreateable4 = new FlowTaskCreater( "Done Task", 4);
        FlowTask flowTask4 = flowTaskCreateable4.createEntity( new FlowTaskFillCommand());
        //Update FlowTask with correct Type
        Updateable<FlowTask> flowTaskUpdateable4 = new FlowTaskUpdater();
        Readable<FlowTask> flowTaskReadable4 = new GetFlowTaskByIDCommand(flowTask4.getFlowTaskId());
        flowTaskUpdateable4.updateEntity(flowTaskReadable4, new UpdateFlowTaskSetTypeTask(doneFlowType.getFlowTypeId()) );
//            flowTask4.setFlowTypeKey( doneFlowType.getKey() );


        //Update Flow with FlowTasks
        Updateable<Flow> flowUpdateable = new FlowUpdater();
        flowUpdateable.updateEntity(defaultFlow, new UpdateFlowAddFlowTask( flowTask0.getFlowTaskId() ));
        flowUpdateable.updateEntity(defaultFlow, new UpdateFlowAddFlowTask( flowTask1.getFlowTaskId() ));
        flowUpdateable.updateEntity(defaultFlow, new UpdateFlowAddFlowTask( flowTask2.getFlowTaskId() ));
        flowUpdateable.updateEntity(defaultFlow, new UpdateFlowAddFlowTask( flowTask3.getFlowTaskId() ));
        flowUpdateable.updateEntity(defaultFlow, new UpdateFlowAddFlowTask( flowTask4.getFlowTaskId() ));

        //Set the next and previous flows
        // (Do this internally if one flow?)
        flowTask0.setNextTask(flowTask1.getKey());
        ofy().save().entity(flowTask0).now();

        flowTask1.setPrevTask(flowTask0.getKey());
        flowTask1.setNextTask(flowTask2.getKey());
        ofy().save().entity(flowTask1).now();

        flowTask2.setPrevTask(flowTask1.getKey());
        flowTask2.setNextTask(flowTask3.getKey());
        ofy().save().entity(flowTask2).now();

        flowTask3.setPrevTask(flowTask2.getKey());
        flowTask3.setNextTask(flowTask4.getKey());
        ofy().save().entity(flowTask3).now();

        flowTask4.setPrevTask(flowTask3.getKey());
        ofy().save().entity(flowTask4).now();

    }



    @RequestMapping(value="/create/drawPage/save", method=RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void saveDrawPage(@RequestParam String pageID,HttpServletRequest req, HttpSession session){

        Integer size = Integer.parseInt(req.getParameter("size"));

        Readable<Page> pageReadable = new GetPageByIDCommand(pageID);
        try {
            Page page = pageReadable.fetch().getResult();

            List<Scene> scenes =  page.getScenes();


        for(int i = 0; i < size;i++)
        {
            String canvasJson = req.getParameter("canvasImage" + i);
            // if the scene already exist update it that check it if i have the I
            for(Scene scene : scenes)
            {
                if(scene.getIndex() == i)
                {
                    Readable<Scene> sceneReadable = new GetSceneByIDCommand(scene.getId());

                    if(scene.getCanvasElement() != null) { // then it should be updated
                        Updateable<Scene> sceneUpdateable = new SceneUpdater();
                        sceneUpdateable.updateEntity(sceneReadable, new UpdateDrawSceneTask(canvasJson));
                    }
                    else // a new canvas should be created
                    {
                        Createable<Canvas> canvasCreateable = new CanvasCreater(canvasJson);

                       Canvas canvas = canvasCreateable.createEntity(new CanvasFillCommand(canvasJson));

                        Updateable<Scene> sceneUpdateable = new SceneUpdater();

                        sceneUpdateable.updateEntity(sceneReadable,new UpdateSceneAddCanvasTask(canvas.getCanvasId()));
                    }


                    break;
                }
            }

        }


        } catch (FetchException e) {
            e.printStackTrace();
        } catch (CreateException e) {
            e.printStackTrace();
        } catch (UpdateException e) {
            e.printStackTrace();
        }

        //updating the scenes of a page that already exist
        // using




//        canvasArr = list.toArray(new String[list.size()]);


    }
}
