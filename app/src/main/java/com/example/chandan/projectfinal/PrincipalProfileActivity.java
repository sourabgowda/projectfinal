package com.example.chandan.projectfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class PrincipalProfileActivity extends AppCompatActivity {
    EditText editTextName, editTextEmail,editTextMobileno,editTextDesignation;
    Button buttonAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_profile);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextName = (EditText) findViewById(R.id.name);
        editTextMobileno = (EditText) findViewById(R.id.phonenumber);
        editTextDesignation=(EditText)findViewById(R.id.designation);

        editTextEmail.setText(SharedPrefManager.getInstance(this).getUserEmail());
        editTextName.setText(SharedPrefManager.getInstance(this).getUsername());
        editTextMobileno.setText(SharedPrefManager.getInstance(this).getUserPhone());
        editTextDesignation.setText("Principal");


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout:
                SharedPrefManager.getInstance(this).logout();
                Intent intent=new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                break;

            case R.id.Home:
                startActivity(new Intent(this, PrincipalActivity.class));
                finish();
                break;


        }
        return true;
    }
}
