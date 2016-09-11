package com.example.hiran.onemarket.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.hiran.onemarket.Adapters.HomeAdapter;
import com.example.hiran.onemarket.R;
import com.example.hiran.onemarket.Util.DBHelper;

/**
 * Created by hiran on 9/4/16.
 */
public class HomeFragment extends Fragment {

    private HomeAdapter homeAdapter = null;
    private GridView homeGridView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout, container, false);
        homeAdapter = new HomeAdapter(view.getContext(), DBHelper.getInstance(getActivity()).getPrices(), DBHelper.getInstance(getActivity()).getItems());
        homeGridView = (GridView) view.findViewById(R.id.home_grid_view);
        homeGridView.setAdapter(homeAdapter);
        return view;
    }

}
