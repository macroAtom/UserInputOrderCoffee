package com.example.android.userinputordercoffee;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 *
 */
public class MainActivity extends AppCompatActivity {
    /**
     * This method will auto run when app is launcher
     *
     * @param bundle
     */

    int quantity;

    private void getQuantity() {
        try {
            quantity = Integer.parseInt(getString(R.string.initial_quantity));
        } catch (NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
    }

    ;
    /**
     * Get the currency format for the user-selected locale.
     * 获取本地的货币格式
     */
    private final NumberFormat mCurrencyFormat = NumberFormat.getCurrencyInstance();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_a);
        /**
         * 初始化数量和价格
         */
        getQuantity();
        TextView abc = (TextView) findViewById(R.id.order_summary_text_view);
        int price = quantity * 5;
        String initialPrice = String.valueOf(price);
        abc.setText(initialPrice);
    }

    /**
     * This method is to decrease quantity.
     */
    public void decrement(View view) {

        if (quantity <= 1) {
            return;
        }
        quantity -= 1;
        TextView textView = (TextView) findViewById(R.id.quantity_text_view);
        textView.setText(String.valueOf(quantity));
    }

    /**
     * This method is to increase quantity.
     */
    public void increment(View view) {
        if (quantity >= 10) {
            return;
        }
        quantity += 1;
        TextView textView = (TextView) findViewById(R.id.quantity_text_view);
        textView.setText(String.valueOf(quantity));
    }

    /**
     * This method will run when order button is clicked.
     */

    public void submitOrder(View view) {
        String priceMessage = displayMessage();
        TextView orderView = (TextView) findViewById(R.id.order_summary_text_view);
        orderView.setText(priceMessage);

//        发送邮件,邮箱为虚假邮箱
        String[] emailTo = {"abcd@126.com"};
        composeEmail(emailTo, "Intent Text Subject", priceMessage);
    }

    /**
     * 计算订单总价格
     *
     * @return
     */
    private String calculatePrice(boolean isOrderWhipped, boolean isOrderChocolate) {
        int price = 5;
        if (isOrderWhipped) {
            price += 1;
        }
        if (isOrderChocolate) {
            price += 2;
        }
        price = quantity * price;

        String totalPrice = mCurrencyFormat.format(price);
        Log.i("MainActivity", "calculatePrice: totalPrice: " + totalPrice + "\tquantitiy:" + quantity);
        return totalPrice;
    }

    /**
     * This method shows message on the device screen.
     *
     * @return
     */
    @NonNull
    private String displayMessage() {


        CheckBox whippedCream = findViewById(R.id.whipped_cream_checkbox);

        CheckBox chocolate = findViewById(R.id.chocolate_checkbox);

        boolean isOrderWhipped = whippedCream.isChecked();
        boolean isOrderChocolate = chocolate.isChecked();

        EditText nameView = (EditText) findViewById(R.id.name_edit_text_view);
        String name = nameView.getText().toString();
        String price = calculatePrice(isOrderWhipped, isOrderChocolate);

        String priceMessage = getString(R.string.hint_name) + ": " + name;
        Log.i("MainActivity", "getString(R.string.hint_name) " + getString(R.string.hint_name) + " getString(R.string.hint_name,name)");
        priceMessage += "\n" + getString(R.string.whipped_cream) + "? " + isOrderWhipped;
        priceMessage += "\n" + getString(R.string.chocolate) + "? " + isOrderChocolate;
        priceMessage += "\n" + getString(R.string.total_price) + ": " + price;
        priceMessage += "\nThank you!";

        return priceMessage;
    }

    /**
     * 通过Intent调用其他app
     *
     * @param addresses
     * @param subject
     * @param text
     */
    public void composeEmail(String[] addresses, String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
