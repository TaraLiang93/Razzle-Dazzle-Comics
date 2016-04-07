package com.rdc;

import com.data.UserData;
import com.data.api.DoodleQueries.GetDoodlesByIDCommand;
import com.data.api.DoodleQueries.GetDoodlesByTagCommand;
import com.data.api.Readable;
import com.data.api.createables.ScribbleCreater;
import com.data.api.createables.TagCreater;
import com.data.api.dataItems.ScribbleCommandFill;
import com.data.api.dataItems.TagCommandFill;
import com.data.creation.Doodle;
import com.data.creation.Page;
import com.data.creation.Scribble;
import com.data.structure.Tag;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

        james.addDoodleToList( doodleCat.getKey() );
        james.addTagToList( catTag.getKey() );
        //save the entity james with updated list values
        ofy().save().entity(james).now();




        String tagName = "Cat";
        //ancestor query to find the Key<Tag> with the name "Cat" owned by Userdata james
        Query<Tag> tagAncestorQuery = ofy().load().type(Tag.class).ancestor( james.getKey() ).filter("name", tagName);

        Tag tag = tagAncestorQuery.first().now();

        // get a list of doodles that match a tag
        // if a Doodle's taglist property has tag return that doodle
        List<Doodle> doodleList = ofy().load().type(Doodle.class).filter("tagList", tag.getKey() ).list();

        for( Doodle d: doodleList){
            System.out.println( d.getTitle() );
        }

        Readable<Doodle> getDoodleWithTagTest = new GetDoodlesByTagCommand( tagName, james);
        Doodle dFetched = getDoodleWithTagTest.fetch().getResult();
        System.out.println( "I did it " + dFetched.getTitle());

        System.out.println(doodleCat.getDoodleId());
//        Readable<Doodle> getDoodleWithID = new GetDoodlesByIDCommand(doodleCat.getDoodleId());
//        Doodle doodleWithID = getDoodleWithID.fetchById(doodleCat.getDoodleId());
//        System.out.println( "I did it " + doodleWithID.getTitle());
        Readable<Doodle> getDoodleWithID = new GetDoodlesByIDCommand(doodleCat.getDoodleId());
        Doodle doodleWithID = getDoodleWithID.fetch().getResult();
        System.out.println( " Fetch Doodle With ID Test has ID: " + doodleWithID + " and has title " + doodleWithID.getTitle());






        return new ModelAndView("test");

    }

    @RequestMapping(value="/test2", method= RequestMethod.GET)
    public ModelAndView test2(HttpServletRequest req, ModelMap map){

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

        userData.addTagToList( tag1.getKey());
        userData.addTagToList( tag2.getKey());
        userData.addTagToList( tag3.getKey());

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

        userData.addDoodleToList( doodle1.getKey() );
        userData.addDoodleToList( doodle2.getKey() );
        userData.addDoodleToList( doodle3.getKey() );
        ofy().save().entity(userData);

        map.put("user", userData);

        System.out.println( " The name of the owner of " + tag1.getName() + " is " + tag1.getUserData().getNickName() );

        TagCreater tagCreater = new TagCreater("I am tagicus", userData.getKey());
//        Tag t = tagCreater.createEntity(new NoWork<Tag>());

        Tag t = tagCreater.createEntity(new TagCommandFill());

        ScribbleCreater scribbleCreater = new ScribbleCreater();
        List<Key<Page>> pageList = new ArrayList<>();
        Scribble scribble =  scribbleCreater.createEntity( new ScribbleCommandFill(pageList) );


        return new ModelAndView("test2");
    }


//    @RequestMapping(value="/test3", method= RequestMethod.GET)
//    public ModelAndView test3(HttpServletRequest req, ModelMap map){
//        /**
//         * testing using the creater objects
//         */
//
//        UserDataCreater userDataCreater = new UserDataCreater(); // fill userDataCreater with initial data
//        System.out.println( userDataCreater.createEntity(new UserDataCommandFill()) );
//
////        map.put();
//        return new ModelAndView("test3");
//    }

}
