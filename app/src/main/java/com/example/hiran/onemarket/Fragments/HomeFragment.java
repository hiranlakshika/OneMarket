package com.example.hiran.onemarket.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hiran.onemarket.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiran on 9/4/16.
 */
public class HomeFragment extends Fragment{

    private List<String> homeList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_layout, container, false);
    }
}
