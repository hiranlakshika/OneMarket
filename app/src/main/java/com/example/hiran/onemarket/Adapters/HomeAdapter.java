package com.example.hiran.onemarket.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hiran.onemarket.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiran on 9/4/16.
 */
public class HomeAdapter extends BaseAdapter {

    private String TAG = HomeAdapter.class.getSimpleName();
    private LayoutInflater layoutInflater;
    private Context context;
    private List<String> price;
    private List<String> titles;

    public HomeAdapter(Context context, List<String> price, List<String> titles) {
        this.context = context;
        this.price = price;
        this.titles = titles;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return price.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.home_custom_list, null);

        } else {
            view = convertView;
        }
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;

        Bitmap note5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.note5,options);
        Bitmap j7 = BitmapFactory.decodeResource(context.getResources(), R.drawable.j7,options);

        ImageView imageView = (ImageView) view.findViewById(R.id.home_grid_image);
        TextView imageTitle = (TextView) view.findViewById(R.id.grid_image_title);
        TextView pricetxt = (TextView) view.findViewById(R.id.grid_img_price);
        try {
            if (titles.get(position).equals("Xperia X")) {
                imageView.setImageResource(R.drawable.xperia_x);
            }
            if (titles.get(position).equals("Galxy J7")) {
                imageView.setImageBitmap(j7);
            }
            if (titles.get(position).equals("Galaxy J5")) {
                imageView.setImageResource(R.drawable.j5);
            }
            if (titles.get(position).equals("Galaxy J2")) {
                imageView.setImageResource(R.drawable.j2);
            }
            if (titles.get(position).equals("One Plus 3")) {
                imageView.setImageResource(R.drawable.oneplus_3);
            }
            if (titles.get(position).equals("Galaxy Note 5")) {
//                imageView.setImageResource(R.drawable.note5);
                imageView.setImageBitmap(note5);
            }
            if (titles.get(position).equals("Xperia Z3")) {
                imageView.setImageResource(R.drawable.xperia_z3);
            }
            if (titles.get(position).equals("Xperia Z5")) {
                imageView.setImageResource(R.drawable.xperia_z5);
            }
            if (titles.get(position).equals("Galaxy S6")) {
                imageView.setImageResource(R.drawable.s6);
            }
            if (titles.get(position).equals("Galaxy S7")) {
                imageView.setImageResource(R.drawable.s7);
            }
        } catch (java.lang.OutOfMemoryError e) {
            Log.e(TAG,e.getMessage());
        }
        imageTitle.setText(titles.get(position));
        pricetxt.setText(price.get(position));

        return view;
    }
}
