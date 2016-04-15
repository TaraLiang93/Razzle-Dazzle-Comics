package com.rdc.create;

import com.data.Globals;
import com.data.structure.Series;
import com.sun.org.apache.bcel.internal.generic.L2D;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class ProjectAdminPageController {

    @RequestMapping(value="/create/projectAdminPage", method= RequestMethod.GET)
    public ModelAndView loadPublishPage(HttpSession session, ModelMap map){

        Globals globals = (Globals) session.getAttribute("globals");
        if(globals == null)
        {
            globals = new Globals();
            session.setAttribute("globals",globals);
        }
        globals.setStatus("create");

        LinkedList<Series> series = new LinkedList<>();

        for(int i = 0; i <= 100; ++i){
            Series newSeries = new Series();
            newSeries.setSeriesID(new Long(i));
            newSeries.setTitle("Series " + i);
            newSeries.setDescription("This is a series about love " + i);
            series.add(newSeries);
        }

        map.put("series",series);

        return new ModelAndView("projectAdminPage");
    }
}
