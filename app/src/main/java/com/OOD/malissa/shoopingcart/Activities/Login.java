package com.OOD.malissa.shoopingcart.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;
import com.OOD.malissa.shoopingcart.Controllers.*;
import com.OOD.malissa.shoopingcart.R;

import java.util.ArrayList;


public class Login extends Activity {

    //region INSTANCE VARIABLES
    private CheckBox _checkBoxSeller;
    private Button _loginBtn;
    private EditText _userName;
    private EditText _password;

    private User _userType;
    private boolean _isSeller = false;
    private String _usernameString;
    private String _passwordString;
    //endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpListeners();
        setContentView(R.layout.login);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long0
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpListeners(){

        //LINK UI OBJECTS TO XML HERE
        _loginBtn = (Button)  findViewById(R.id.logInButton);
        _password = (EditText) findViewById(R.id.passwordField);
        _userName = (EditText) findViewById(R.id.usernameField);
        _checkBoxSeller = (CheckBox)  findViewById(R.id.userTypeCheck);


        /**
         * Setup the listener that takes the input from the
         * username edittext and places it into instance variable.
         */
        _userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _usernameString = _userName.getText().toString();
            }

        });

        /**
         * Setup the listener that takes the input from the
         * password edittext and places it into instance variable.
         */
        _password.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
            _passwordString = _password.getText().toString();
           }

        });

        /**
         * Setup the listener that determines if the user is logging
         * in as a seller or not.
         */
        _checkBoxSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _isSeller = !_isSeller;
            }
        });

        /**
         * This is the login listener where logging in calls a function
         * from storeclerk.
         */
        _loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(_isSeller)
                    _userType = User.SELLER;
                else
                    _userType = User.BUYER;

            StoreClerk.getInstance().verifyAccount(_usernameString, _passwordString, _isSeller);

            }
        });

    }
}
