package com.rdc;

import com.data.UserData;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.queries.external.GetUserDataByUserCommand;
import com.data.api.updatables.UserDataUpdater;
import com.data.api.updatables.updateTasks.UpdateUserDataUpdateInfoTask;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class ProfileController {


    public static final String LOAD_PROFILE = "/profile/load";
    public static final String CHANGE_INFO = "/profile/updateInfo";

    @RequestMapping(value=LOAD_PROFILE, method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView redirectProfilePage( ModelMap map){

        User user = UserServiceFactory.getUserService().getCurrentUser();
        BlobstoreService blobs = BlobstoreServiceFactory.getBlobstoreService();

        Container<UserData> data = null;
        try {
            data = new GetUserDataByUserCommand(user).fetch();
            UserData userData = data.getResult();
            map.put("user", userData);
        } catch (FetchException e) {
            e.printStackTrace();
            return new ModelAndView("redirect:/");
        }

        map.put("uploadAction", blobs.createUploadUrl(CHANGE_INFO));

        return new ModelAndView("profilePage");
    }

    @RequestMapping(value=CHANGE_INFO, method= RequestMethod.POST)
    public ModelAndView updateInfo(@RequestParam String nickName,
                                   @RequestParam String location,
                                   @RequestParam String description,
                                   @RequestHeader String referer,
                                   HttpServletRequest req, ModelMap map){

        User user = UserServiceFactory.getUserService().getCurrentUser();

        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
        List<BlobKey> blobKeys = blobs.get("profileImage");

        BlobKey newProfile = null;

        if(blobKeys != null){//We're chaning the image
            newProfile = blobKeys.get(0);
        }

        try {
            new UserDataUpdater().updateEntity(
                    new GetUserDataByUserCommand(user),
                    new UpdateUserDataUpdateInfoTask(nickName, description, location, newProfile));
        } catch (FetchException | UpdateException | CreateException e) {
            e.printStackTrace();
            return new ModelAndView("redirect:" + referer);
        }

        return new ModelAndView("forward:" + LOAD_PROFILE);
    }

}
