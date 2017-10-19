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
    private Context context;
    private LayoutInflater inflater;

    public ImageAdapter(Context context,List<Movie> objects) {
        super(context,0, objects);

        this.context = context;

        inflater = LayoutInflater.from(context);

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
            convertView = inflater.inflate(R.layout.image_view,parent,false);
        }
        //Picasso.with(this.getContext()).load(movie.getMoviePoster()).into(imageView);
        Picasso
                .with(context)
                .load(movie.getMoviePoster())
                .into((ImageView) convertView);

        return convertView;
    }
}