package com.OOD.malissa.shoopingcart.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.OOD.malissa.shoopingcart.Controllers.BuyerClerk;
import com.OOD.malissa.shoopingcart.Controllers.SellerClerk;
import com.OOD.malissa.shoopingcart.Controllers.StoreClerk;
import com.OOD.malissa.shoopingcart.R;

import java.util.ArrayList;

public class Checkout extends Activity {

    //region INSTANCE VARIABLES
    private TextView _receiptNames;
    private TextView _receiptPrices;
    private Button _purchaseBtn;
    private Button _cancelBtn;
    private static Context context; // used to get the context of this activity. only use when onCreate of Activity has been called!
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Checkout.context = getApplicationContext();
        setupView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_checkout, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //if logout was clicked...
        if (id == R.id.logout) {
            // restart storeclerks
            BuyerClerk.getInstance().reset();
            SellerClerk.getInstance().reset();
            StoreClerk.getInstance().reset();

            // redirect user to login screen
            Intent i = new Intent(getApplicationContext(), Login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(i);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Function used to get the application's context. Only use if the application exists!
     * @return The context of this activity
     * @author Malissa Augustin
     */
    public static Context getAppContext() {
        return Checkout.context;
    }

    /**
     * This is a method that connects the activity to the UI xml.
     * @author Paul Benedict Reyes
     */
    private void setupView(){
        setContentView(R.layout.checkout);
        setupListeners();

    }

    /**
     * This is a method that sets up the UI objects listeners.
     * @author Paul Benedict Reyes
     */
    private void setupListeners(){

        //LINK UI OBJECTS TO XML HERE
        _receiptNames = (TextView)findViewById(R.id.checkoutNames);
        _receiptPrices = (TextView)findViewById(R.id.checkoutPrices);
        _purchaseBtn = (Button)findViewById(R.id.purchase_button);
        _cancelBtn =(Button)findViewById(R.id.cancel_checkout);

        //Changed from a giant block of textview to linear layout. 4/19/15
        ArrayList<String> receipt = BuyerClerk.getInstance().getReceipt();
        _receiptNames.setText(receipt.get(0));
        _receiptPrices.setText(receipt.get(1));

        _cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        _purchaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pay the proper seller the proper amount of money
                BuyerClerk.getInstance().paySeller();

                // post toast
                Toast.makeText(getAppContext(), "Items Purchased. Thank you.",
                        Toast.LENGTH_LONG).show();

                //Return to the BrowseList
                Intent i = new Intent(getApplicationContext(), BrowseList.class);
                i.putExtra("User",  BuyerClerk.getInstance().currentUserType());
                BuyerClerk.getInstance().goToActivity(getApplicationContext(),i);

            }
        });


    }
}
