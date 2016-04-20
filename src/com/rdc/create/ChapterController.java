package com.rdc.create;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.api.queries.external.GetTeamMemberByIDCommand;
import com.data.creation.Doodle;
import com.data.structure.TeamMember;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class ChapterController {

    public static final String NEW_CHAPTER = "/create/chapter/new";

    @RequestMapping(value="/loadTeam", method= RequestMethod.GET)
    public ModelAndView loadTeam(@PathVariable String id, HttpSession session, ModelMap map){

        try {
            System.out.println(id);
            Readable<TeamMember> getMember = new GetTeamMemberByIDCommand(id);
            TeamMember member = getMember.fetch().getResult();
            map.put("teamMember",member);
        } catch (FetchException e) {
            e.printStackTrace();
        }

        return new ModelAndView("doodles");
    }


    @RequestMapping(value=NEW_CHAPTER, method= RequestMethod.POST)
    public ModelAndView addChapter(@RequestParam String id,
                           @RequestParam String title,
                           @RequestParam String description,
                           @RequestParam String flow,
                           @RequestPart MultipartFile chapterImage,
                           @RequestHeader String referer){

        System.out.println("ID : " + id);
        System.out.println("Title : " + title);
        System.out.println("Description : " + description);
        System.out.println("Flow : " + flow);
        System.out.println("Image Content : " + chapterImage.getContentType());

         byte[] bytes;
        try {
            bytes = chapterImage.getBytes();
            System.out.println("Chapter Img length : " + bytes.length);
            System.out.println("Chapter Img : " + bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Referer: " + referer);

        String redirect = "homepage";

/*
        try {
            Chapter chapter = new ChapterCreater(title, id, description).createEntity(new NoWork<Chapter>());
        } catch (CreateException e) {
            redirect = referer;
            e.printStackTrace();
        }
*/
        return new ModelAndView(redirect);
    }

}
