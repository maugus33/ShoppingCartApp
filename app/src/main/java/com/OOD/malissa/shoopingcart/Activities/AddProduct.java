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

import com.OOD.malissa.shoopingcart.Controllers.BuyerClerk;
import com.OOD.malissa.shoopingcart.Controllers.SellerClerk;
import com.OOD.malissa.shoopingcart.Controllers.StoreClerk;
import com.OOD.malissa.shoopingcart.R;


public class AddProduct extends Activity {

    //region INSTANCE VARIABLES
    private EditText _prodName;
    private EditText _prodDesc;
    private EditText _prodIPrice;
    private EditText _prodSPrice;
    private EditText _prodQuant;
    private EditText _prodType;
    private Button _cancelBtn;
    private Button _saveBtn;
    private static Context context; // used to get the context of this activity. only use when onCreate of Activity has been called!
    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        AddProduct.context = getApplicationContext();
        setupView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_product, menu);
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
        return AddProduct.context;
    }

    private void setupView(){
        setupListeners();
        setContentView(R.layout.add_product);

    }

    private void setupListeners(){

        //LINK UI OBJECTS TO XML HERE
        //_prodName = (EditText)findViewById(R.id.mybutton);
        // _prodDesc = (EditText)findViewById(R.id.mybutton);
        // _prodIPrice = (EditText)findViewById(R.id.mybutton);
        // _prodSPrice = (EditText)findViewById(R.id.mybutton);
        // _prodQuant =(EditText)findViewById(R.id.mybutton);
        // _prodType =(EditText)findViewById(R.id.mybutton);

        _cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add function you want to call here
            }
        });

        _saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add function you want to call here
            }
        });
    }
}
