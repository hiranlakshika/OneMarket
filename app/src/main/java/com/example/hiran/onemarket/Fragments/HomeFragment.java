package com.example.hiran.onemarket.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.hiran.onemarket.Activities.ItemDetailActivity;
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout, container, false);
        homeAdapter = new HomeAdapter(view.getContext(), DBHelper.getInstance(getActivity()).getPrices(), DBHelper.getInstance(getActivity()).getItems());
        homeGridView = (GridView) view.findViewById(R.id.home_grid_view);
        homeGridView.setAdapter(homeAdapter);

        homeGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();

                Toast.makeText(getActivity(),
                        "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
                bundle.putInt("Position", position);
                Intent intent = new Intent(getActivity(),ItemDetailActivity.class);
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

}
