package com.OOD.malissa.shoopingcart.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;


import com.OOD.malissa.shoopingcart.Controllers.StoreClerk;
import com.OOD.malissa.shoopingcart.R;

import java.util.ArrayList;

/**
 * This is the Login Activity
 */
public class Login extends Activity {



    //region INSTANCE VARIABLES
    private CheckBox _checkBoxSeller;
    private Button _loginBtn;
    private Button _registerBtn;
    private EditText _userName;
    private EditText _password;
    private StoreClerk Clerk = StoreClerk.getInstance();
    private static Context context; // used to get the context of this activity. only use when onCreate of Activity has been called!
    private boolean _isInitialized;
    private boolean _isSeller = false;
    private String _usernameString;
    private String _passwordString;
    //endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _isInitialized = false;
        Login.context = getApplicationContext();


        setContentView(R.layout.login);
        setUpListeners();
    }

    /**
     * onStart() is called after onCreate(). Used to initialize teh models
     */
    @Override
    protected void onStart() {
        super.onStart();

        if(!_isInitialized) {
            //Initialize all models
            Clerk.initializeAllModel(context);
            this._isInitialized = true;
        }
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

        return super.onOptionsItemSelected(item);
    }

    /**
     * Function used to get the application's context. Only use if the application exists!
     * @return The context of this activity
     */
    public static Context getAppContext() {
        return Login.context;
    }

    private void setUpListeners(){

        //LINK UI OBJECTS TO XML HERE
        this._loginBtn = (Button)  findViewById(R.id.logInButton);
        this._password = (EditText) findViewById(R.id.passwordField);
        this._userName = (EditText) findViewById(R.id.usernameField);
        this._checkBoxSeller = (CheckBox)  findViewById(R.id.userTypeCheck);
        this._registerBtn = (Button) findViewById(R.id.register);

        /**
         * Setup the listener that takes the input from the
         * username edittext and places it into instance variable.
         */
        this._userName.addTextChangedListener(new TextWatcher() {
                  @Override
                  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                  }

                  @Override
                  public void onTextChanged(CharSequence s, int start, int before, int count) {
                      _usernameString = _userName.getText().toString();
                  }

                  @Override
                  public void afterTextChanged(Editable s) {

                  }
              }

        );

        this._registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clerk.register(_usernameString, _passwordString, _isSeller);
            }
        });

        /**
         * Setup the listener that takes the input from the
         * password edittext and places it into instance variable.
         */
        this._password.addTextChangedListener(new TextWatcher() {
                  @Override
                  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                  }

                  @Override
                  public void onTextChanged(CharSequence s, int start, int before, int count) {
                      _passwordString = _password.getText().toString();
                  }

                  @Override
                  public void afterTextChanged(Editable s) {

                  }
              }

        );

        /**
         * Setup the listener that determines if the user is logging
         * in as a seller or not.
         */
        this._checkBoxSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _isSeller = !_isSeller;
            }
        });

        /**
         * This is the login listener where logging in calls a function
         * from storeclerk.
         */
        this._loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo:  hey Paul, looking at this, it doesn't seem like we actually use _userType. I think this should be deleted
                /*if(_isSeller)
                    _userType = User.SELLER;
                else
                    _userType = User.BUYER;*/

           Clerk.login(_usernameString, _passwordString, _isSeller);

            }
        });

    }
}
