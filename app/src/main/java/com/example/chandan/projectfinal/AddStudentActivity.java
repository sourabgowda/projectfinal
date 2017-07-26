package com.example.chandan.projectfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddStudentActivity extends AppCompatActivity {
    EditText editTextName, editTextEmail, editTextUsn,editTextMobileno;
    Spinner spinnerDept,spinnerSem,spinnerSection;
    Button buttonAdd;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        editTextEmail = (EditText) findViewById(R.id.email);
        editTextName = (EditText) findViewById(R.id.name);
        editTextUsn = (EditText) findViewById(R.id.usn);
        editTextMobileno = (EditText) findViewById(R.id.phonenumber);
        spinnerDept=(Spinner) findViewById(R.id.department);
        spinnerSem=(Spinner)findViewById(R.id.semester);
        spinnerSection=(Spinner)findViewById(R.id.section);

        buttonAdd=(Button)findViewById(R.id.Add);
        progressDialog = new ProgressDialog(this);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(editTextName.getText().toString()) || !TextUtils.isEmpty(editTextUsn.getText().toString()) || !TextUtils.isEmpty(editTextMobileno.getText().toString())|| !TextUtils.isEmpty(editTextEmail.getText().toString())){
                    if(emailValidator(editTextEmail.getText().toString()))
                    {
                        if(usnValidator(editTextUsn.getText().toString()))
                        {
                        if (mobilenoValidator(editTextMobileno.getText().toString()))
                            registerUser();
                        else
                            Toast.makeText(getApplicationContext(), "enter 10 digit mobile number", Toast.LENGTH_LONG).show();
                    }else {
                            Toast.makeText(getApplicationContext(), "enter usn in correct format with caps", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "enter email in correct format", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    if (TextUtils.isEmpty(editTextName.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "you should enter a name", Toast.LENGTH_LONG).show();

                    } else if (TextUtils.isEmpty(editTextUsn.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "you should enter a Phone Number", Toast.LENGTH_LONG).show();

                    }
                    else if (TextUtils.isEmpty(editTextMobileno.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "you should enter a password", Toast.LENGTH_LONG).show();

                    }
                    else if (TextUtils.isEmpty(editTextEmail.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "you should enter a Email in the right format", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
    }
    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
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
    public boolean usnValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^1DA([0-9]{2})([A-Z]{2})([0-9]{3})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void registerUser() {
        final String email = editTextEmail.getText().toString().trim();
        final String name = editTextName.getText().toString().trim();
        final String usn = editTextUsn.getText().toString().trim();
        final String mobileno = editTextMobileno.getText().toString().trim();
        final String dept = spinnerDept.getSelectedItem().toString().trim();
        final String sem = spinnerSem.getSelectedItem().toString().trim();
        final String section = spinnerSection.getSelectedItem().toString().trim();


        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Constants.URL_STUDENTREGISTER ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

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
                params.put("name", name);
                params.put("usn", usn);
                params.put("mobileno", mobileno);
                params.put("email", email);
                params.put("dept", dept);
                params.put("sem", sem);
                params.put("section",section);

                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);


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
                if(SharedPrefManager.getInstance(this).getUserDesignation().toString().equalsIgnoreCase("Principal")){
                    startActivity(new Intent(this, PrincipalActivity.class));}
                else
                    startActivity(new Intent(this,FacultyActivity.class));
                break;

        }
        return true;
    }

}
