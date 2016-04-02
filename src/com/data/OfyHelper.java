package com.data;

/**
 * Created by tara on 2/14/16.
 */
//
//import com.googlecode.objectify.Objectify;
//import com.googlecode.objectify.ObjectifyFactory;

import com.data.creation.Doodle;
import com.data.creation.Scribble;
import com.data.structure.Tag;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * OfyHelper, a ServletContextListener, is setup in web.xml to run before a JSP is run.  This is
 * required to let JSP's access Ofy.
 **/
public class OfyHelper implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        // This will be invoked as part of a warmup request, or the first User request if no warmup
        // request.

        ObjectifyService.register(UserData.class);
        ObjectifyService.register(Scribble.class);
        ObjectifyService.register(Doodle.class);
        ObjectifyService.register(Tag.class);

    }

    public void contextDestroyed(ServletContextEvent event) {
        // App Engine does not currently invoke this method.
    }
}
