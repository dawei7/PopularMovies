package ch.dawei.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;

import ch.dawei.popularmovies.utilities.NetworkUtils;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    //API KEY https://api.themoviedb.org/3/movie/550?api_key=3c2f9be53000d40d4f2a54992e159d54
    //https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg

    //Fields
    GridView gridView;
    ArrayList<Movie> mMovies;
    AsyncResponse listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listener=new AsyncResponse() {
            @Override
            public void processFinish(ArrayList<Movie> output) {

            }
        };
        gridView = (GridView) findViewById(R.id.gridView);
        makeTheMovieDBSearchQuery("popular");

    }

    private void makeTheMovieDBSearchQuery(String sortby) {
        URL theMovieDBSearchUrl = NetworkUtils.buildUrl(sortby);
        new TheMovieDBQueryTask(listener).execute(theMovieDBSearchUrl);
    }

    @Override
    public void processFinish(ArrayList<Movie> output) {

        gridView.setAdapter(new ImageAdapter(this, output));

        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
