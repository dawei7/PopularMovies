package ch.dawei.popularmovies;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by david on 10/17/2017.
 */

public class ImageAdapter extends ArrayAdapter<Movie> {

    public ImageAdapter(Context context,List<Movie> objects) {
        super(context,0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Gets the AndroidFlavor object from the ArrayAdapter at the appropriate position
        Movie movie = getItem(position);

        // Adapters recycle views to AdapterViews.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        // and we modify the View widgets as usual.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_main,parent);
        }
        ImageView imageView = convertView.findViewById(R.id.imageView);
        Picasso.with(this.getContext()).load(movie.getMoviePoster()).into(imageView);

        return convertView;
    }
}