package com.example.hiran.onemarket.Fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hiran.onemarket.R;
import com.example.hiran.onemarket.Util.DBHelper;


/**
 * Created by hiran on 9/18/16.
 */
public class ViewCartFragment extends Fragment {

    private TextView quantity, total;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_cart, container, false);
        quantity = (TextView) view.findViewById(R.id.itemQuantity);
        total = (TextView) view.findViewById(R.id.itemTotal);

        total.setText(Integer.toString(DBHelper.getInstance(getActivity()).getTotal()));
        quantity.setText(Integer.toString(DBHelper.getInstance(getActivity()).getQuantity()));

        final Button checkout = (Button) view.findViewById(R.id.checkout);
        if (DBHelper.getInstance(getActivity()).getQuantity() == 0) {
            checkout.setEnabled(false);
        } else {
            checkout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

                    // set title
                    alertDialogBuilder.setTitle("Alert !");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Do you want to buy all items?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // if this button is clicked, close
                                    // current activity
                                    showMessage("Thanks", "Your items will be delivered soon");
                                    total.setText("0");
                                    quantity.setText("0");
                                    DBHelper.getInstance(getActivity()).dropCart();
                                    DBHelper.getInstance(getActivity()).createDB();
                                    checkout.setEnabled(false);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    dialog.cancel();
                                }
                            });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();
                }
            });
        }

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
