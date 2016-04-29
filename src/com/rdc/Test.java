package com.rdc;

import com.data.UserData;
import com.data.api.createables.*;
import com.data.api.createables.fillCommands.*;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Createable;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.Updateable;
import com.data.api.queries.external.*;
import com.data.api.queries.internal.GetDoodleByKeyCommand;
import com.data.api.queries.internal.GetEntityFromKeyCommand;
import com.data.api.updatables.*;
import com.data.api.updatables.updateTasks.*;
import com.data.creation.*;
import com.data.structure.*;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.model.ChapterModel;
import com.model.PageModel;
import com.model.SceneModel;
import com.model.ScribbleModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by drodrigues on 3/19/16.
 */
@Controller
public class Test {


    @RequestMapping(value="/test", method= RequestMethod.GET)
    public ModelAndView test(HttpServletRequest req, ModelMap map){
        try {
            System.out.println("Test Is being run");

            UserService userSer = UserServiceFactory.getUserService();
            User currentUser = userSer.getCurrentUser();


            UserData data = new UserData();
            String nickName = "James";
            data.setNickName(nickName);

            ofy().save().entity(data).now();

            UserData user = ofy().load().type(UserData.class).filter("nickName", nickName).first().now();
            System.out.println("Found User : " + user);


            //initialize user james which will have a doodle
            UserData james = new UserData();
            ofy().save().entity(james).now(); // create and save so autogenerate Long userId which is needed to generate key

            //we can get the tag entity if we do an ancestor query on tag with a specific userKey as the parent
            Doodle doodleCat = new Doodle();
            doodleCat.setTitle("Cat Picture");
            ofy().save().entity(doodleCat).now(); // save Doodle so it generates Long doodleId which is needed to generate key

            //tag called "Cat"
            Tag catTag = new Tag();
            //set james as the parent of catTag
            catTag.setUserData(james.getKey());


            catTag.setName("Cat");
            ofy().save().entity(catTag).now(); // save Tag

            // Add catTag using the catTag's key
            doodleCat.addTagToList(catTag.getKey());
            ofy().save().entity(doodleCat).now(); // update doodleCat with the new tagList (maybe use Ref<> and we don't need to?)

            james.addDoodleToList(doodleCat.getKey());
            james.addTagToList(catTag.getKey());
            //save the entity james with updated list values
            ofy().save().entity(james).now();


            String tagName = "Cat";
            //ancestor query to find the Key<Tag> with the name "Cat" owned by Userdata james
            Query<Tag> tagAncestorQuery = ofy().load().type(Tag.class).ancestor(james.getKey()).filter("name", tagName);

            Tag tag = tagAncestorQuery.first().now();

            // get a list of doodles that match a tag
            // if a Doodle's taglist property has tag return that doodle
            List<Doodle> doodleList = ofy().load().type(Doodle.class).filter("tagList", tag.getKey()).list();

            for (Doodle d : doodleList) {
                System.out.println(d.getTitle());
            }

            Readable<Doodle> getDoodleWithTagTest = new GetDoodlesByTagCommand(tagName, james);
            Doodle dFetched = getDoodleWithTagTest.fetch().getResult();
            System.out.println("I did it " + dFetched.getTitle());

            System.out.println(doodleCat.getDoodleId());
//        Readable<Doodle> getDoodleWithID = new GetDoodlesByIDCommand(doodleCat.getDoodleId());
//        Doodle doodleWithID = getDoodleWithID.fetchById(doodleCat.getDoodleId());
//        System.out.println( "I did it " + doodleWithID.getTitle());
            Readable<Doodle> getDoodleWithID = new GetDoodlesByIDCommand(doodleCat.getDoodleId());
            Doodle doodleWithID = getDoodleWithID.fetch().getResult();
            System.out.println(" Fetch Doodle With ID Test has ID: " + doodleWithID + " and has title " + doodleWithID.getTitle());

        }
        catch ( Exception ex){
            ex.printStackTrace();
        }




        return new ModelAndView("test");

    }

    @RequestMapping(value="/test2", method= RequestMethod.GET)
    public ModelAndView test2(HttpServletRequest req, ModelMap map){
        try {
            UserData userData = new UserData();
            userData.setNickName("Charles Junior");
            ofy().save().entity(userData).now();

            Scribble scribble1 = new Scribble();
            scribble1.setTitle("I am Scribble One");
            ofy().save().entity(scribble1).now();

            Scribble scribble2 = new Scribble();
            scribble2.setTitle("I am Scribble Two");
            ofy().save().entity(scribble2).now();

            Scribble scribble3 = new Scribble();
            scribble3.setTitle("I am Scribble Three");
            ofy().save().entity(scribble3).now();

            userData.addScribbleToList(scribble1.getKey());
            userData.addScribbleToList(scribble2.getKey());
            userData.addScribbleToList(scribble3.getKey());

            ofy().save().entity(userData).now();


            // this tells the test2.JSP that "user" refers to userData a UserData instance

            Tag tag1 = new Tag();
            tag1.setName("I am tag 1");
            tag1.setUserData(userData.getKey());
            ofy().save().entity(tag1).now();

            Tag tag2 = new Tag();
            tag2.setName("I am tag 2");
            tag2.setUserData(userData.getKey()); // set parent using setUserData
            ofy().save().entity(tag2).now();

            Tag tag3 = new Tag();
            tag3.setName("I am tag 3");
            tag3.setUserData(userData.getKey());
            ofy().save().entity(tag3).now();

            userData.addTagToList(tag1.getKey());
            userData.addTagToList(tag2.getKey());
            userData.addTagToList(tag3.getKey());

            ofy().save().entity(userData).now();


            Doodle doodle1 = new Doodle();
            doodle1.setTitle("I am Doodle 1");
            ofy().save().entity(doodle1).now();

            Doodle doodle2 = new Doodle();
            doodle2.setTitle("I am Doodle 2");
            ofy().save().entity(doodle2).now();

            Doodle doodle3 = new Doodle();
            doodle3.setTitle("I am Doodle 3");
            ofy().save().entity(doodle3).now();

            userData.addDoodleToList(doodle1.getKey());
            userData.addDoodleToList(doodle2.getKey());
            userData.addDoodleToList(doodle3.getKey());
            ofy().save().entity(userData);

            map.put("user", userData);

            System.out.println(" The name of the owner of " + tag1.getName() + " is " + tag1.getUserData().getNickName());

            TagCreater tagCreater = new TagCreater("I am tagicus", userData.getKey());
//        Tag t = tagCreater.createEntity(new NoWork<Tag>());

            Tag t = tagCreater.createEntity(new TagCommandFill());

            ScribbleCreater scribbleCreater = new ScribbleCreater("title");
            List<Key<Page>> pageList = new ArrayList<>();
           // Scribble scribble = scribbleCreater.createEntity(new ScribbleFillCommand(pageList));
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return new ModelAndView("test2");
    }


    @RequestMapping(value="/test3", method= RequestMethod.GET)
    public ModelAndView test3(HttpServletRequest req, ModelMap map){
        try{

        /**
         * testing using the creater objects
         */

        //created userdata
//        UserDataCreater userDataCreater = new UserDataCreater("jamezTez@gmail.com", "jamezTezNickName", "jamezTezID"); // fill userDataCreater with initial data
//        UserData james =  userDataCreater.createEntity(new UserDataFillCommand());

//        //create canvas
//        Canvas canvas = new Canvas();
//        ofy().save().entity(canvas).now();
        String canvasJSON = null;


        //created Doodle
        DoodleCreater doodleCreater = new DoodleCreater("I am DoodleCreater created");
        Doodle createdDoodle = doodleCreater.createEntity(new DoodleFillCommand(canvasJSON));


        //create Page
        Page page = new Page();
        ofy().save().entity(page).now();
        List<Key<Page>> pageList = new ArrayList<>();
        pageList.add(page.getKey());

        //createScribble
        ScribbleCreater scribbleCreater = new ScribbleCreater("I am ScribbleCreater created");
        //Scribble createdScribble =  scribbleCreater.createEntity(new ScribbleFillCommand(pageList));

//        james.addDoodleToList(createdDoodle.getKey());
//        james.addScribbleToList(createdScribble.getKey());
//        ofy().save().entity(james).now();

        /**
         * testing using readable objects
         */

        //get doodle by passing in the long id of the doodle
        Readable<Doodle> getDoodleReadable = new GetDoodlesByIDCommand(createdDoodle.getDoodleId());
        System.out.println( "Readable to fetch Doodle has title: " + getDoodleReadable.fetch().getResult().getTitle() );

        //get Scribble by passing in the long id of the scribble
        //Readable<Scribble> getScribbleReadable = new GetScribblesByIDCommand(createdScribble.getScribbleId());
       // System.out.println( "Readable to fetch Scribble has title " + getScribbleReadable.fetch().getResult().getTitle());

//        Readable<UserData> getUserdataReadable = new GetEntityFromKeyCommand<>(james.getKey());
//        UserData theUser = getUserdataReadable.fetch().getResult();

        //In this example there is only one scribble and Doodle so we just made sure we can retrieve the scribble and doodle
        // in actual implementation we would iterate throught the List<Scribble> and List<Doodle>
//        System.out.println( "Readable to fetch UserData has Scribble with title: " + theUser.getScribbles().get(0).getTitle() +
//                " and Doodle with title: " + theUser.getDoodles().get(0).getTitle());


        /**
         * Test updating using Updatable
         */


        //update Doodle
        Updateable<Doodle> updateDoodle = new DoodleUpdater();
                //front end will have the id of the doodle to get
        Readable<Doodle> getDoodle = new GetDoodlesByIDCommand(createdDoodle.getDoodleId());
        Doodle theDoodle = getDoodle.fetch().getResult();
        String updatedDoodleTitle = "I updated Doodle title yo";

        // update Doodle entity
        try {
            String canvas = null;
            updateDoodle.updateEntity(getDoodle, new UpdateDoodleTask(updatedDoodleTitle,
                    theDoodle.getDescription(), canvas)
            );
        }
        catch ( FetchException| UpdateException ex){
            ex.printStackTrace();
        }

        //update Scribble

        Updateable<Scribble> updateScribble = new ScribbleUpdater();
                // frontend will have the scribble id of the scribble to get
        //Readable<Scribble> getScribble = new GetScribblesByIDCommand(createdScribble.getScribbleId());
        //Scribble theScribble = getScribble.fetch().getResult();
        String updatedScribbleTitle = "I updated Scribble title yo";

//        //update scribble
//        try {
//            updateScribble.updateEntity(getScribble,
//                    new UpdateScribbleTask(updatedScribbleTitle,
//                            theScribble.getDescription(),
//                            theScribble.getPageList())
//            );
//        }
//        catch ( FetchException| UpdateException ex){
//            ex.printStackTrace();
//        }

//        //Update the UserData
//        Updateable<UserData> updateUserData = new UserDataUpdater();
//        Readable<UserData> getUserDataWithReadableTest = new GetEntityFromKeyCommand<>(james.getKey());
//        updateUserData.updateEntity( new UpdateUserDataTask( ) );


        /**
         * Using Readable<T> testif Updatable actually changed the values
         */

        //get doodle by passing in the long id of the doodle
        Readable<Doodle> getDoodleReadable2 = new GetDoodlesByIDCommand(createdDoodle.getDoodleId());
        System.out.println( "Updated Doodle has title: " + getDoodleReadable.fetch().getResult().getTitle() );

        //get Scribble by passing in the long id of the scribble
        //Readable<Scribble> getScribbleReadable2 = new GetScribblesByIDCommand(createdScribble.getScribbleId());
       // System.out.println( "Updated Scribble has title " + getScribbleReadable.fetch().getResult().getTitle());

//        Readable<UserData> getUserdataReadable2 = new GetEntityFromKeyCommand<>(james.getKey());
//        UserData theUser2 = getUserdataReadable.fetch().getResult();

        //In this example there is only one scribble and Doodle so we just made sure we can retrieve the scribble and doodle
        // in actual implementation we would iterate throught the List<Scribble> and List<Doodle>
//        System.out.println( "Updated UserData has Scribble with title: " + theUser2.getScribbles().get(0).getTitle() +
//                " and Doodle with title: " + theUser2.getDoodles().get(0).getTitle());


        System.out.println("testing fetch doodle by key");
        Readable<Doodle> getDoodleByKey = new GetDoodleByKeyCommand(createdDoodle.getKey());
        System.out.println( " Doodle fetched by key has title: " + getDoodleByKey.fetch().getResult().getTitle() );


        /**
         * Test user data creatable
         */

        //user userService to login

        UserService userService = UserServiceFactory.getUserService();
        String thisURL = req.getRequestURI();
        userService.createLoginURL(thisURL);
        User user = userService.getCurrentUser();


        //
        Createable<UserData> userDataCreateable = new UserDataCreater(user);
        UserData userData = userDataCreateable.createEntity(new UserDataFillCommand());
        System.out.println(" the nickname: " + userData.getNickName() + " the UserId: "  +
                        userData.getUserId() + " the UserName: " + userData.getUserName());


//        map.put("user", james);

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ModelAndView("test3");
    }


    @RequestMapping(value="/test4", method= RequestMethod.GET)
    public ModelAndView loginScreen(HttpSession session, ModelMap map) {

        /**
         * Testing if Userdata can be filled in from google's User object
         */

        try{


        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if (user != null) {
            //return new ModelAndView("redirect:"+LOGIN_SUCCESS);
        }

        if (session.getAttribute("loginURL") == null) {
            session.setAttribute("logoutURL", userService.createLoginURL("/test3"));
        }
//        session.removeAttribute(USER);
//        session.removeAttribute(IS_AUTH);
        Createable<UserData> userDataCreateable = new UserDataCreater(user);
        UserData userData = userDataCreateable.createEntity(new UserDataFillCommand());
        System.out.println(" the nickname: " + userData.getNickName() + " the UserId: " +
                userData.getUserId() + " the UserName: " + userData.getUserName());


        /**
         * Test updating userDataUpdater
         */
        Updateable<UserData> userDataUpdater = new UserDataUpdater();
        Readable<UserData> getUserDataReadable = new GetUserDataByIDCommand(userData.getUserId());

        String newLocation = "I'm located at new location";
        String newDescription = "New description describes me newly ";

        // Updating user location and Description
            userDataUpdater.updateEntity(getUserDataReadable,
                    new UpdateUserDataTask(getUserDataReadable.fetch().getResult().getNickName(),
                            newLocation, newDescription)
            );

        System.out.println("UserData has nickname: " + userData.getNickName() + " and location: "
                + userData.getLocation() + " and description: " + userData.getDescription());

        //Updating user Doodle
        Updateable<UserData> userDataUpdater2 = new UserDataUpdater();

//        String canvasJSONString = "canvas JSOn String";
//        Canvas canvas = new Canvas();
//        ofy().save().entity(canvas).now();

        String canvasJSON = "canvasString"; // on front end it wouldn't be null SHOWJASON

        Createable<Doodle> anotherDoodleCreater = new DoodleCreater("title", "description");
        Doodle anotherDoodle = anotherDoodleCreater.createEntity(new DoodleFillCommand(canvasJSON));

        Updateable<Doodle> doodleUpdater = new DoodleUpdater();
        Readable<Doodle> getDoodle = new GetDoodlesByIDCommand(anotherDoodle.getDoodleId());

        try {
            String canvas = "canvasString Updated";
            doodleUpdater.updateEntity(getDoodle,
                    new UpdateDoodleTask("updated doodle title",
                            "describing doodle", canvas)
            );
        } catch (FetchException | UpdateException ex) {
            ex.printStackTrace();
        }
            System.out.println("The sample JSOn is" + anotherDoodle.getCanvas().getCanvasImage()  );;



        Readable<UserData> userDataReadable = new GetUserDataByIDCommand(userData.getUserId());

        try{
            userDataUpdater2.updateEntity(userDataReadable, new UpdateUserDataTask(anotherDoodle));
        }
        catch( FetchException| UpdateException ex){
            ex.printStackTrace();

        }

            /**
             * Loading a Doodle using GetDoodlesOfUserDataCommand
             */

            Readable<Doodle> getUserDoodles = new GetDoodlesOfUserDataCommand( user );
            List<Doodle> doodleList = getUserDoodles.fetch().getList();
            System.out.println("before for loop");
            for (Doodle d : doodleList){
                System.out.println(" DoodleID: " + d.getDoodleId() + " title: " + d.getTitle());
            }
            System.out.println("after for loop");



        for( Doodle d : userData.getDoodles()){
            System.out.println( "Doodle title is: " + d.getTitle() + " and description is: " + d.getDescription());
        }

        //Updating user scribble

        Createable<Scribble> scribbleCreateable = new ScribbleCreater(" Scribble Title bro ", " Scribble description bro ");

        List<Key<Page>> pageList = new ArrayList<>();

        //Scribble scribble = scribbleCreateable.createEntity(new ScribbleFillCommand(pageList));


        Updateable<UserData> userDataUpdater3 = new UserDataUpdater();
        Readable<UserData> getUserData3 = new GetEntityFromKeyCommand<>( userData.getKey());
        /*try {
            userDataUpdater3.updateEntity(getUserData3, new UpdateUserDataTask(scribble));
        }
        catch( FetchException| UpdateException ex){
            ex.printStackTrace();

        }
*/
        for(Scribble scrib : userData.getScribbles()){
            System.out.println( "Scribble Title is: " + scrib.getTitle() + " and description is: " + scrib.getDescription());
        }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }



        return new ModelAndView("test3");

    }


    @RequestMapping(value="/testScribbles", method= RequestMethod.GET)
    public ModelAndView testScribbles(HttpSession session, ModelMap map)  {
        /**
         * testing Scribbles
         */
        try {
            /**
             * Page 1
             */

            SceneModel page1Scene1 =  new SceneModel();
            page1Scene1.setSetting("page1Scene1 setting");
            page1Scene1.setTinyMCEText("<p>page1Scene1 TinyMCText</p>");

            SceneModel page1Scene2 =  new SceneModel();
            page1Scene2.setSetting("page1Scene2 setting");
            page1Scene2.setTinyMCEText("<p>page1Scene2 TinyMCText</p>");

            List<SceneModel> sceneModelListForPage1 = new ArrayList<>();
            sceneModelListForPage1.add(page1Scene1);
            sceneModelListForPage1.add(page1Scene2);

            PageModel page1Model = new PageModel();
            page1Model.setScenes(sceneModelListForPage1);

            //Make a Page
            Createable<Page> pageCreater1 = new PageCreater();
            Page pageCreated1 = pageCreater1.createEntity(new PageModelFillCommand(page1Model));

            /**
             * Page 2
             */

            SceneModel page2Scene1 =  new SceneModel();
            page2Scene1.setSetting("page2Scene1 setting");
            page2Scene1.setTinyMCEText("<p>age2Scene1 TinyMCText</p>");

            SceneModel page2Scene2 =  new SceneModel();
            page2Scene2.setSetting("page2Scene2 setting");
            page2Scene2.setTinyMCEText("<p>page2Scene2 TinyMCText</p>");

            List<SceneModel> sceneModelListForPage2 = new ArrayList<>();
            sceneModelListForPage2.add(page2Scene1);
            sceneModelListForPage2.add(page2Scene2);

            PageModel page2Model = new PageModel();
            page2Model.setScenes(sceneModelListForPage2);

            //Make a Page
            Createable<Page> pageCreater2 = new PageCreater();
            Page pageCreated2 = pageCreater2.createEntity(new PageModelFillCommand(page2Model));

            /**
             * pageList of the 2 pages
             */
            List<Key<Page>> pageList = new ArrayList<>();
            pageList.add(pageCreated1.getKey());
            pageList.add(pageCreated2.getKey());

            List<PageModel> mList = new ArrayList<>();
            mList.add(page1Model);
            mList.add(page2Model);
            ScribbleModel model = new ScribbleModel();
            model.setPages(mList);
            model.setTitle("Test");
            model.setDescription("Test");

            Createable<Scribble> scribbleCreater = new ScribbleCreater("Scribble Title", "Description");
            Scribble scribble = scribbleCreater.createEntity(new ScribbleFillCommand(model));

            if(scribble !=null){
                UserService service = UserServiceFactory.getUserService();
                User user = service.getCurrentUser();

                if(user != null){
                    try {
//                        new Updateable().updateEntity(new GetUserDataByUserCommand(user), new UpdateUserAddScribbleTask(scribble));
                        Readable<UserData> getUserData = new GetUserDataByIDCommand( user.getUserId());
                        UserData userData = getUserData.fetch().getResult();
                        //if userData null create first
                        if( userData == null){
                            Createable<UserData> userDataCreater = new UserDataCreater(user);
                            userDataCreater.createEntity( new UserDataFillCommand());
                        }
                            //now update the UserData
                            Updateable<UserData> userDataUpdater = new UserDataUpdater();
                            Readable<UserData> userDataReadable = new GetUserDataByUserCommand(user);
                            userDataUpdater.updateEntity(userDataReadable, new UpdateUserAddScribbleTask(scribble));
                            System.out.println("Successful update!");

                    } catch (FetchException e) {
                        e.printStackTrace();
                    } catch (UpdateException e) {
                        e.printStackTrace();
                    }
                }
            }
            UserService service = UserServiceFactory.getUserService();
            User user = service.getCurrentUser();
            Readable<UserData> getUserData = new GetUserDataByUserCommand(user);
            UserData userData = getUserData.fetch().getResult();
            for( Scribble scrib : userData.getScribbles() ){

                System.out.println("Scribble ID: " + scrib.getScribbleId());
                System.out.println("Scribble Title: " + scrib.getTitle() );
                System.out.println("Scribble Description: " + scrib.getDescription());

                for( Page page : scrib.getPages()){
                    System.out.println( "-Page ID: " + page.getId() );
                    for( Scene scene : page.getScenes()){
                        System.out.println( "--Scene Id: " +scene.getId() ) ;
                        System.out.println( "--Scene Setting: " + scene.getSetting() );
                        System.out.println( "--Scene TinyMCEText: " + scene.getTinyMCEText() );
                    }
                }
            }

        }
        catch (CreateException | FetchException ex){
            ex.printStackTrace();
        }
        return new ModelAndView("test3");
    }


    @RequestMapping(value="/testSeries", method= RequestMethod.GET)
    public ModelAndView testSeries(HttpSession session, ModelMap map)  {
        try{
            //create a series
            Createable<Series> seriesCreater = new SeriesCreater( null, "Series Title Mang", "Series Description Mang", false);
            Series seriesOne = seriesCreater.createEntity( new SeriesFillCommand(null));

            // create a chapter
            Createable<Chapter> chapterCreater = new ChapterCreater(UserServiceFactory.getUserService().getCurrentUser(),"Chapter Title Mang",
                    "Chapter ChapterString Mang", "Chapter description Mang");
            Chapter chapterOne = chapterCreater.createEntity( new ChapterFillCommand());

            //create scene Models
            SceneModel sceneModelOne = new SceneModel();
            sceneModelOne.setTinyMCEText("SceneModel TinyMCEText One");
            sceneModelOne.setSetting("SceneModel Setting One");

            SceneModel sceneModelTwo = new SceneModel();
            sceneModelTwo.setTinyMCEText("SceneModel TinyMCEText Two");
            sceneModelTwo.setSetting("SceneModel Setting Two");


            //create a page Models
            PageModel pageModelOne = new PageModel();

            //SceneList
            List<SceneModel> sceneModelList = new ArrayList<>();
            sceneModelList.add(sceneModelOne);
            sceneModelList.add(sceneModelTwo);

            //page has scene models
            pageModelOne.setScenes(sceneModelList);

            //Chapter Model
            ChapterModel chapterModel = new ChapterModel();
            chapterModel.setTitle("Chapter title mang Updated");
            chapterModel.setDescription("Chapter description mang Updated");

            //PageModelList
            List<PageModel> pageModelList = new ArrayList<>();
            pageModelList.add(pageModelOne);

            //Chapter has PagesModels
            chapterModel.setPageList(pageModelList);


            //Create page
            Createable<Page> pageCreater = new PageCreater();
            Page page = pageCreater.createEntity( new PageModelFillCommand(pageModelOne));


            //Update Chapter with page
            Updateable<Chapter> chapterUpdater = new ChapterUpdater();
            Readable<Chapter> chapterReadable = new GetChapterByIDCommand(chapterOne.getChapterId());
            Chapter chapterFetch = chapterReadable.fetch().getResult();
            chapterUpdater.updateEntity(chapterReadable, new UpdateChapterTask( chapterModel ));
            chapterUpdater.updateEntity(chapterReadable, new UpdateChapterTask( chapterModel ));

            System.out.println( chapterOne.getDescription() );
            System.out.println( chapterOne.getTitle() );
            System.out.println( chapterOne.getPublished() );

            //Update Series with chapter
            Updateable<Series> seriesUpdateable2 = new SeriesUpdater();
            Readable<Series> seriesReadable2 = new GetSeriesByIDCommand( seriesOne.getSeriesID()); // make series readable
            seriesUpdateable2.updateEntity(seriesReadable2, new UpdateSeriesAddChapterTask( chapterOne.getChapterId() ));

            /**
             * update series visibility
             */
            System.out.println( seriesOne.isPublished() );
            Updateable<Series> seriesUpdateable = new SeriesUpdater();
            Readable<Series> seriesReadable = new GetSeriesByIDCommand(seriesOne.getSeriesID());
            seriesUpdateable.updateEntity(seriesReadable, new UpdateSeriesToggleVisibilityTask());
            System.out.println( seriesOne.isPublished());




            /**
             * Test: Create Default Flow, add flowTasks and assign FlowTypes
             */
            Createable<Flow> defaultFlowCreater = new FlowCreater( "defaultFlow");
            Flow defaultFlow = defaultFlowCreater.createEntity( new FlowFillCommand() );

            //Create the FlowTypes
            Createable<FlowType> flowTypeCreateable = new FlowTypeCreater("Write Type");
            FlowType writeFlowType = flowTypeCreateable.createEntity( new FlowTypeFillCommand( ) );

            //Create the FlowTypes
            Createable<FlowType> flowTypeCreateable2 = new FlowTypeCreater("PreDraw Type");
            FlowType preDrawFlowType = flowTypeCreateable2.createEntity( new FlowTypeFillCommand( ) );

            //Create the FlowTypes
            Createable<FlowType> flowTypeCreateable3 = new FlowTypeCreater("Draw Type");
            FlowType drawFlowType = flowTypeCreateable3.createEntity( new FlowTypeFillCommand( ) );

            //Create the FlowTypes
            Createable<FlowType> flowTypeCreateable4 = new FlowTypeCreater("Review Type");
            FlowType reviewFlowType = flowTypeCreateable4.createEntity( new FlowTypeFillCommand( ) );

            //Create the FlowTypes
            Createable<FlowType> flowTypeCreateable5 = new FlowTypeCreater("Done Type");
            FlowType doneFlowType = flowTypeCreateable5.createEntity( new FlowTypeFillCommand( ) );


            //Create FlowTask0
            Createable<FlowTask> flowTaskCreateable0 = new FlowTaskCreater( "Write Task", 0);
            FlowTask flowTask0 = flowTaskCreateable0.createEntity( new FlowTaskFillCommand());
            //Update FlowTask with correct Type
            Updateable<FlowTask> flowTaskUpdateable0 = new FlowTaskUpdater();
            Readable<FlowTask> flowTaskReadable0 = new GetFlowTaskByIDCommand(flowTask0.getFlowTaskId());
            flowTaskUpdateable0.updateEntity(flowTaskReadable0, new UpdateFlowTaskSetTypeTask(writeFlowType.getFlowTypeId()));
//            flowTask0.setFlowTypeKey(writeFlowType.getKey());

            //Create FlowTask1
            Createable<FlowTask> flowTaskCreateable1 = new FlowTaskCreater( "PreDraw Task", 1);
            FlowTask flowTask1 = flowTaskCreateable1.createEntity( new FlowTaskFillCommand());
            //Update FlowTask with correct Type
            Updateable<FlowTask> flowTaskUpdateable1 = new FlowTaskUpdater();
            Readable<FlowTask> flowTaskReadable1 = new GetFlowTaskByIDCommand(flowTask1.getFlowTaskId());
            flowTaskUpdateable1.updateEntity(flowTaskReadable1, new UpdateFlowTaskSetTypeTask(preDrawFlowType.getFlowTypeId()));
//            flowTask1.setFlowTypeKey( preDrawFlowType.getKey() );

            //Create FlowTask2
            Createable<FlowTask> flowTaskCreateable2 = new FlowTaskCreater( "Draw Task", 2);
            FlowTask flowTask2 = flowTaskCreateable2.createEntity( new FlowTaskFillCommand());
            //Update FlowTask with correct Type
            Updateable<FlowTask> flowTaskUpdateable2 = new FlowTaskUpdater();
            Readable<FlowTask> flowTaskReadable2 = new GetFlowTaskByIDCommand(flowTask2.getFlowTaskId());
            flowTaskUpdateable2.updateEntity(flowTaskReadable2, new UpdateFlowTaskSetTypeTask(drawFlowType.getFlowTypeId()));
//            flowTask2.setFlowTypeKey( drawFlowType.getKey() ); // how do we want to set the Flow Type?

            //Create FlowTask3
            Createable<FlowTask> flowTaskCreateable3 = new FlowTaskCreater( "Review Task", 3);
            FlowTask flowTask3 = flowTaskCreateable3.createEntity( new FlowTaskFillCommand());
            //Update FlowTask with correct Type
            Updateable<FlowTask> flowTaskUpdateable3 = new FlowTaskUpdater();
            Readable<FlowTask> flowTaskReadable3 = new GetFlowTaskByIDCommand( flowTask3.getFlowTaskId() );
            flowTaskUpdateable3.updateEntity( flowTaskReadable3, new UpdateFlowTaskSetTypeTask(reviewFlowType.getFlowTypeId()));
//            flowTask3.setFlowTypeKey( reviewFlowType.getKey() );


            //Create FlowTask4
            Createable<FlowTask> flowTaskCreateable4 = new FlowTaskCreater( "Done Task", 4);
            FlowTask flowTask4 = flowTaskCreateable4.createEntity( new FlowTaskFillCommand());
            //Update FlowTask with correct Type
            Updateable<FlowTask> flowTaskUpdateable4 = new FlowTaskUpdater();
            Readable<FlowTask> flowTaskReadable4 = new GetFlowTaskByIDCommand(flowTask4.getFlowTaskId());
            flowTaskUpdateable4.updateEntity(flowTaskReadable4, new UpdateFlowTaskSetTypeTask(doneFlowType.getFlowTypeId()) );
//            flowTask4.setFlowTypeKey( doneFlowType.getKey() );


            //Update Flow with FlowTasks
            Updateable<Flow> flowUpdateable = new FlowUpdater();
            Readable<Flow> flowReadable = new GetFlowByIDCommand(defaultFlow.getFlowId());
            flowUpdateable.updateEntity(flowReadable, new UpdateFlowAddFlowTask( flowTask0.getFlowTaskId() ));
            flowUpdateable.updateEntity(flowReadable, new UpdateFlowAddFlowTask( flowTask1.getFlowTaskId() ));
            flowUpdateable.updateEntity(flowReadable, new UpdateFlowAddFlowTask( flowTask2.getFlowTaskId() ));
            flowUpdateable.updateEntity(flowReadable, new UpdateFlowAddFlowTask( flowTask3.getFlowTaskId() ));
            flowUpdateable.updateEntity(flowReadable, new UpdateFlowAddFlowTask( flowTask4.getFlowTaskId() ));

            //Set the next and previous flows
            // (Do this internally if one flow?)
            flowTask0.setNextTask(flowTask1.getKey());

            flowTask1.setPrevTask(flowTask0.getKey());
            flowTask1.setNextTask(flowTask2.getKey());

            flowTask2.setPrevTask(flowTask1.getKey());
            flowTask2.setNextTask(flowTask3.getKey());

            flowTask3.setPrevTask(flowTask2.getKey());
            flowTask3.setNextTask(flowTask4.getKey());

            flowTask4.setPrevTask(flowTask3.getKey());

            //Set Chapter to use defaultFlow
            // (Do this internally if only one flow?)
            Updateable<Chapter> chapterUpdateable1 = new ChapterUpdater();
            Readable<Chapter> chapterReadable1 = new GetChapterByIDCommand(chapterOne.getChapterId());
            chapterUpdateable1.updateEntity( chapterReadable1 , new UpdateChapterFlowTask( defaultFlow.getFlowId() ) );


            //Set page to a Flow
            Updateable<Page> pageUpdateable1 = new PageUpdater();
            Readable<Page> pageReadable1 = new GetPageByIDCommand(chapterOne.getPages().get(0).getId());
            pageUpdateable1.updateEntity( pageReadable1, new UpdatePageSetFlowTask( flowTask0.getFlowTaskId() ));


            // 1) Check to make sure chapter has the default flow
            Chapter chapter = seriesOne.getChapters().get(0);
            System.out.println( chapter.getTitle() + " has the flow: " + chapter.getFlow().getFlowName() );

            // 2) Check to make sure flow has a list of flowTasks
            // 3) Check to make sure flowTasks has  a flow type
            Flow flow = chapter.getFlow();
            System.out.println( flow.getFlowName() + " has the following flow Tasks: ");
            for( FlowTask flowTask : flow.getFlowTasks() ){
                System.out.println( flowTask.getFlowTaskName() + " of type " + flowTask.getFlowType().getFlowTypeName() );
            }

            /**
             * Test if we can change move a page between tasks
             **/

            // BEFORE PRINT
            Page pageToUpdate = seriesOne.getChapters().get(0).getPages().get(0);
            FlowTask flowTaskBefore =  pageToUpdate.getFlowTaskEntity();
            System.out.println( " Flow Task is " + flowTaskBefore.getFlowTaskName()
                                + " with the type " + flowTaskBefore.getFlowType().getFlowTypeName() );

            // Move to next Flow
            Updateable<Page> flowUpdateable1 = new PageUpdater();
            Readable<Page> pageReadable2 = new GetPageByIDCommand(pageToUpdate.getId());
            flowUpdateable1.updateEntity( pageReadable2, new UpdatePageMoveToNextFlowTask());
            System.out.println( "Move page to Next Flow");

            //AFTER PRINT
            Page pageToUpdate2 = seriesOne.getChapters().get(0).getPages().get(0);
            FlowTask flowTaskAfter =  pageToUpdate2.getFlowTaskEntity();
            System.out.println( " Flow Task is " + flowTaskAfter.getFlowTaskName()
                    + " with the type " + flowTaskAfter.getFlowType().getFlowTypeName() );

            //Move to previous flow
            Updateable<Page> flowUpdateable2 = new PageUpdater();
            Readable<Page> pageReadable3 = new GetPageByIDCommand(pageToUpdate2.getId());
            flowUpdateable2.updateEntity( pageReadable3, new UpdatePageMoveToPreviousFlowTask());
            System.out.println( "Move page to Previous Flow");

            //After 2 Print
            Page pageToUpdate3 = seriesOne.getChapters().get(0).getPages().get(0);
            FlowTask flowTaskAfter2 = pageToUpdate3.getFlowTaskEntity();
            System.out.println( " Flow Task is " + flowTaskAfter2.getFlowTaskName()
                    + " with the type " + flowTaskAfter2.getFlowType().getFlowTypeName() );


            /**
             * Testing Update Series Edit Description
             */

            Updateable<Series> seriesUpdateableDannyGangsta = new SeriesUpdater();
            Readable<Series> seriesReadableDannyGansta = new GetSeriesByIDCommand(seriesOne.getSeriesID());
            seriesUpdateableDannyGangsta.updateEntity( seriesReadableDannyGansta,
                    new UpdateSeriesEditDescriptionTask("Series One Updated Danny Gangsta"));

            System.out.println(" seriesOne has new description:  " + seriesOne.getDescription() );




            /**
             * Test Getting Pages with the FlowTask Done
             */
            // move the Page to done task
            Page pageToUpdate4 = seriesOne.getChapters().get(0).getPages().get(0);

            Updateable<Page> pageUpdateableDoneTask = new PageUpdater();
            Readable<Page> pageReadable = new GetPageByIDCommand(pageToUpdate4.getId());
            pageUpdateableDoneTask.updateEntity( pageReadable, new UpdatePageSetFlowTask( flowTask4.getFlowTaskId() ) );

            seriesOne.getChapters().get(0).getPages().get(0).setFlowTask( flowTask4.getKey());

            //Getting the pages in the done task
            Readable<Page> pageReadable4 = new GetPagesInDoneFlowTaskCommand();
            List<Page> pageList = pageReadable4.fetch().getList();

            System.out.println("printing pages in the Done Task");
            
            for( Page p : pageList){
                System.out.println( p.getTitle() );
            }


        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("test3");

    }


        /*
    @RequestMapping(value="/addScribbles", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> loginScreen(@RequestParam String title,
                                              @RequestParam String description,
                                              HttpSession session, ModelMap map) {

        try {
            Createable<Scribble> newScribble = new ScribbleCreater(title, description);
            Scribble scribble = newScribble.createEntity(new NoWork<Scribble>());


        } catch (CreateException e) {
            return new ResponseEntity<String>(HttpStatus.BAD_GATEWAY);
            e.printStackTrace();
        }

    }


    @RequestMapping(value="/addPages", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> loginScreen(@RequestParam String summary,
                                              HttpSession session, ModelMap map) {
        return new ResponseEntity<String>("");

    }

    @RequestMapping(value="/addPages", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> loginScreen(@RequestParam String summary,
                                              HttpSession session, ModelMap map) {


    }

*/



}
