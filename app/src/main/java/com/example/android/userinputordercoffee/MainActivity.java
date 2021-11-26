package com.example.android.userinputordercoffee;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

/**
 *
 */
public class MainActivity extends Activity {
    /**
     * This method will auto run when app is launcher
     *
     * @param bundle
     */

    int quantity = 2;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_a);
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

//        发送邮件
//        String[] emailTo = {"abcd@126.com"};
//        composeEmail(emailTo,"Intent Text Subject","Hello 你们好吗");
    }

    /**
     * 计算订单总价格
     * @return
     */
    private int calculatePrice(boolean isOrderWhipped,boolean isOrderChocolate){
        int price = 5;
        if(isOrderWhipped){
            price +=1;
        }
        if(isOrderChocolate){
            price +=2;
        }
        int totalPrice = quantity * price;
        Log.i("MainActivity", "calculatePrice: totalPrice: "+totalPrice+"\tquantitiy:"+quantity);
        return totalPrice;
    }

    /**
     * This method shows message on the device screen.
     * @return
     */
    @NonNull
    private String displayMessage(){


        CheckBox whippedCream = findViewById(R.id.whipped_cream_checkbox);

        CheckBox chocolate = findViewById(R.id.chocolate_checkbox);

        boolean isOrderWhipped=whippedCream.isChecked();
        boolean isOrderChocolate=chocolate.isChecked();

        EditText nameView = (EditText) findViewById(R.id.name_edit_text_view);
        String name = nameView.getText().toString();
        int price = calculatePrice(isOrderWhipped,isOrderChocolate);

        String priceMessage = "Name: " + name;
        priceMessage += "\nadd whippedCream? " + isOrderWhipped;
        priceMessage += "\nadd chocolate? " + isOrderChocolate;
        priceMessage += "\nToTal:" + price;
        priceMessage += "\nThank you!";

        return priceMessage;
    }

//    /**
//     * 通过Intent调用其他app
//     * @param addresses
//     * @param subject
//     * @param text
//     */
//    public void composeEmail(String[] addresses, String subject, String text) {
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setType("*/*");
//        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
//        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
//        intent.putExtra(Intent.EXTRA_TEXT,text);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }

}
