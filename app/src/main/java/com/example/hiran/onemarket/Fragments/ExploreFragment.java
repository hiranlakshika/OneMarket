package com.example.hiran.onemarket.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hiran.onemarket.Adapters.ExploreAdapter;
import com.example.hiran.onemarket.R;
import com.example.hiran.onemarket.Util.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiran on 9/4/16.
 */
public class ExploreFragment extends Fragment {


    private ExploreAdapter exploreAdapter = null;
    private ListView exploreList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.explore_layout, container, false);
        exploreList = (ListView) view.findViewById(R.id.explore_list);
        exploreAdapter = new ExploreAdapter(getActivity(), DBHelper.getInstance(getActivity()).getPrices(), DBHelper.getInstance(getActivity()).getItems());
        exploreList.setAdapter(exploreAdapter);
        return view;
    }

}
