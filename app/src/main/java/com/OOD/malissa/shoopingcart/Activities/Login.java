package com.OOD.malissa.shoopingcart.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

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
    private EditText _userName;
    private EditText _password;
    private StoreClerk Clerk = StoreClerk.getInstance();
    private static Context context; // used to get the context of this activity. only use when onCreate of Activity has been called!
    //endregion



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Login.context = getApplicationContext();

        setUpListeners();
        setContentView(R.layout.login);
    }

    /**
     * onStart() is called after onCreate(). Used to initialize teh models
     */
    @Override
    protected void onStart() {
        super.onStart();

        //Initialize all models
        Clerk.initializeAllModel(context);

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

    /**
     * Function used to get the application's context. Only use if the application exists!
     * @return The context of this activity
     */
    public static Context getAppContext() {
        return Login.context;
    }

    private void setUpListeners(){

        //LINK UI OBJECTS TO XML HERE
        //_loginBtn = (Button)  findViewById(R.id.mybutton);
        //_password = (EditText) findViewById(R.id.mybutton);
        //_userName = (EditText) findViewById(R.id.mybutton);
        //_checkBoxSeller = (CheckBox)  findViewById(R.id.mybutton);

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
