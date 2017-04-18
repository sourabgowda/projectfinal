package com.example.chandan.projectfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class LoginActivity extends AppCompatActivity {

    private EditText editTextName, editTextPassword;
    private Spinner spinnerDesignation;
    Button buttonLogin;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(SharedPrefManager.getInstance(this).isLoggedIn()==1){
            finish();
            startActivity(new Intent(this, StudentActivity.class));
            return;
        }
        else if(SharedPrefManager.getInstance(this).isLoggedIn()==2){
            finish();
            startActivity(new Intent(this, FacultyActivity.class));
            return;
        }
        else if(SharedPrefManager.getInstance(this).isLoggedIn()==3){
            finish();
            startActivity(new Intent(this, PrincipalActivity.class));
            return;
        }


        editTextName = (EditText) findViewById(R.id.name);
        editTextPassword = (EditText) findViewById(R.id.password);
        buttonLogin = (Button) findViewById(R.id.login);
        spinnerDesignation=(Spinner)findViewById(R.id.designation);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String designation=spinnerDesignation.getSelectedItem().toString();

                if(designation.equalsIgnoreCase("principal")){
                    if (!TextUtils.isEmpty(editTextName.getText().toString()) || !TextUtils.isEmpty(editTextPassword.getText().toString())){
                        userLogin();
                    }
                    else{
                        if (TextUtils.isEmpty(editTextName.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "you should enter a name", Toast.LENGTH_LONG).show();

                        } else if (TextUtils.isEmpty(editTextPassword.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "you should enter a Password", Toast.LENGTH_LONG).show();

                        }
                    }
                }
                else if(designation.equalsIgnoreCase("faculty")){
                    if (!TextUtils.isEmpty(editTextName.getText().toString()) || !TextUtils.isEmpty(editTextPassword.getText().toString())){
                        facultyLogin();
                    }
                    else{
                        if (TextUtils.isEmpty(editTextName.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "you should enter a name", Toast.LENGTH_LONG).show();

                        } else if (TextUtils.isEmpty(editTextPassword.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "you should enter a Password", Toast.LENGTH_LONG).show();

                        }
                    }

                }
                else{
                    if (!TextUtils.isEmpty(editTextName.getText().toString()) || !TextUtils.isEmpty(editTextPassword.getText().toString())){
                        studentLogin();
                    }
                    else{
                        if (TextUtils.isEmpty(editTextName.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "you should enter a name", Toast.LENGTH_LONG).show();

                        } else if (TextUtils.isEmpty(editTextPassword.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "you should enter a Phone Number", Toast.LENGTH_LONG).show();

                        }
                    }

                }
            }
        });

    }

  //Principal Login----------------------------------------------------
    private void userLogin(){
        final String name = editTextName.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")){
                                SharedPrefManager.getInstance(getApplicationContext())
                                        .userLogin(
                                                obj.getString("mobileno"),
                                                obj.getString("name"),
                                                obj.getString("email")
                                        );
                                startActivity(new Intent(getApplicationContext(), PrincipalActivity.class));
                                finish();
                            }else{
                                Toast.makeText(
                                        getApplicationContext(),
                                        obj.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();

                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("password", password);
                return params;
            }

        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

  //Faculty login----------------------------------------------------------------------------
    private void facultyLogin(){
        final String name = editTextName.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_FACULTY_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")){
                                SharedPrefManager.getInstance(getApplicationContext())
                                        .facultyLogin(
                                                obj.getString("mobileno"),
                                                obj.getString("name"),
                                                obj.getString("email"),
                                                obj.getString("dept"),
                                                obj.getString("designation")
                                        );
                                startActivity(new Intent(getApplicationContext(), FacultyActivity.class));
                                finish();
                            }else{
                                Toast.makeText(
                                        getApplicationContext(),
                                        obj.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();

                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("password", password);
                return params;
            }

        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

//Student Login---------------------------------------------------------------------------
private void studentLogin(){
    final String name = editTextName.getText().toString().trim();
    final String password = editTextPassword.getText().toString().trim();

    progressDialog.show();

    StringRequest stringRequest = new StringRequest(
            Request.Method.POST,
            Constants.URL_STUDENT_LOGIN,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    try {
                        JSONObject obj = new JSONObject(response);
                        if(!obj.getBoolean("error")){
                            SharedPrefManager.getInstance(getApplicationContext())
                                    .studentLogin(
                                            obj.getString("mobileno"),
                                            obj.getString("name"),
                                            obj.getString("email"),
                                            obj.getString("dept"),
                                            obj.getString("sem"),
                                            obj.getString("usn"),
                                            obj.getString("section")
                                    );
                            startActivity(new Intent(getApplicationContext(), StudentActivity.class));
                            finish();
                        }
                        else
                            {
                            Toast.makeText(
                                    getApplicationContext(),
                                    obj.getString("message"),
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();

                    Toast.makeText(
                            getApplicationContext(),
                            error.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();
                }
            }
    ){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            params.put("name", name);
            params.put("password", password);
            return params;
        }

    };

    RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
}

}
