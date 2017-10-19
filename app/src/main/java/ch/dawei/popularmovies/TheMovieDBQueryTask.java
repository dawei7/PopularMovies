package ch.dawei.popularmovies;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import ch.dawei.popularmovies.utilities.NetworkUtils;

/**
 * Created by david on 10/18/2017.
 */

public class TheMovieDBQueryTask extends AsyncTask<URL, Void, String> {


    private AsyncResponse listener;
    private ProgressBar mLoadingIndicator;

    public TheMovieDBQueryTask(AsyncResponse listener) {
        this.listener = listener;
    }

    // COMPLETED (26) Override onPreExecute to set the loading indicator to visible
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(URL... params) {
        URL searchUrl = params[0];
        String movieDBSearchResults = null;

        try {
            movieDBSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movieDBSearchResults;
    }

    @Override
    protected void onPostExecute(String movieDBSearchResults) {

        ArrayList<Movie> movies = new ArrayList<>();
        String moviePoster;
        String title;
        String releasedDate;
        double voteAverage;
        String plotSynopsis;

        try {
            JSONObject movieDB = new JSONObject(movieDBSearchResults);
            JSONArray results = movieDB.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                moviePoster = "http:/image.tmdb.org/t/p/w342/" + results.getJSONObject(i).getString("poster_path") + ".jpg";
                title = results.getJSONObject(i).getString("title");
                releasedDate = results.getJSONObject(i).getString("release_date");
                voteAverage = results.getJSONObject(i).getDouble("vote_average");
                plotSynopsis = results.getJSONObject(i).getString("overview");
                movies.add(new Movie(moviePoster, title, releasedDate, voteAverage, plotSynopsis));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //mLoadingIndicator.setVisibility(View.INVISIBLE);
        listener.processFinish(movies);
    }
}