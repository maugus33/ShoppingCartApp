package com.OOD.malissa.shoopingcart.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.OOD.malissa.shoopingcart.R;

import java.util.ArrayList;


public class Login extends Activity {

    //region INSTANCE VARIABLES
    private CheckBox _checkBoxSeller;
    private Button _loginBtn;
    private EditText _userName;
    private EditText _password;

    private boolean isSeller = false;
    private String username;
    private String password;
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

        _loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add function you want to call here
            }
        });

        _checkBoxSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add function you want to call here
            }
        });

        // set the user and password text??
        //_userName.setText();
        //_password.setText();



    }
}
