package ch.dawei.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by david on 10/19/2017.
 */

public class DetailActivity extends AppCompatActivity {

    ImageView iv_ShowMoviePoster;
    TextView tv_textOutput;
    String moviePoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        iv_ShowMoviePoster = (ImageView) findViewById(R.id.moviePoster);

        tv_textOutput = (TextView) findViewById(R.id.textOutput);

        Bundle extras = getIntent().getExtras();

        moviePoster = extras.getString("MOVIE_POSTER");
        Picasso.with(this).load(moviePoster).into(iv_ShowMoviePoster);

        tv_textOutput.setText(Html.fromHtml("" +
                        "<u><b>Title: </u></b>" + extras.getString("TITLE") +
                        "<br><u><b>Release Date: </u></b>" + extras.getString("RELEASE") +
                        "<br><u><b>Average Vote: </u></b>" + extras.getString("VOTE_AVERAGE") +
                        "<br><u><b>Overview: </u></b><br>" + extras.getString("OVERVIEW")
                , Html.FROM_HTML_MODE_COMPACT));
    }
}
