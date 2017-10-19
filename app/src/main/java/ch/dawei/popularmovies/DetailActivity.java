package ch.dawei.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_GoBack;
    ImageView iv_ShowMoviePoster;
    TextView tv_textOutput;
    String moviePoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btn_GoBack = (Button) findViewById(R.id.backButton);
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

        btn_GoBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            Intent i = new Intent(DetailActivity.this, MainActivity.class);
            startActivity(i);
    }
}
