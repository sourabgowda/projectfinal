package com.example.chandan.projectfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class AddPrincipalActivity extends AppCompatActivity {
     EditText editTextName, editTextEmail, editTextPassword,editTextMobileno;
     Button buttonAdd;
     ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_principal);

        editTextEmail = (EditText) findViewById(R.id.email);
        editTextName = (EditText) findViewById(R.id.name);
        editTextPassword = (EditText) findViewById(R.id.password);
        editTextMobileno = (EditText) findViewById(R.id.phonenumber);
        buttonAdd=(Button)findViewById(R.id.Add);
        progressDialog = new ProgressDialog(this);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(editTextName.getText().toString()) || !TextUtils.isEmpty(editTextPassword.getText().toString()) || !TextUtils.isEmpty(editTextMobileno.getText().toString())|| !TextUtils.isEmpty(editTextEmail.getText().toString())){
                    registerUser();
                }
                else{
                    if (TextUtils.isEmpty(editTextName.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "you should enter a name", Toast.LENGTH_LONG).show();

                    } else if (TextUtils.isEmpty(editTextPassword.getText().toString())) {
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
    private void registerUser() {
        final String email = editTextEmail.getText().toString().trim();
        final String name = editTextName.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String mobileno = editTextMobileno.getText().toString().trim();


        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Constants.URL_REGISTER ,
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
                params.put("email", email);
                params.put("password", password);
                params.put("mobileno", mobileno);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
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

        }
        return true;
    }


}
