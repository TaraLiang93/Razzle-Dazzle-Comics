package com.rdc.create;

import com.data.Globals;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
        return new ModelAndView("projectAdminPage");
    }
}
