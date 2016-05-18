package com.rdc.create;

import com.data.UserData;
import com.data.api.createables.ChapterCreater;
import com.data.api.createables.fillCommands.ChapterFillCommand;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.Updateable;
import com.data.api.queries.external.*;
import com.data.api.updatables.ChapterUpdater;
import com.data.api.updatables.SeriesUpdater;
import com.data.api.updatables.UserDataUpdater;
import com.data.api.updatables.updateTasks.*;
import com.data.creation.Chapter;
import com.data.creation.PublishedPage;
import com.data.structure.FlowTask;
import com.data.structure.Series;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
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
        map.put("uploadAction", blobstoreService.createUploadUrl(NEW_CHAPTER));
        return "newChapterModal";
    }

    @RequestMapping(value = LOAD_CHAPTER, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loadChapter(@PathVariable String id, @RequestHeader String referer, HttpServletRequest req, ModelMap map) {

        try {
            String seriesID;
            if((seriesID=req.getParameter("seriesID")) != null){
                map.put("seriesID",seriesID);
            }
            Container<Chapter> chapterContainer = new GetChapterByIDCommand(id).fetch();
            Chapter chapter = chapterContainer.getResult();

            Readable<TeamMember> teamMemberReadable = new GetTeamMembersOfChapterCommand(id);
            List<TeamMember> teamMembers = teamMemberReadable.fetch().getList();

            List<FlowTask> tasks = chapter.getFlow().getFlowTasks();

            map.put("chapter", chapter);
            map.put("chapterId", id);
            map.put("teamMembers",teamMembers);
  //          map.put("pages", pages);

            BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
            map.put("uploadAction", blobstoreService.createUploadUrl(UPDATE_CHAPTER_INFO));

        } catch (FetchException e) {
            e.printStackTrace();
            return new ModelAndView(referer);
        }

        return new ModelAndView("chapterPage");
    }

    @RequestMapping(value = "/create/chapter/addMember", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addTeam(@RequestParam String chapterID, @RequestParam String newMemeber, @RequestParam String seriesID, HttpSession session){
        try{
            System.out.println("newMemeber = " + newMemeber);
            Updateable<Chapter> chapterUpdater = new ChapterUpdater();
            Readable<Chapter> chapterReadable = new GetChapterByIDCommand(chapterID);
            chapterUpdater.updateEntity(chapterReadable, new UpdateChapterAddTeamMemberTask(newMemeber));

            //should only add the series if the user doesn't have it
            Readable<UserData> getUserData= new GetUserDataByUserNameCommand(newMemeber);

            List<Series> series = getUserData.fetch().getResult().getSeries();

            HashSet<Long> seriesIDs = new HashSet<>();

            for(Series s :  series){
                seriesIDs.add(s.getSeriesID());
            }
            if(!seriesIDs.contains(Long.parseLong(seriesID))) {// if the user doesn't contain this series add it
                Updateable<UserData> userDataUpdateable = new UserDataUpdater();

                userDataUpdateable.updateEntity(getUserData, new UpdateUserDataAddSeriesTask(seriesID));
            }


        } catch(UpdateException | FetchException | CreateException e){
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/create/chapter/removeMember", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void removeTeamMember(@RequestParam String chapterID, @RequestParam String removeMember,  @RequestParam String seriesID, HttpSession session){
        try{
            Updateable<Chapter> chapterUpdater = new ChapterUpdater();
            Readable<Chapter> chapterReadable = new GetChapterByIDCommand(chapterID);
            chapterUpdater.updateEntity(chapterReadable, new UpdateChapterRemoveTeamMemberTask(removeMember));


            // if the user is in other chapters of this series a
            Readable<Series> seriesReadable = new GetSeriesByIDCommand(seriesID);

            Readable<UserData> getUserData= new GetUserDataByUserNameCommand(removeMember);

            List<Chapter> chapters = seriesReadable.fetch().getResult().getChapters();

            UserData userData = getUserData.fetch().getResult();

            HashSet<String> userIDSs = new HashSet<>();


            for(Chapter chapter : chapters)// checks to see if a user is in anymore chapters of that series
            {
                List<TeamMember> teamMembers = chapter.getTeamMembers();
                for(TeamMember member : teamMembers)
                {
                    userIDSs.add(member.getUserStringId());
                }

            }

            if(!userIDSs.contains(userData.getUserId())) {
                Updateable<UserData> userDataUpdateable = new UserDataUpdater();
                userDataUpdateable.updateEntity(getUserData, new UpdateUserDataRemoveSeriesTask(seriesID));
            }

        } catch(UpdateException | FetchException | CreateException e){
            e.printStackTrace();
        }
    }


    @RequestMapping(value = NEW_CHAPTER, method = RequestMethod.POST)
    public ModelAndView addChapter(@RequestParam String chapterID,
                                   @RequestParam String title,
                                   @RequestParam String description,
                                   @RequestParam String seriesID,
                                   @RequestHeader String referer,
                                   HttpServletRequest req,
                                   ModelMap map) {

        String redirect = "chapterPage";

        System.out.println("Title : " + title);
        System.out.println("Description : " + description);
        System.out.println("Series ID : " + seriesID);

        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
        List<BlobKey> blobKeys = blobs.get("chapterImage");

        BlobKey key = null;
        Chapter chapter;

        if (blobKeys != null) {
            BlobKey blobKey = blobKeys.get(0); // Get the first one
            BlobInfoFactory blobInfoFactory = new BlobInfoFactory();
            BlobInfo info = blobInfoFactory.loadBlobInfo(blobKey);
            System.out.println("Content Type : " + info.getContentType());
            System.out.println("Image FileName : " + info.getFilename());
            key = info.getBlobKey();
        }

        try {
            if(key != null) {
                chapter = new ChapterCreater(UserServiceFactory.getUserService().getCurrentUser(),
                        title, chapterID, description).createEntity(new ChapterFillCommand(key));
                new SeriesUpdater()
                        .updateEntity(
                                new GetSeriesByIDCommand(seriesID),
                                new UpdateSeriesAddChapterTask(chapter));
            }
            else{
                key = null;
                chapter = new ChapterCreater(UserServiceFactory.getUserService().getCurrentUser(),
                        title, chapterID, description).createEntity(new ChapterFillCommand(key));
                new SeriesUpdater()
                        .updateEntity(
                                new GetSeriesByIDCommand(seriesID),
                                new UpdateSeriesAddChapterTask(chapter));
            }

            map.put("chapter", chapter);
            redirect = "redirect:/create/chapter/load/" + chapter.getChapterId();
        } catch (CreateException | FetchException | UpdateException e) {
            redirect = referer;
            e.printStackTrace();
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

    @RequestMapping(value="/read/{seriesName}/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView readChapter(HttpServletResponse response, HttpServletRequest req, @PathVariable String seriesName, @PathVariable String id, @RequestParam String seriesID, ModelMap map){


        String currentPage;



        Readable<Chapter> chapterReadable = new GetChapterByIDCommand(id);
        Readable<Series> seriesReadable = new GetSeriesByIDCommand(seriesID);
        try {

            Chapter chapter = chapterReadable.fetch().getResult();
            Series series = seriesReadable.fetch().getResult();

            if( ( currentPage=req.getParameter("pageNum") ) == null ){
                currentPage = "0";
            }

            PublishedPage publishedPage = null;

            List<PublishedPage> publishedPages = chapter.getPublishedPages();

            for(PublishedPage page : publishedPages ){
                if(page.getIndex() == Integer.parseInt(currentPage) )
                {
                   publishedPage = page;
                    break;
                }
            }

            map.put("chapter",chapter);
            map.put("series",series);
            map.put("publishPage",publishedPage);

            Updateable<Series> seriesUpdatable = new SeriesUpdater();

            seriesUpdatable.updateEntity(seriesReadable, new UpdateSeriesIncrementViews(series.getSeriesID().toString()));


        } catch (FetchException e) {
            e.printStackTrace();
        } catch (CreateException e) {
            e.printStackTrace();
        } catch (UpdateException e) {
            e.printStackTrace();
        }
        try {
        response.getWriter().print("<script language='JavaScript'>loadAndDisabled($('#canvasID'), $('#loadCanvasJSON').val());</script>");
    } catch (IOException e) {
        e.printStackTrace();
    }
        return new ModelAndView("readAChapter");
    }

}
