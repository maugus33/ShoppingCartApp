package com.OOD.malissa.shoopingcart.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.OOD.malissa.shoopingcart.Controllers.BuyerClerk;
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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
                BuyerClerk.getInstance().openBrowseList(context); //Added this function to start BrowseList from Checkout
                //TODO: Can we make the activity calls in the Clerks in the form of openBrowseList(Context callingActivity)?
                //TODO: this way, the activity calls are not limited to a specific activity. Also, the BrowseList call
                //TODO: used from different activities so at least this could be done this way.

            }
        });


    }
}
