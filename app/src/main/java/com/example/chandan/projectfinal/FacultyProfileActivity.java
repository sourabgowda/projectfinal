package com.example.chandan.projectfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class FacultyProfileActivity extends AppCompatActivity {
    EditText editTextName, editTextEmail,editTextMobileno,editTextDept,editTextDesignation;
    Button buttonAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_profile);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextName = (EditText) findViewById(R.id.name);
        editTextMobileno = (EditText) findViewById(R.id.phonenumber);
        editTextDept=(EditText) findViewById(R.id.department);
        editTextDesignation=(EditText)findViewById(R.id.designation);

        editTextEmail.setText(SharedPrefManager.getInstance(this).getUserEmail());
        editTextName.setText(SharedPrefManager.getInstance(this).getUsername());
        editTextMobileno.setText(SharedPrefManager.getInstance(this).getUserPhone());
        editTextDept.setText(SharedPrefManager.getInstance(this).getUserDept());
        editTextDesignation.setText(SharedPrefManager.getInstance(this).getUserDesignation());

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;

        }
        return true;
    }


}
