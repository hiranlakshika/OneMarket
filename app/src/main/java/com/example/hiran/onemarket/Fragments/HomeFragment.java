package com.example.hiran.onemarket.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.hiran.onemarket.Adapters.HomeAdapter;
import com.example.hiran.onemarket.R;

/**
 * Created by hiran on 9/4/16.
 */
public class HomeFragment extends Fragment {

    private HomeAdapter homeAdapter = null;
    private GridView homeGridView;
    private static final String[] price = {"100", "200", "250", "508", "436", "268"};
    private static final String[] title = {"Watch", "HandBag", "Mouse", "Charger", "Mouse", "Keyboard"};
    private static final int[] image = {R.drawable.bagsample, R.drawable.bagsample, R.drawable.bagsample, R.drawable.bagsample, R.drawable.bagsample, R.drawable.bagsample,};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout, container, false);
        homeAdapter = new HomeAdapter(getActivity(), image,price,title);
        homeGridView = (GridView) view.findViewById(R.id.home_grid_view);
        homeGridView.setAdapter(homeAdapter);
        return view;
    }
}
