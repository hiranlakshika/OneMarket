package com.example.hiran.onemarket.Activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hiran.onemarket.R;
import com.example.hiran.onemarket.Util.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ItemDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = ItemDetailActivity.class.getSimpleName();
    private List<String> itemTitles = new ArrayList<>();
    private List<String> itemPrices = new ArrayList<>();
    private List<String> brand = new ArrayList<>();
    private Button addCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);
        TextView title = (TextView) findViewById(R.id.detail_item_name);
        itemTitles = DBHelper.getInstance(this).getItems();
        itemPrices = DBHelper.getInstance(this).getPrices();
        brand = DBHelper.getInstance(this).getBrand();
        TextView itemPrice = (TextView) findViewById(R.id.detail_price);
        TextView itemBrand = (TextView) findViewById(R.id.brand);
        addCart = (Button) findViewById(R.id.detail_add_cart);
        ImageView imageView = (ImageView) findViewById(R.id.detail_image);
        addCart.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("Position");
        title.setText(itemTitles.get(position));
        itemPrice.setText("Rs." + itemPrices.get(position));
        itemBrand.setText("Brand : " + brand.get(position));

        try {
            if (itemTitles.get(position).equals("Xperia X")) {
                imageView.setImageResource(R.drawable.xperia_x);
            }
            if (itemTitles.get(position).equals("Galaxy J7")) {
                imageView.setImageResource(R.drawable.j7);
            }
            if (itemTitles.get(position).equals("Galaxy J5")) {
                imageView.setImageResource(R.drawable.j5);
            }
            if (itemTitles.get(position).equals("Galaxy J2")) {
                imageView.setImageResource(R.drawable.j2);
            }
            if (itemTitles.get(position).equals("One Plus 3")) {
                imageView.setImageResource(R.drawable.oneplus_3);
            }
            if (itemTitles.get(position).equals("Galaxy Note 5")) {
                imageView.setImageResource(R.drawable.note5);

            }
            if (itemTitles.get(position).equals("Xperia Z3")) {
                imageView.setImageResource(R.drawable.xperia_z3);
            }
            if (itemTitles.get(position).equals("Xperia Z5")) {
                imageView.setImageResource(R.drawable.xperia_z5);
            }
            if (itemTitles.get(position).equals("Galaxy S6")) {
                imageView.setImageResource(R.drawable.s6);
            }
            if (itemTitles.get(position).equals("Galaxy S7")) {
                imageView.setImageResource(R.drawable.s7);
            }
        } catch (OutOfMemoryError e) {
            Log.e(TAG, e.getMessage());
        }

    }

    @Override
    public void onClick(View v) {
        showMessage("Congratulations", "Adding cart successful");
        super.onBackPressed();
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
