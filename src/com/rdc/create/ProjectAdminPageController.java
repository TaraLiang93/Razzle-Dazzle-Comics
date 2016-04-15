package com.rdc.create;

import com.data.Globals;
import com.data.UserData;
import com.data.api.createables.SeriesCreater;
import com.data.api.createables.fillCommands.SeriesFillCommand;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Createable;
import com.data.api.interfaces.Updateable;
import com.data.api.queries.external.GetSeriesOfUserDataCommand;
import com.data.api.queries.external.GetUserDataByIDCommand;
import com.data.api.updatables.UserDataUpdater;
import com.data.api.updatables.updateTasks.UpdateUserDataAddDoodlesTask;
import com.data.api.updatables.updateTasks.UpdateUserDataAddSeriesTask;
import com.data.creation.Doodle;
import com.data.structure.Series;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.sun.org.apache.bcel.internal.generic.L2D;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.data.api.interfaces.Readable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class ProjectAdminPageController {

    @RequestMapping(value="/create/projectAdminPage", method= RequestMethod.GET)
    public ModelAndView loadPublishPage(HttpSession session, ModelMap map){

        Globals globals = (Globals) session.getAttribute("globals");
        if(globals == null)
        {
            globals = new Globals();
            session.setAttribute("globals",globals);
        }
        globals.setStatus("create");




        try {

            Readable<Series> getUserSeries = new GetSeriesOfUserDataCommand(UserServiceFactory.getUserService().getCurrentUser());
            Series seriesList = getUserSeries.fetch().getResult();
            map.put("series",seriesList);

        } catch (FetchException e) {
            e.printStackTrace();
        }

//        for(int i = 0; i <= 1; ++i){
//            Series newSeries = new Series();
//            newSeries.setSeriesID(new Long(i));
//            newSeries.setTitle("Series " + i);
//            newSeries.setDescription("This is a series about love " + i);
//            series.add(newSeries);
//        }




        return new ModelAndView("projectAdminPage");
    }
//    @RequestMapping(value="/createData", method= RequestMethod.GET)
//    public String createData(HttpSession session, ModelMap map){
//
//        User user = UserServiceFactory.getUserService().getCurrentUser();
//
//        for(int i = 0;i < 1000;++i) {
//
//            Createable<Series> seriesCreater = new SeriesCreater(null,"Series "+ i,"This is a description",true);
//            try {
//                Series  series = seriesCreater.createEntity(new SeriesFillCommand());
//                Readable<UserData> userDataReadable = new GetUserDataByIDCommand(UserServiceFactory.getUserService().getCurrentUser().getUserId());
//                Updateable<UserData> userDataUpdateable = new UserDataUpdater();
//                userDataUpdateable.updateEntity(userDataReadable,new UpdateUserDataAddSeriesTask(series.getSeriesID()));
//
//
//            } catch (CreateException e) {
//                e.printStackTrace();
//            } catch (FetchException e) {
//                e.printStackTrace();
//            } catch (UpdateException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return "redirect:/create/projectAdminPage";
//    }
}
