package com.rdc.create;

import com.data.api.createables.ChapterCreater;
import com.data.api.createables.fillCommands.ChapterFillCommand;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.Updateable;
import com.data.api.queries.external.GetChapterByIDCommand;
import com.data.api.queries.external.GetSeriesByIDCommand;
import com.data.api.queries.external.GetTeamMembersOfChapterCommand;
import com.data.api.updatables.ChapterUpdater;
import com.data.api.updatables.SeriesUpdater;
import com.data.api.updatables.updateTasks.UpdateChapterAddTeamMemberTask;
import com.data.api.updatables.updateTasks.UpdateChapterEditInfoTask;
import com.data.api.updatables.updateTasks.UpdateChapterRemoveTeamMemberTask;
import com.data.api.updatables.updateTasks.UpdateSeriesAddChapterTask;
import com.data.creation.Chapter;
import com.data.creation.Page;
import com.data.creation.PublishedPage;
import com.data.structure.FlowTask;
import com.data.structure.TeamMember;
import com.google.appengine.api.blobstore.*;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class ChapterController {

    public static final String NEW_CHAPTER = "/create/chapter/new";
    public static final String LOAD_CHAPTER = "/create/chapter/load/{id}";
    public static final String LOAD_NEW_CHAPTER = "/create/chapter/new/load";
    public static final String UPDATE_CHAPTER_INFO = "/create/chapter/updateChapterInfo";


    @RequestMapping(value = LOAD_NEW_CHAPTER, method = RequestMethod.GET)
    public String getImageUploadUrl(ModelMap map) {
        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        map.put("uploadAction",blobstoreService.createUploadUrl(NEW_CHAPTER));
        return "newChapterModal";
    }

    @RequestMapping(value = LOAD_CHAPTER, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loadChapter(@PathVariable String id, @RequestHeader String referer, ModelMap map) {

        try {
            Container<Chapter> chapterContainer = new GetChapterByIDCommand(id).fetch();
            Chapter chapter = chapterContainer.getResult();

            Readable<TeamMember> teamMemberReadable = new GetTeamMembersOfChapterCommand(id);
            List<TeamMember> teamMembers = teamMemberReadable.fetch().getList();

            List<FlowTask> tasks = chapter.getFlow().getFlowTasks();


            Page page1 = new Page("Page 1", "Summary 1 Summary 1Summary 1Summary 1Summary 1Summary 1Summary 1Summary 1Summary 1Summary 1Summary 1" +
                    "Summary 1Summary 1Summary 1Summary 1Summary 1Summary 1Summary 1Summary 1Summary 1Summary 1",

                    new Date(), null, null, 0, tasks.get(0).getKey());
            Page page2 = new Page("Page 2", "Summary 2", new Date(), null, null, 0, tasks.get(0).getKey());
            Page page3 = new Page("Page 3", "Summary 3", new Date(), null, null, 0, tasks.get(2).getKey());
            Page page4 = new Page("Page 4", "Summary 4", new Date(), null, null, 0, tasks.get(3).getKey());
            Page page5 = new Page("Page 5", "Summary 5", new Date(), null, null, 0, tasks.get(4).getKey());

            List<Page> pages = new ArrayList<>();
            pages.add(page1);
            pages.add(page2);
            pages.add(page3);
            pages.add(page4);
            pages.add(page5);
            //chapter.getPageList().add(page1.getKey());
            //chapter.getPageList().add(page2.getKey());

            map.put("chapter", chapter);
            map.put("chapterId", id);
            map.put("teamMembers",teamMembers);
            map.put("pages", pages);

            BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
            map.put("uploadAction",blobstoreService.createUploadUrl(UPDATE_CHAPTER_INFO));

        } catch (FetchException e) {
            e.printStackTrace();
            return new ModelAndView(referer);
        }

        return new ModelAndView("chapterPage");
    }

    @RequestMapping(value="/create/chapter/addMember", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addTeam(@RequestParam String chapterID, @RequestParam String newMemeber, HttpSession session){
        try{
            System.out.println("newMemeber = " + newMemeber);
            Updateable<Chapter> chapterUpdater = new ChapterUpdater();
            Readable<Chapter> chapterReadable = new GetChapterByIDCommand(chapterID);
            chapterUpdater.updateEntity(chapterReadable, new UpdateChapterAddTeamMemberTask(newMemeber));
        } catch(UpdateException | FetchException | CreateException e){
            e.printStackTrace();
        }
    }


    @RequestMapping(value="/create/chapter/removeMember", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void removeTeamMember(@RequestParam String chapterID, @RequestParam String removeMember, HttpSession session){
        try{
            Updateable<Chapter> chapterUpdater = new ChapterUpdater();
            Readable<Chapter> chapterReadable = new GetChapterByIDCommand(chapterID);
            chapterUpdater.updateEntity(chapterReadable, new UpdateChapterRemoveTeamMemberTask(removeMember));
        } catch(UpdateException | FetchException | CreateException e){
            e.printStackTrace();
        }
    }


    @RequestMapping(value=NEW_CHAPTER, method= RequestMethod.POST)
    public ModelAndView addChapter(@RequestParam String chapterID,
                                   @RequestParam String title,
                                   @RequestParam String description,
                                   @RequestParam String flow,
                                   @RequestParam String seriesID,
                                   @RequestHeader String referer,
                                   HttpServletRequest req,
                                   ModelMap map){

        String redirect = "chapterPage";

        System.out.println("Title : " + title);
        System.out.println("Description : " + description);
        System.out.println("Flow : " + flow);
        System.out.println("Series ID : " + seriesID);

        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
        List<BlobKey> blobKeys = blobs.get("chapterImage");


        if (blobKeys == null) {
            System.out.println("Why you null BlobKeys?");
            redirect = referer; //Go back from whence thee came
        }
        else {
            if(blobKeys.size() == 0){
                System.out.println("There should be a default Image here");
                redirect = referer;
            }
            else{

                    BlobKey blobKey = blobKeys.get(0); // Get the first one
                    BlobInfoFactory blobInfoFactory = new BlobInfoFactory();
                    BlobInfo info = blobInfoFactory.loadBlobInfo(blobKey);
                    System.out.println("Content Type : "+info.getContentType());
                    System.out.println("Image FileName : "+info.getFilename());
                    BlobKey key = info.getBlobKey();

                try {
                    Chapter chapter = new ChapterCreater(UserServiceFactory.getUserService().getCurrentUser(),
                                                    title, chapterID, description).createEntity(new ChapterFillCommand(key));
                    new SeriesUpdater()
                            .updateEntity(
                                          new GetSeriesByIDCommand(seriesID),
                                          new UpdateSeriesAddChapterTask(chapter));
                    map.put("chapter", chapter);
                    redirect="redirect:/create/chapter/load/" + chapter.getChapterId();
                } catch (CreateException | FetchException | UpdateException e) {
                    redirect = referer;
                    e.printStackTrace();
                }
            }

        }




        return new ModelAndView(redirect);
    }

    @RequestMapping(value=UPDATE_CHAPTER_INFO, method=RequestMethod.POST)
    @ResponseBody
    public ModelAndView updateChapterInfo(@RequestParam String chapterID,
                                          @RequestParam String chapterString,
                                          @RequestParam String chapterTitle,
                                          @RequestParam String description,
                                         HttpServletRequest req,
                                        HttpSession session){

        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
        List<BlobKey> blobKeys = blobs.get("imgSrc");

        System.out.printf("Updating Chapter with ID:%s { Title: %s, Chapter String: %s, Description: %s}\n", chapterID, chapterTitle, chapterString, description);

        try {
            new ChapterUpdater().updateEntity(new GetChapterByIDCommand(chapterID),
                                            new UpdateChapterEditInfoTask(chapterTitle,
                                                                            chapterString,
                                                                            description,
                                                                            (blobKeys != null && blobKeys.size() > 0)? blobKeys.get(0) : null));
        } catch (FetchException | UpdateException | CreateException e) {
            e.printStackTrace();
        }


        return new ModelAndView("forward:/create/chapter/load/" + chapterID);

    }

    @RequestMapping(value="/create/chapter/getTeam", method=RequestMethod.GET)
    @ResponseBody
    public JSONObject getTeam(HttpServletRequest req, String chapter){

        return null;
    }

    @RequestMapping(value="/read/{seriesName}/{id}", method = RequestMethod.GET)
    public ModelAndView readChapter(@PathVariable String seriesName, @PathVariable String id,ModelMap map){



        Readable<PublishedPage> publishedPageReadable = null;
        try {
            PublishedPage page  = publishedPageReadable.fetch().getResult();

        } catch (FetchException e) {
            e.printStackTrace();
        }


        return new ModelAndView("readAChapter");
    }

}
