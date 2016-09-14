package com.example.hiran.onemarket.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hiran.onemarket.R;

import java.util.List;

/**
 * Created by hiran on 9/4/16.
 */
public class ExploreAdapter extends BaseAdapter {

    private List<String> prices, titles;
    private Button addCart;
    private LayoutInflater layoutInflater;
    private Context context;
    private String TAG = ExploreAdapter.class.getSimpleName();

    public ExploreAdapter(Context context, List<String> prices, List<String> titles) {
        this.context = context;
        this.prices = prices;
        this.titles = titles;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return prices.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.explore_custom_list, null);

        } else {
            view = convertView;
            ImageView imageView = (ImageView) view.findViewById(R.id.explore_list_img);
            TextView title = (TextView) view.findViewById(R.id.explore_list_title);
            TextView price = (TextView) view.findViewById(R.id.explore_list_price);
            addCart = (Button) view.findViewById(R.id.add_cart);
            price.setText("Rs. " + prices.get(position));
            title.setText(titles.get(position));

            addCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonClickListener.onButtonClick(titles.get(position));
                }
            });

            try {
                if (titles.get(position).equals("Xperia X")) {
                    imageView.setImageResource(R.drawable.xperia_x);
                }
                if (titles.get(position).equals("Galaxy J7")) {
                    imageView.setImageResource(R.drawable.j7);
                }
                if (titles.get(position).equals("Galaxy J5")) {
                    imageView.setImageResource(R.drawable.j5);
                }
                if (titles.get(position).equals("Galaxy J2")) {
                    imageView.setImageResource(R.drawable.j2);
                }
                if (titles.get(position).equals("One Plus 3")) {
                    imageView.setImageResource(R.drawable.oneplus_3);
                }
                if (titles.get(position).equals("Galaxy Note 5")) {
                    imageView.setImageResource(R.drawable.note5);

                }
                if (titles.get(position).equals("Xperia Z3")) {
                    imageView.setImageResource(R.drawable.xperia_z3);
                }
                if (titles.get(position).equals("Xperia Z5")) {
                    imageView.setImageResource(R.drawable.xperia_z5);
                }
                if (titles.get(position).equals("Galaxy S6")) {
                    imageView.setImageResource(R.drawable.s6);
                }
                if (titles.get(position).equals("Galaxy S7")) {
                    imageView.setImageResource(R.drawable.s7);
                }
            } catch (java.lang.OutOfMemoryError e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return view;
    }

    public interface ButtonClickListener {
        void onButtonClick(String title);
    }

    private ButtonClickListener buttonClickListener = null;

    public void setButtonClickListener(ButtonClickListener buttonClickListener) {
        this.buttonClickListener = buttonClickListener;
    }
}
