package com.example.hiran.onemarket.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hiran.onemarket.R;

/**
 * Created by hiran on 9/18/16.
 */
public class ViewCartFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_cart, container, false);
    }
}
