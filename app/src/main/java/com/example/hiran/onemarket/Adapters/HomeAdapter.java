package com.example.hiran.onemarket.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.hiran.onemarket.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiran on 9/4/16.
 */
public class HomeAdapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    Context context;

    private List<String> homeList = new ArrayList<>();

    public HomeAdapter(Context context, List<String> homeList) {
        this.context = context;
        this.homeList = homeList;
    }

    @Override
    public int getCount() {
        return homeList.size();
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
        convertView = layoutInflater.inflate(R.layout.home_custom_list, null);
        return convertView;
    }
}
