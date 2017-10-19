package ch.dawei.popularmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import ch.dawei.popularmovies.utilities.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    //API KEY https://api.themoviedb.org/3/movie/550?api_key=3c2f9be53000d40d4f2a54992e159d54
    //https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg

    //Fields
    GridView gridView;
    ArrayList<Movie> mMovies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridView);
        makeTheMovieDBSearchQuery("popular");

    }

    private void makeTheMovieDBSearchQuery(String sortby) {
        URL theMovieDBSearchUrl = NetworkUtils.buildUrl(sortby);
        new MainActivity.TheMovieDBQueryTask().execute(theMovieDBSearchUrl);
    }

    public class TheMovieDBQueryTask extends AsyncTask<URL, Void, String> {

        private ProgressBar mLoadingIndicator;


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
                    moviePoster = "http://image.tmdb.org/t/p/w342//"+results.getJSONObject(i).getString("poster_path");
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
            gridView.setAdapter(new ImageAdapter(getBaseContext(), movies));

            /*gridView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    Toast.makeText(MainActivity.this, "" + position,
                            Toast.LENGTH_SHORT).show();
                }
            });*/
        }
    }
}
