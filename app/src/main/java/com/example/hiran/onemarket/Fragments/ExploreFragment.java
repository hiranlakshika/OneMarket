package com.example.hiran.onemarket.Fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.hiran.onemarket.Adapters.ExploreAdapter;
import com.example.hiran.onemarket.R;
import com.example.hiran.onemarket.Util.DBHelper;

/**
 * Created by hiran on 9/4/16.
 */
public class ExploreFragment extends Fragment {


    private ExploreAdapter exploreAdapter = null;
    private ListView exploreList;

    private ExploreAdapter.ButtonClickListener buttonClickListener = new ExploreAdapter.ButtonClickListener() {
        @Override
        public void onButtonClick(String title) {
            showMessage(title,"Added successfully");
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.explore_layout, container, false);
        exploreList = (ListView) view.findViewById(R.id.explore_list);
        exploreAdapter = new ExploreAdapter(getActivity(), DBHelper.getInstance(getActivity()).getPrices(), DBHelper.getInstance(getActivity()).getItems());
        exploreList.setAdapter(exploreAdapter);
        exploreAdapter.setButtonClickListener(buttonClickListener);
        return view;
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
