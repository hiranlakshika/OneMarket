package com.example.hiran.onemarket.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.hiran.onemarket.Adapters.HomeAdapter;
import com.example.hiran.onemarket.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiran on 9/4/16.
 */
public class HomeFragment extends Fragment {

    private HomeAdapter homeAdapter = null;
    private GridView homeGridView;


    private List<String> sendPrice(){
        List<String> titles = new ArrayList<>();
        titles.add("100");
        titles.add("15");
        titles.add("200");
        titles.add("780");
        return titles;
    }

    private List<String> sendTitle(){
        List<String> titles = new ArrayList<>();
        titles.add("Bag");
        titles.add("Watch");
        titles.add("Phone");
        titles.add("Pen");
        return titles;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout, container, false);
        homeAdapter = new HomeAdapter(view.getContext(),sendPrice(),sendTitle());
        homeGridView = (GridView) view.findViewById(R.id.home_grid_view);
        homeGridView.setAdapter(homeAdapter);
        return view;
    }

}
