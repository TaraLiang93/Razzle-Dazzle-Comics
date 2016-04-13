package com.rdc.create;

import com.model.ScribbleModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class SeriesController {

    @RequestMapping(value="/create/series/updateDescription", method= RequestMethod.POST)
    public ModelAndView updateSeriesDescr(@RequestParam String desc, HttpSession session){

        System.out.println("NEW DESCRIPTION: " + desc);


        return new ModelAndView("homepage");
    }

    @RequestMapping(value="/create/series/updateSeriesImage", method=RequestMethod.GET)
    public ModelAndView updateSeriesImg(@RequestParam String imgSrc, HttpSession session){
        System.out.println("New image: "+ imgSrc);
        return new ModelAndView("homepage");

    }


    public static final String NEW_SERIES = "/create/series/new";

    @RequestMapping(value=NEW_SERIES, method= RequestMethod.POST)
    public ModelAndView addSeries(@RequestParam String title,
                                   @RequestParam String author,
                                   @RequestParam String artist,
                                   @RequestParam String description,
                                   @RequestPart final MultipartFile seriesImage,
                                   @RequestHeader String referer){

        System.out.println("Title : " + title);
        System.out.println("Author : " + author);
        System.out.println("Artist : " + artist);
        System.out.println("Description : " + description);
        System.out.println("Image Content : " + seriesImage.getContentType());

        byte[] bytes;
        try {
            bytes = seriesImage.getBytes();
            System.out.println("Series Img length : " + bytes.length);
            System.out.println("Series Img : " + bytes);
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
    View view = new View() {
            @Override
            public String getContentType() {
                return "image/png";
            }

            @Override
            public void render(Map<String, ?> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
                ServletOutputStream sos = httpServletResponse.getOutputStream();
                sos.write(seriesImage.getBytes());
            }
        };

        return view;
        */
        return new ModelAndView(redirect);
    }

}
