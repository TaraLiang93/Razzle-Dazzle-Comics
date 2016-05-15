package com.data.api.queries.Comparators;

import com.data.structure.Series;

import java.util.Comparator;

/**
 * Created by Zhenya on 5/14/16.
 */
public class SeriesViewsComparator implements Comparator<Series> {

    @Override
    public int compare(Series o1, Series o2) {
        int compareVal;
        if( o1.getViews() == o2.getViews() ){
            compareVal = 0;
        }
        else if( o1.getViews() < o2.getViews() ){
            compareVal = -1;
        }
        else if (o1.getViews() > o2.getViews() ){
            compareVal = 1;
        }
        return 0;
    }

}
