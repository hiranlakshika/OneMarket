package com.example.hiran.onemarket.Adapters;

import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
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
            ImageView imageView = (ImageView) view.findViewById(R.id.home_grid_image);
            TextView imageTitle = (TextView) view.findViewById(R.id.grid_image_title);
            TextView pricetxt = (TextView) view.findViewById(R.id.grid_img_price);
            //imageView.setImageResource(image[position]);
            imageTitle.setText(titles.get(position));
            pricetxt.setText(price.get(position));
        } else {
            view = convertView;
        }
        return view;
    }
}
