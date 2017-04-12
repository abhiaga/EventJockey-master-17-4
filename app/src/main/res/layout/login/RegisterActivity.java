
package com.examples.eventjockey.login;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

public class RegisterActivity extends Activity
{
  private final static java.text.SimpleDateFormat SIMPLE_DATE_FORMAT = new java.text.SimpleDateFormat( "yyyy/MM/dd" );

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

  private com.examples.eventjockey.login.EventJockeyUser user;

  public void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.register );

    initUI();
  }

  private void initUI()
  {nameField = (EditText) findViewById( R.id.nameField );createdField = (EditText) findViewById( R.id.createdField );updatedField = (EditText) findViewById( R.id.updatedField );ownerIdField = (EditText) findViewById( R.id.ownerIdField );emailField = (EditText) findViewById( R.id.emailField );objectIdField = (EditText) findViewById( R.id.objectIdField );passwordField = (EditText) findViewById( R.id.passwordField );

    registerButton = (Button) findViewById( R.id.registerButton );

    final java.util.Calendar createdCalendar = java.util.Calendar.getInstance();
    createdField.setOnClickListener( new View.OnClickListener()
    {
      @Override
      public void onClick( View view )
      {
        new DatePickerDialog( com.examples.eventjockey.login.RegisterActivity.this, new DatePickerDialog.OnDateSetListener()
        {
          @Override
          public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth )
          {
            createdCalendar.set( java.util.Calendar.YEAR, year );
            createdCalendar.set( java.util.Calendar.MONTH, monthOfYear );
            createdCalendar.set( java.util.Calendar.DAY_OF_MONTH, dayOfMonth );
            createdField.setText( SIMPLE_DATE_FORMAT.format( createdCalendar.getTime() ) );
          }
        }, createdCalendar.get( java.util.Calendar.YEAR ), createdCalendar.get( java.util.Calendar.MONTH ), createdCalendar.get( java.util.Calendar.DAY_OF_MONTH ) ).show();
      }
    } );

    final java.util.Calendar updatedCalendar = java.util.Calendar.getInstance();
    updatedField.setOnClickListener( new View.OnClickListener()
    {
      @Override
      public void onClick( View view )
      {
        new DatePickerDialog( com.examples.eventjockey.login.RegisterActivity.this, new DatePickerDialog.OnDateSetListener()
        {
          @Override
          public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth )
          {
            updatedCalendar.set( java.util.Calendar.YEAR, year );
            updatedCalendar.set( java.util.Calendar.MONTH, monthOfYear );
            updatedCalendar.set( java.util.Calendar.DAY_OF_MONTH, dayOfMonth );
            updatedField.setText( SIMPLE_DATE_FORMAT.format( updatedCalendar.getTime() ) );
          }
        }, updatedCalendar.get( java.util.Calendar.YEAR ), updatedCalendar.get( java.util.Calendar.MONTH ), updatedCalendar.get( java.util.Calendar.DAY_OF_MONTH ) ).show();
      }
    } );

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
    String createdText = createdField.getText().toString().trim();
    String updatedText = updatedField.getText().toString().trim();
    String ownerIdText = ownerIdField.getText().toString().trim();
    String emailText = emailField.getText().toString().trim();
    String objectIdText = objectIdField.getText().toString().trim();
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

    if( !createdText.isEmpty() )
    {
      try
      {
        created = SIMPLE_DATE_FORMAT.parse( createdText );
      }
      catch( java.text.ParseException e )
      {
        showToast( e.getMessage() );
        return;
      }
    }

    if( !updatedText.isEmpty() )
    {
      try
      {
        updated = SIMPLE_DATE_FORMAT.parse( updatedText );
      }
      catch( java.text.ParseException e )
      {
        showToast( e.getMessage() );
        return;
      }
    }

    if( !ownerIdText.isEmpty() )
    {
      ownerId = ownerIdText;
    }

    if( !emailText.isEmpty() )
    {
      email = emailText;
    }

    if( !objectIdText.isEmpty() )
    {
      objectId = objectIdText;
    }

    if( !passwordText.isEmpty() )
    {
      password = passwordText;
    }

    user = new com.examples.eventjockey.login.EventJockeyUser();

    if( name != null )
    {
      user.setName( name );
    }

    if( created != null )
    {
      user.setCreated( created );
    }

    if( updated != null )
    {
      user.setUpdated( updated );
    }

    if( ownerId != null )
    {
      user.setOwnerId( ownerId );
    }

    if( email != null )
    {
      user.setEmail( email );
    }

    if( objectId != null )
    {
      user.setObjectId( objectId );
    }

    if( password != null )
    {
      user.setPassword( password );
    }

    Backendless.UserService.register( user, new com.examples.eventjockey.login.DefaultCallback<BackendlessUser>( com.examples.eventjockey.login.RegisterActivity.this )
    {
      @Override
      public void handleResponse( BackendlessUser response )
      {
        super.handleResponse( response );
        startActivity( new Intent( com.examples.eventjockey.login.RegisterActivity.this, RegistrationSuccessActivity.class ) );
        finish();
      }
    } );
  }

  private void showToast( String msg )
  {
    Toast.makeText( this, msg, Toast.LENGTH_SHORT ).show();
  }
}
                