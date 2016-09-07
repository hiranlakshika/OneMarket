package com.example.hiran.onemarket.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.hiran.onemarket.Adapters.ExploreAdapter;
import com.example.hiran.onemarket.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiran on 9/4/16.
 */
public class ExploreFragment extends Fragment {


    private ExploreAdapter exploreAdapter = null;
    private ListView exploreList;

    private List<String> sendItems() {
        List<String> explore = new ArrayList<>();
        explore.add("100");
        explore.add("200");
        explore.add("110");
        explore.add("120");
        return explore;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.explore_layout, container, false);
        exploreList = (ListView) view.findViewById(R.id.explore_list);
        exploreAdapter = new ExploreAdapter(getActivity(), sendItems());
        exploreList.setAdapter(exploreAdapter);
        return view;
    }
}
