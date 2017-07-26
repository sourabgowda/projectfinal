package com.example.chandan.projectfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FacultyProfileActivity extends AppCompatActivity {
    EditText editTextName, editTextEmail,editTextMobileno,editTextDept,editTextDesignation;
    Button buttonAdd;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_profile);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextName = (EditText) findViewById(R.id.name);
        editTextMobileno = (EditText) findViewById(R.id.phonenumber);
        editTextDept=(EditText) findViewById(R.id.department);
        editTextDesignation=(EditText)findViewById(R.id.designation);
        buttonAdd=(Button)findViewById(R.id.Add);
        progressDialog = new ProgressDialog(this);

        editTextMobileno.setEnabled(false);

        editTextEmail.setText(SharedPrefManager.getInstance(this).getUserEmail());
        editTextName.setText(SharedPrefManager.getInstance(this).getUsername());
        editTextMobileno.setText(SharedPrefManager.getInstance(this).getUserPhone());
        editTextDept.setText(SharedPrefManager.getInstance(this).getUserDept());
        editTextDesignation.setText(SharedPrefManager.getInstance(this).getUserDesignation());
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        if (mobilenoValidator(editTextMobileno.getText().toString()))
                            updateStudent();
                        else
                            Toast.makeText(getApplicationContext(), "enter 10 digit mobile number", Toast.LENGTH_LONG).show();

            }
        });

    }
    public boolean mobilenoValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[0-9]{10}$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void updateStudent(){

        final String mobileno = editTextMobileno.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();



        progressDialog.setMessage("updating profile...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Constants.URL_UPDATE_FACULTY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            SharedPrefManager.getInstance(getApplicationContext()).setUserPhone(editTextMobileno.getText().toString());
                            editTextMobileno.setEnabled(false);
                            buttonAdd.setVisibility(View.INVISIBLE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("mobileno",mobileno);
                params.put("email", email);

                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update, menu);
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
                startActivity(new Intent(this, FacultyActivity.class));
                finish();
                break;
            case R.id.edit:
                editTextMobileno.setEnabled(true);
                buttonAdd.setVisibility(View.VISIBLE);



        }
        return true;
    }


}
