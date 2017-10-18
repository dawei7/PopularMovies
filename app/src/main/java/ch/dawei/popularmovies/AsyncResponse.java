package ch.dawei.popularmovies;

import java.util.ArrayList;

/**
 * Created by david on 10/18/2017.
 */

public interface AsyncResponse {
    void processFinish(ArrayList<Movie> output);
}
