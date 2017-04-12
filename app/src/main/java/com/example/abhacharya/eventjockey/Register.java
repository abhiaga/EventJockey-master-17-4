package com.example.abhacharya.eventjockey;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

public class Register extends AppCompatActivity {
    private EditText nameField;
    private EditText createdField;
    private EditText updatedField;
    private EditText ownerIdField;
    private EditText emailField;
    private EditText objectIdField;
    private EditText passwordField;

    private Button registerButton;

    private String name;
    private java.util.Date created;
    private java.util.Date updated;
    private String ownerId;
    private String email;
    private String objectId;
    private String password;

    private EventJockeyUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initUI();
    }

    private void initUI()
    {nameField = (EditText) findViewById( R.id.name );emailField = (EditText) findViewById( R.id.emailID);passwordField = (EditText) findViewById( R.id.password);

        registerButton = (Button) findViewById( R.id.register );

    registerButton.setOnClickListener( new View.OnClickListener()
    {
        @Override
        public void onClick( View view )
        {
            onRegisterButtonClicked();
        }
    } );




    }
    private void onRegisterButtonClicked()
    {
        String nameText = nameField.getText().toString().trim();
       // String createdText = createdField.getText().toString().trim();
        //String updatedText = updatedField.getText().toString().trim();
        //String ownerIdText = ownerIdField.getText().toString().trim();
        String emailText = emailField.getText().toString().trim();
        //String objectIdText = objectIdField.getText().toString().trim();
        String passwordText = passwordField.getText().toString().trim();

        if ( emailText.isEmpty() )
        {
            showToast( "Field 'email' cannot be empty." );
            return;
        }

        if ( passwordText.isEmpty() )
        {
            showToast( "Field 'password' cannot be empty." );
            return;
        }

        if( !nameText.isEmpty() )
        {
            name = nameText;
        }







        if( !emailText.isEmpty() )
        {
            email = emailText;
        }



        if( !passwordText.isEmpty() )
        {
            password = passwordText;
        }

        user = new EventJockeyUser();

        if( name != null )
        {
            user.setName( name );
        }


        if( email != null )
        {
            user.setEmail( email );
        }


        if( password != null )
        {
            user.setPassword( password );
        }

        Backendless.UserService.register( user, new DefaultCallback<BackendlessUser>( Register.this )
        {
            @Override
            public void handleResponse( BackendlessUser response )
            {
                super.handleResponse( response );
                startActivity( new Intent( Register.this, LoginActivity.class ) );
                finish();
            }
        } );
    }

    private void showToast( String msg )
    {
        Toast.makeText( this, msg, Toast.LENGTH_SHORT ).show();
    }
}





