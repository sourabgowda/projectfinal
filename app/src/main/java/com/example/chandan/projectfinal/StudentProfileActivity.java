package com.example.chandan.projectfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class StudentProfileActivity extends AppCompatActivity {
    EditText editTextName, editTextEmail, editTextUsn,editTextMobileno,editTextDept,editTextSem,editTextSection;
    Button buttonAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextName = (EditText) findViewById(R.id.name);
        editTextUsn = (EditText) findViewById(R.id.usn);
        editTextMobileno = (EditText) findViewById(R.id.phonenumber);
        editTextDept=(EditText) findViewById(R.id.department);
        editTextSem=(EditText)findViewById(R.id.semester);
        editTextSection=(EditText)findViewById(R.id.section);

        editTextEmail.setText(SharedPrefManager.getInstance(this).getUserEmail());
        editTextName.setText(SharedPrefManager.getInstance(this).getUsername());
        editTextUsn.setText(SharedPrefManager.getInstance(this).getUserUsn());
        editTextMobileno.setText(SharedPrefManager.getInstance(this).getUserPhone());
        editTextDept.setText(SharedPrefManager.getInstance(this).getUserDept());
        editTextSem.setText(SharedPrefManager.getInstance(this).getUserSem());
        editTextSection.setText(SharedPrefManager.getInstance(this).getUserSection());
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
