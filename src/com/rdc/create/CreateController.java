package com.rdc.create;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.queries.external.GetChapterByIDCommand;
import com.data.api.queries.external.GetTeamMembersOfChapterCommand;
import com.data.creation.Chapter;
import com.data.structure.TeamMember;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class CreateController {

    public static final String CHAPTER_HOME = "/create";


    @RequestMapping(value = CHAPTER_HOME, method = RequestMethod.GET)
    public ModelAndView loadChapter(@RequestHeader String referer, ModelMap map) {


        return new ModelAndView("createHome");
    }
}
