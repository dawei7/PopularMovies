package ch.dawei.popularmovies;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by david on 10/17/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setAdjustViewBounds(true);

        } else {
            imageView = (ImageView) convertView;
        }

        Picasso.with(mContext).load("http://image.tmdb.org/t/p/w342//"+mThumbIds[position]+".jpg").into(imageView);
        return imageView;
    }

    // references to our images
    private String[] mThumbIds = {
            "nBNZadXqJSdt05SHLqgT0HuC5Gm",
            "uC6TTUhPpQCmgldGyYveKRAu8JN",
            "q0R4crx2SehcEEQEkYObktdeFy",
            "kY2c7wKgOfQjvbqe7yVzLTYkxJO",
            "pKESfn2Pdy0b7drvZHQb7UzgqoY",
            "9gLu47Zw5ertuFTZaxXOvNfy78T",
            "imekS7f1OuHyUP2LAiTEM0zBzUz",
            "kmcqlZGaSh20zpTbuoF0Cdn07dT",
            "dM2w364MScsjFf8pfMbaWUcWrR"
    };
}