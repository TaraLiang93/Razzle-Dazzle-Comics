package com.rdc;

import com.data.UserData;
import com.data.creation.Doodle;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
/*
        //tag called "Cat"
        Tag catTag = new Tag();
        //set james as the parent of catTag
        catTag.setUserData(james.getKey());

        // set james to be the parentKey of the catTag
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




        String tagNameParam = "Cat";
        Query<Tag> tagAncestorQuery = ofy().load().type(Tag.class).ancestor( james.getKey() ).filter("name", tagNameParam);

        Tag tag = tagAncestorQuery.first().now();

        // get a list of doodles that match a tag
        // if a Doodle's taglist property has tag return that doodle
        List<Doodle> doodleList = ofy().load().type(Doodle.class).filter("tagList", tag.getKey() ).list();

        for( Doodle d: doodleList){
            System.out.println( d.getTitle() );
        }
        */

        return new ModelAndView("test");

    }

}
