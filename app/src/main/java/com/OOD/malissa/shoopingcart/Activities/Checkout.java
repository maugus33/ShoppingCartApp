package com.OOD.malissa.shoopingcart.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.OOD.malissa.shoopingcart.Controllers.BuyerClerk;
import com.OOD.malissa.shoopingcart.Controllers.SellerClerk;
import com.OOD.malissa.shoopingcart.Controllers.StoreClerk;
import com.OOD.malissa.shoopingcart.R;

import java.util.ArrayList;

public class Checkout extends Activity {

    //region INSTANCE VARIABLES
    private ArrayList<EditText> _checkoutTextFields;
    private TextView _receipt;
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
     */
    public static Context getAppContext() {
        return Checkout.context;
    }

    private void calculateTotal(){

    }

    private void setupView(){
        setContentView(R.layout.checkout);
        setupListeners();

    }

    private void setupListeners(){

        //LINK UI OBJECTS TO XML HERE
         _receipt = (TextView)findViewById(R.id.receipt);
         _purchaseBtn = (Button)findViewById(R.id.purchase_button);
         _cancelBtn =(Button)findViewById(R.id.cancel_checkout);

        _receipt.setText(BuyerClerk.getInstance().getReceipt());

        _cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        _purchaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuyerClerk.getInstance().paySeller();
                //todo: would this work? the views make a request to the controllers to take them where
                //todo:they need to go. defining the intent from the activity allows form flexibility
                //todo:when it comes to where they want to go and if they want to send extras
                //todo: but we can have a bunch of functions for traveling to various views, it's just
                //todo: that the contollers would get really big.
                Intent i = new Intent(getApplicationContext(), BrowseList.class);
                i.putExtra("User",  BuyerClerk.getInstance().currentUserType());
                BuyerClerk.getInstance().goToActivity(getApplicationContext(),i);
                //BuyerClerk.getInstance().openBrowseList(context); //Added this function to start BrowseList from Checkout
                //TODO: Can we make the activity calls in the Clerks in the form of openBrowseList(Context callingActivity)?
                //TODO: this way, the activity calls are not limited to a specific activity. Also, the BrowseList call
                //TODO: used from different activities so at least this could be done this way.

            }
        });


    }
}
