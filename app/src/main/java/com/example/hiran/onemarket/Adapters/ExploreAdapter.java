package com.example.hiran.onemarket.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hiran.onemarket.R;

import java.util.List;

/**
 * Created by hiran on 9/4/16.
 */
public class ExploreAdapter extends BaseAdapter {

    private List<String> exploreList;
    private LayoutInflater layoutInflater;
    private Context context;

    public ExploreAdapter(Context context, List<String> exploreList) {
        this.context = context;
        this.exploreList = exploreList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return exploreList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.explore_custom_list, null);
            ImageView listImg = (ImageView) view.findViewById(R.id.explore_list_img);
            TextView title = (TextView) view.findViewById(R.id.explore_list_title);
            TextView price = (TextView) view.findViewById(R.id.explore_list_price);

            price.setText(exploreList.get(position));
        } else {
            view = convertView;
        }
        return view;
    }
}
