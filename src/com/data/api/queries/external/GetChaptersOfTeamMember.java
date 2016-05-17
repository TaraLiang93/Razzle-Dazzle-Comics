package com.data.api.queries.external;

import com.data.api.containers.MapContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.queries.internal.GetEntityListFromKeyListCommand;
import com.data.creation.Chapter;
import com.data.structure.Series;
import com.data.structure.TeamMember;
import com.google.appengine.api.datastore.Query.Filter;
import com.googlecode.objectify.Key;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 5/17/16.
 */
public class GetChaptersOfTeamMember extends Readable {


    Long seriesId;
    String userId;

    public GetChaptersOfTeamMember(Long seriesId, String userId) {
        this.seriesId = seriesId;
        this.userId = userId;
    }

    public GetChaptersOfTeamMember(String strSeriesId,String userId) {
        try {
            this.seriesId = Long.parseLong(strSeriesId);
            this.userId = userId;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected Filter getFilter() throws FetchException {
        return null;
    }

    @Override
    protected Class getType() {
        return Chapter.class;
    }

    @Override
    public Container fetch() throws FetchException {
        // get series from id
        Readable<Series> getSeries = new GetSeriesByIDCommand(seriesId);
        Series series = getSeries.fetch().getResult();


        List<Chapter> chapters = series.getChapters();

        List<Chapter> currentChapters = new ArrayList<>();

        for (Chapter chapter : chapters) {
            for (TeamMember member : chapter.getTeamMembers()) {
                if (member.getUserData().getUserId().equals(userId)) {
                        currentChapters.add(chapter);
                }
            }
        }

        Map<Key<Chapter>, Chapter> map = new HashMap<>();
        for (Chapter chapter : currentChapters) map.put(chapter.getKey(), chapter);


        MapContainer<Chapter> chapterMapContainer = new MapContainer<>(map);
        // get chapters of series
        return chapterMapContainer;

    }
}
