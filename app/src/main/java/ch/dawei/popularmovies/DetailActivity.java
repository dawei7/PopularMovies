package ch.dawei.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class DetailActivity extends AppCompatActivity{


    @InjectView(R.id.moviePoster)
    ImageView ivShowMoviePoster;
    @InjectView(R.id.textOutput)
    TextView tvTextOutput;

    String moviePoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.inject(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();

        moviePoster = extras.getString("MOVIE_POSTER");
        Picasso.with(this).load(moviePoster).into(ivShowMoviePoster);

        tvTextOutput.setText(Html.fromHtml("" +
                        "<u><b>Title: </u></b>" + extras.getString("TITLE") +
                        "<br><u><b>Release Date: </u></b>" + extras.getString("RELEASE") +
                        "<br><u><b>Average Vote: </u></b>" + extras.getString("VOTE_AVERAGE") +
                        "<br><u><b>Overview: </u></b><br>" + extras.getString("OVERVIEW")
                , Html.FROM_HTML_MODE_COMPACT));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(i);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
