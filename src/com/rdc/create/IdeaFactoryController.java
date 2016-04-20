package com.rdc.create;

import com.data.UserData;
import com.data.api.createables.DoodleCreater;
import com.data.api.createables.ScribbleCreater;
import com.data.api.createables.fillCommands.DoodleFillCommand;
import com.data.api.createables.fillCommands.ScribbleFillCommand;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Createable;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.Updateable;
import com.data.api.queries.external.*;
import com.data.api.updatables.DoodleUpdater;
import com.data.api.updatables.UserDataUpdater;
import com.data.api.updatables.updateTasks.UpdateDoodleTask;
import com.data.api.updatables.updateTasks.UpdateScribbleTask;
import com.data.api.updatables.updateTasks.UpdateUserAddScribbleTask;
import com.data.api.updatables.updateTasks.UpdateUserDataAddDoodlesTask;
import com.data.creation.Doodle;
import com.data.creation.Scribble;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.model.ScribbleModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class IdeaFactoryController {

    public static final String IDEA_HOME = "/create/ideas";
    public static final String LOAD_SCRIBBLE = "/create/scribble/load/{id}";
    public static final String LOAD_DOODLE = "/create/doodle/load/{id}";
    public static final String SAVE_SCRIBBLE ="/create/scribble/save";
    public static final String SAVE_DOODLE ="/create/doodle/save";

    @RequestMapping(value=IDEA_HOME, method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loadIdeaFactory(HttpSession session, ModelMap map){

        User user = UserServiceFactory.getUserService().getCurrentUser();

        if(user == null) return new ModelAndView("/logout");

            List<Doodle> doodles = new LinkedList<>();
            Readable<Doodle> getUserDoodles = new GetDoodlesOfUserDataCommand(user);
            try {
                List<Doodle> doodleList = getUserDoodles.fetch().getList();
                map.put("doodles", doodleList);
            } catch (FetchException e) {
                e.printStackTrace();
            }

            List<Scribble> scribbles = new LinkedList<>();
            Readable<Scribble> getUserScribbles = new GetScribblesOfUserDataCommand(user);
            try {
                List<Scribble> scribbleList = getUserScribbles.fetch().getList();
                map.put("scribbles", scribbleList);
            } catch (FetchException e) {
                e.printStackTrace();
            }

        return new ModelAndView("ideaFactory");
    }


    @RequestMapping(value=LOAD_SCRIBBLE, method= RequestMethod.GET)
    public ModelAndView loadScribble(@PathVariable String id, HttpSession session, ModelMap map){

        System.out.println("ID : " + id);
        if(id != null && id.equals("new")){
            System.out.println("New!");
            map.put("scribble", new Scribble());
        }
        else{
            User user = UserServiceFactory.getUserService().getCurrentUser();
            System.out.println("Old!");
            Readable<Scribble> q = new GetScribblesByIDCommand(id);
            Scribble scribble = null;
            try {
                scribble = q.fetch().getResult();
                map.put("scribble", scribble);
            } catch (FetchException e) {
                e.printStackTrace();
                map.put("scribble", new Scribble());
            }

        }

        return new ModelAndView("scribbles", "scribbleModel", new ScribbleModel());
    }

    @RequestMapping(value=SAVE_SCRIBBLE, method=RequestMethod.POST)
    public ModelAndView saveScribble(@ModelAttribute("scribbleModel") ScribbleModel model, HttpServletRequest req){

        System.out.println("Made It! -->" + model);

        User user = UserServiceFactory.getUserService().getCurrentUser();

        if(model.getId() != null && !model.getId().equals("")){ //It Exists, update it
            try {
                new Updateable().updateEntity(new GetScribblesByIDCommand(model.getId()), new UpdateScribbleTask(model));
            } catch (FetchException | UpdateException | CreateException e) {
                e.printStackTrace();
            }
        }
        else{ //It's brand new

            try {
                Scribble newScribb = new ScribbleCreater(model.getTitle(), model.getDescription()).createEntity(new ScribbleFillCommand(model));
                new UserDataUpdater().updateEntity(new GetUserDataByUserCommand(user), new UpdateUserAddScribbleTask(newScribb));
            } catch (CreateException e) {
                e.printStackTrace();
            } catch (FetchException e) {
                e.printStackTrace();
            } catch (UpdateException e) {
                e.printStackTrace();
            }
        }

        return new ModelAndView("redirect:" + IDEA_HOME);
    }

    @RequestMapping(value=LOAD_DOODLE, method= RequestMethod.GET)
    public ModelAndView loadDoodle(@PathVariable String id, HttpSession session, ModelMap map){

        String canvasImage;

        try {
            System.out.println(id);
            Readable<Doodle> getDoodle = new GetDoodlesByIDCommand(id);
            Doodle doodle = getDoodle.fetch().getResult();
            map.put("canvasImage",doodle.getCanvas().getCanvasImage());
            map.put("doodleTitle",doodle.getTitle());
            map.put("doodleDescription",doodle.getDescription());
            map.put("doodleId",doodle.getDoodleId());
        } catch (FetchException e) {
            e.printStackTrace();
        }

        return new ModelAndView("doodles");
    }

    @RequestMapping(value="/create/doodle/new", method= RequestMethod.GET)
    public ModelAndView newDoodle(HttpSession session,ModelMap map){

        return new ModelAndView("doodles");
    }

        @RequestMapping(value=SAVE_DOODLE, method= RequestMethod.POST)
    public ModelAndView saveDoodle(@RequestParam final String canvasImage, @RequestParam String doodleTitle, @RequestParam String doodleDescription, HttpServletRequest req, HttpSession session, ModelMap map){

        System.out.println("saving a doodle made easy");
        System.out.println(canvasImage);
        System.out.println(doodleTitle);
        System.out.println(doodleDescription);


        String dId = req.getParameter("doodleId");
        System.out.println(dId);
        if(dId != null)// updating the doodle
        {
            Long doodleId = Long.parseLong(dId);
            Updateable<Doodle> updateDoodle = new DoodleUpdater();
            Readable<Doodle> getDoodle = new GetDoodlesByIDCommand(doodleId);
            try
            {
                Doodle theDoodle = getDoodle.fetch().getResult();
                updateDoodle.updateEntity(getDoodle, new UpdateDoodleTask(doodleTitle, doodleDescription, canvasImage) );

            }
            catch (CreateException | FetchException | UpdateException  e) {
                e.printStackTrace();
            }

        }
        else// creating the doodle
        {
            try
            {
                Createable<Doodle> anotherDoodleCreater = new DoodleCreater(doodleTitle, doodleDescription);
                Doodle anotherDoodle = anotherDoodleCreater.createEntity(new DoodleFillCommand(canvasImage));

                Readable<UserData> userDataReadable = new GetUserDataByIDCommand(UserServiceFactory.getUserService().getCurrentUser().getUserId());
                Updateable<UserData> userDataUpdateable = new UserDataUpdater();
                userDataUpdateable.updateEntity(userDataReadable, new UpdateUserDataAddDoodlesTask(anotherDoodle.getDoodleId()));
                System.out.println("created the doodles");
            }
            catch (UpdateException | FetchException | CreateException e) {
                e.printStackTrace();
            }

        }

        return new ModelAndView("forward:" + IDEA_HOME);
    }

    @RequestMapping(value="/test/scribble", method= RequestMethod.GET)
    public ModelAndView testScribble(HttpSession session, ModelMap map){

        ScribbleModel model = new ScribbleModel();
        model.setTitle("Damnn Right!");
        System.out.println(model);

        Scribble scrib = new Scribble();
        System.out.println("Before--->" + scrib);
        map.put("scribble", scrib);

        return new ModelAndView("testScribble", "scribbleModel", model);
    }

    @RequestMapping(value="/test/scribble/save", method= RequestMethod.POST)
    public ModelAndView saveTestScribble(@ModelAttribute("scribbleModel") ScribbleModel model, HttpSession session, ModelMap map){

        System.out.println("After! -->" + model);


        return new ModelAndView("homepage");
    }



}
