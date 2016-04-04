package com.data.api.DoodleQueries;

/**
 * Created by Zhenya on 4/1/16.
 */

import com.data.UserData;
import com.data.api.QueryContainer;
import com.data.api.Readable;
import com.data.creation.Doodle;
import com.data.structure.Tag;
import com.google.appengine.api.datastore.Query.*;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 *  This is the object for the DoodleQuery command getDoodles
 */

public class GetDoodlesByTagCommand extends Readable {

    Filter filter;
    Key<UserData> userDataKey;
    String tagName;

    /**
     *
     * @param tagName name of the tag
     * @param userData the User entity passed in
     */
    public GetDoodlesByTagCommand(String tagName, UserData userData){
        this.tagName = tagName;
        this.userDataKey = userData.getKey();
    }

    //TODO:
    //getDoodles with a specific tag
    //what is passed in?? The tag name or the tag id?
    //get a list of doodles from

    @Override
    protected Filter getFilter() {
        //create filter
        filter = new FilterPredicate("name",
                FilterOperator.EQUAL,
                tagName);
        return filter;
    }

    /**
     * getDoodlesCommand will override the fetch method since it is an ancestor query which involves an extra step
     * @return QueryContainer which holds the query
     */
    @Override
    public QueryContainer<GetDoodlesByTagCommand> fetch() {
        //query that finds the Tag entity with the given name belonging a specific user
        Query<Tag> tagQuery = ofy().load().type(Tag.class).ancestor(userDataKey).filter(getFilter());
        Tag tag = tagQuery.first().now();

        Query doodleQuery = ofy().load().type(Doodle.class).filter("tagList", tag.getKey() );
        QueryContainer<GetDoodlesByTagCommand> queryContainer =  new QueryContainer(doodleQuery);
        return queryContainer;
    }

    @Override
    protected Class<Doodle> getType() {
        return Doodle.class;
    }


}

