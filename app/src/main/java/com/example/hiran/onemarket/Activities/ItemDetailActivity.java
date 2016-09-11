package com.example.hiran.onemarket.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.hiran.onemarket.R;
import com.example.hiran.onemarket.Util.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ItemDetailActivity extends AppCompatActivity {

    private List<String> itemTitles = new ArrayList<>();
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);
        title=(TextView)findViewById(R.id.item_name);
        itemTitles= DBHelper.getInstance(this).getItems();

        Bundle bundle = getIntent().getExtras();
        int value = bundle.getInt("Position");
        title.setText(itemTitles.get(value));
    }
}
