package com.example.hiran.onemarket.Adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiran on 9/4/16.
 */
public class HomeAdapter extends BaseAdapter {

    private List<String> homeList = new ArrayList<>();

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

        return null;
    }
}
