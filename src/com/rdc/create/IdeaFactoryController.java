package com.rdc.create;

import com.data.creation.Doodle;
import com.data.creation.Scribble;
import com.model.ScribbleModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

        Doodle doodle1 = new Doodle();
        doodle1.setTitle("Ayyyy");
        doodle1.setDescription("Awww Yeahhh");

        Doodle doodle2 = new Doodle();
        doodle2.setTitle("Beeeee");
        doodle2.setDescription("Be Bop Boop");

        Doodle doodle3 = new Doodle();
        doodle3.setTitle("Seeee");
        doodle3.setDescription("See ya next tuesday");

        List<Doodle> doodles = new LinkedList<>();

        doodles.add(doodle1);
        doodles.add(doodle2);
        doodles.add(doodle3);

        map.put("doodles", doodles);
        map.put("scribbles", doodles);

        return new ModelAndView("ideaFactory");
    }


    @RequestMapping(value=LOAD_SCRIBBLE, method= RequestMethod.GET)
    public ModelAndView loadScribble(@PathVariable String id, HttpSession session, ModelMap map){

        System.out.println("ID : " + id);
        if(id != null && id.equals("new")){
            System.out.println("New!");
        }
        else{
            System.out.println("Old!");
            map.put("scribble", new Scribble("This is a title, Yay"));
        }

        return new ModelAndView("scribbles", "scribbleModel", new ScribbleModel());
    }

    @RequestMapping(value=SAVE_SCRIBBLE, method=RequestMethod.POST)
    public ModelAndView saveScribble(@ModelAttribute("scribbleModel") ScribbleModel model, HttpServletRequest req){

        System.out.println("Made It! -->" + model);

        return new ModelAndView("forward:" + IDEA_HOME);
    }

    @RequestMapping(value=LOAD_DOODLE, method= RequestMethod.GET)
    public ModelAndView loadDoodle(@PathVariable String id, HttpSession session, ModelMap map){

        System.out.println("ID : " + id);
        if(id != null && id.equals("new")){
            System.out.println("New!");
        }
        else{
            System.out.println("Old!");
        }

        return new ModelAndView("homepage");
    }

    @RequestMapping(value="/create/doodle/new", method= RequestMethod.GET)
    public ModelAndView newDoodle(HttpSession session,ModelMap map){

        return new ModelAndView("doodles");
    }

    @RequestMapping(value="/create/doodle/save", method= RequestMethod.POST)
    public ModelAndView saveDoodle(@RequestParam String canvasImage, @RequestParam String doodleTitle, @RequestParam String doodleDescription,HttpServletRequest req, HttpSession session,ModelMap map){

        System.out.println("saving a doodle made easy");
        System.out.println(canvasImage);
        System.out.println(doodleTitle);
        System.out.println(doodleDescription);

        return new ModelAndView("forward:" + IDEA_HOME);
    }

    @RequestMapping(value=SAVE_SCRIBBLE, method=RequestMethod.POST)
    public ModelAndView saveScribble(HttpServletRequest req, ModelMap map){

        System.out.println("Made It!");


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
