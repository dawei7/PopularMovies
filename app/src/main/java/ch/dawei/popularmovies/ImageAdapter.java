package ch.dawei.popularmovies;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class ImageAdapter extends ArrayAdapter<Movie> {
    private Context context;
    private LayoutInflater inflater;

    public ImageAdapter(Context context,List<Movie> objects) {
        super(context,0, objects);

        this.context = context;
        inflater = LayoutInflater.from(context);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Movie movie = getItem(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.image_view,parent,false);
        }

        Picasso
                .with(context)
                .load(movie.getMoviePoster())
                .into((ImageView) convertView);

        return convertView;
    }
}