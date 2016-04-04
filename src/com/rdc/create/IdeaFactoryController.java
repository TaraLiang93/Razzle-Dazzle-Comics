package com.rdc.create;

import com.data.creation.Doodle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class IdeaFactoryController {

    public static final String IDEA_HOME = "/create/ideas";

    @RequestMapping(value=IDEA_HOME, method= RequestMethod.GET)
    public ModelAndView loadIdeaFactory(HttpSession session, ModelMap map){

        Doodle doodle1 = new Doodle();
        doodle1.setTitle("Ayyyy");

        Doodle doodle2 = new Doodle();
        doodle2.setTitle("Beeeee");

        Doodle doodle3 = new Doodle();
        doodle3.setTitle("Seeee");

        List<Doodle> doodles = new LinkedList<>();

        doodles.add(doodle1);
        doodles.add(doodle2);
        doodles.add(doodle3);

        map.put("doodles", doodles);

        return new ModelAndView("ideaFactory");
    }
}
