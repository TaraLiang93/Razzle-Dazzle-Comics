package com.rdc.create;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class PublishController {

    @RequestMapping(value="/publish", method= RequestMethod.POST)
    public ModelAndView  loadPublishPage(HttpServletRequest req, ModelMap map){
        return new ModelAndView("homepage");
    }

}
