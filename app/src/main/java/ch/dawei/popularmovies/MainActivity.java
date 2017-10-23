package ch.dawei.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import ch.dawei.popularmovies.utilities.NetworkUtils;

public class MainActivity extends AppCompatActivity {


    GridView gridView;
    TextView mErrorMessageDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridView);
        mErrorMessageDisplay=(TextView) findViewById(R.id.mErrorMessageDisplay);
        makeTheMovieDBSearchQuery("popular"); //Preselected Choice
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.optionPopularity:
                makeTheMovieDBSearchQuery("popular");
                break;

            case R.id.optionTopRated:
                makeTheMovieDBSearchQuery("top_rated");
                break;
        }
        return true;
    }


    private void makeTheMovieDBSearchQuery(String sortby) {
        URL theMovieDBSearchUrl = NetworkUtils.buildUrl(sortby);
        new MainActivity.TheMovieDBQueryTask().execute(theMovieDBSearchUrl);
    }

    private class TheMovieDBQueryTask extends AsyncTask<URL, Void, String> {

        private void showErrorMessage () {
            gridView.setVisibility(View.INVISIBLE);
            gridView.getLayoutParams().height=0;
            mErrorMessageDisplay.setVisibility(View.VISIBLE);
        }

        private void hideErrorMessage () {
            mErrorMessageDisplay.setVisibility(View.INVISIBLE);
            gridView.setVisibility(View.VISIBLE);
        }

        // COMPLETED (26) Override onPreExecute to set the loading indicator to visible
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            hideErrorMessage();
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
            super.onPostExecute(movieDBSearchResults);

            final ArrayList<Movie> movies = new ArrayList<>();
            String moviePoster;
            String title;
            String releasedDate;
            double voteAverage;
            String plotSynopsis;

            if (movieDBSearchResults != null && !movieDBSearchResults.equals("")) {
                try {
                    JSONObject movieDB = new JSONObject(movieDBSearchResults);
                    JSONArray results = movieDB.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        moviePoster = "http://image.tmdb.org/t/p/w342//" + results.getJSONObject(i).getString("poster_path");
                        title = results.getJSONObject(i).getString("title");
                        releasedDate = results.getJSONObject(i).getString("release_date");
                        voteAverage = results.getJSONObject(i).getDouble("vote_average");
                        plotSynopsis = results.getJSONObject(i).getString("overview");
                        movies.add(new Movie(moviePoster, title, releasedDate, voteAverage, plotSynopsis));
                        hideErrorMessage();
                    }
                } catch(JSONException e){
                    e.printStackTrace();
                }
            } else {
                showErrorMessage();
            }

            //mLoadingIndicator.setVisibility(View.INVISIBLE);
            gridView.setAdapter(new ImageAdapter(getBaseContext(), movies));

            gridView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    Intent i = new Intent(MainActivity.this, DetailActivity.class);
                    i.putExtra("MOVIE_POSTER", movies.get(position).getMoviePoster());
                    i.putExtra("TITLE", movies.get(position).getTitle());
                    i.putExtra("RELEASE", movies.get(position).getReleasedDate());
                    i.putExtra("VOTE_AVERAGE", Double.toString(movies.get(position).getVoteAverage()));
                    i.putExtra("OVERVIEW", movies.get(position).getPlotSynopsis());
                    startActivity(i);
                }
            });
        }
    }
}
