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
import com.data.api.updatables.DoodleUpdater;
import com.data.api.updatables.ScribbleUpdater;
import com.data.api.updatables.UserDataUpdater;
import com.data.api.updatables.updateTasks.UpdateDoodleTask;
import com.data.api.updatables.updateTasks.UpdateUserDataTask;
import com.data.creation.Doodle;
import com.data.creation.Page;
import com.data.creation.Scribble;
import com.data.structure.Tag;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.model.PageModel;
import com.model.SceneModel;
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
            Scribble scribble = scribbleCreater.createEntity(new ScribbleFillCommand(pageList));
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
        page.setTitle("I am Page YAY!");
        ofy().save().entity(page).now();
        List<Key<Page>> pageList = new ArrayList<>();
        pageList.add(page.getKey());

        //createScribble
        ScribbleCreater scribbleCreater = new ScribbleCreater("I am ScribbleCreater created");
        Scribble createdScribble =  scribbleCreater.createEntity(new ScribbleFillCommand(pageList));

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
        Readable<Scribble> getScribbleReadable = new GetScribblesByIDCommand(createdScribble.getScribbleId());
        System.out.println( "Readable to fetch Scribble has title " + getScribbleReadable.fetch().getResult().getTitle());

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
        Readable<Scribble> getScribble = new GetScribblesByIDCommand(createdScribble.getScribbleId());
        Scribble theScribble = getScribble.fetch().getResult();
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
        Readable<Scribble> getScribbleReadable2 = new GetScribblesByIDCommand(createdScribble.getScribbleId());
        System.out.println( "Updated Scribble has title " + getScribbleReadable.fetch().getResult().getTitle());

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

        Scribble scribble = scribbleCreateable.createEntity(new ScribbleFillCommand(pageList));


        Updateable<UserData> userDataUpdater3 = new UserDataUpdater();
        Readable<UserData> getUserData3 = new GetEntityFromKeyCommand<>( userData.getKey());
        try {
            userDataUpdater3.updateEntity(getUserData3, new UpdateUserDataTask(scribble));
        }
        catch( FetchException| UpdateException ex){
            ex.printStackTrace();

        }

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
            page1Scene1.setTinyMCEText("page1Scene1 TinyMCText");

            SceneModel page1Scene2 =  new SceneModel();
            page1Scene2.setSetting("page1Scene2 setting");
            page1Scene2.setTinyMCEText("page1Scene2 TinyMCText");

            List<SceneModel> sceneModelListForPage1 = new ArrayList<>();
            sceneModelListForPage1.add(page1Scene1);
            sceneModelListForPage1.add(page1Scene2);

            PageModel page1Model = new PageModel();
            page1Model.setTitle("page1Title");
            page1Model.setSummary("page1Summary");
            page1Model.setScenes(sceneModelListForPage1);

            //Make a Page
            Createable<Page> pageCreater1 = new PageCreater(page1Model);
            Page pageCreated1 = pageCreater1.createEntity(new PageFillCommand());

            /**
             * Page 2
             */

            SceneModel page2Scene1 =  new SceneModel();
            page2Scene1.setSetting("page2Scene1 setting");
            page2Scene1.setTinyMCEText("page2Scene1 TinyMCText");

            SceneModel page2Scene2 =  new SceneModel();
            page2Scene2.setSetting("page2Scene2 setting");
            page2Scene2.setTinyMCEText("page2Scene2 TinyMCText");

            List<SceneModel> sceneModelListForPage2 = new ArrayList<>();
            sceneModelListForPage2.add(page2Scene1);
            sceneModelListForPage2.add(page2Scene2);

            PageModel page2Model = new PageModel();
            page2Model.setTitle("page2Title");
            page2Model.setSummary("page2Summary");
            page2Model.setScenes(sceneModelListForPage2);

            //Make a Page
            Createable<Page> pageCreater2 = new PageCreater(page1Model);
            Page pageCreated2 = pageCreater2.createEntity(new PageFillCommand());

            /**
             * pageList of the 2 pages
             */
            List<Key<Page>> pageList = new ArrayList<>();
            pageList.add(pageCreated1.getKey());
            pageList.add(pageCreated2.getKey());



            Createable<Scribble> scribbleCreater = new ScribbleCreater("Scribble Title");
            scribbleCreater.createEntity(new ScribbleFillCommand(pageList));

        }
        catch (CreateException ex){
            ex.printStackTrace();
        }
        return new ModelAndView("test3");
    }

}
