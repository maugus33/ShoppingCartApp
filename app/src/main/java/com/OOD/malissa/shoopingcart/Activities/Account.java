package com.OOD.malissa.shoopingcart.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.OOD.malissa.shoopingcart.Activities.Interfaces.Editable;
import com.OOD.malissa.shoopingcart.Models.Product;
import com.OOD.malissa.shoopingcart.R;

import java.util.ArrayList;

public class Account extends Activity implements Editable {

    //region INSTANCE VARIABLES
    private ArrayList<Product> _products;
    private Button _editBtn;
    private Button _saveBtn;
    private EditText _userName;
    private EditText _password;
    private static Context context; // used to get the context of this activity. only use when onCreate of Activity has been called!
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Account.context = getApplicationContext();
        setUpListeners();
        setContentView(R.layout.account);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_account, menu);
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
        return Account.context;
    }

    /**
     * Private method used to setUp UI listeners based on User that logged in
     */
    private void setUpListeners(){

        //LINK UI OBJECTS TO XML HERE
        //_editBtn = (Button)  findViewById(R.id.mybutton);
        //_saveBtn = (Button)  findViewById(R.id.mybutton);
        //_password = (EditText) findViewById(R.id.mybutton);
        //_userName = (EditText) findViewById(R.id.mybutton);

        //setting visibility of saveBtn to initially hide it
        //reference: http://stackoverflow.com/questions/6173400/how-to-programmatically-hide-a-button-in-android-sdk
        _saveBtn.setVisibility(View.GONE);

        _editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hide the edit button and show the save button
                _editBtn.setVisibility(View.GONE);
                _saveBtn.setVisibility(View.VISIBLE);

                // add function you want to call here
            }
        });
        _saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hide the save button and show the edit button
                _editBtn.setVisibility(View.VISIBLE);
                _saveBtn.setVisibility(View.GONE);

                // add function you want to call here
                        }
        });



        // set the user and password text??
        //_userName.setText();
        //_password.setText();

    }

    @Override
    public void makeTextEditable(View viewObject) {

    }

    @Override
    public void convertBack(View viewObject) {

    }

    @Override
    public boolean callConfirmDialogBox() {
        return false;
    }
}
