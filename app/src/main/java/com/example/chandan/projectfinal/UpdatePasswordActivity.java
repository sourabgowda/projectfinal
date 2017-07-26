package com.example.chandan.projectfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class UpdatePasswordActivity extends AppCompatActivity {
EditText editTextnewpass,editTextConfirmnewpass;
    Button buttonUpdate;
    ProgressDialog progressDialog;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        editTextnewpass = (EditText) findViewById(R.id.newpass);
        editTextConfirmnewpass = (EditText) findViewById(R.id.newpassconfirm);
        progressDialog = new ProgressDialog(this);

        buttonUpdate=(Button)findViewById(R.id.update);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String designation=SharedPrefManager.getInstance(getApplicationContext()).getUserDesignation();
                if(designation.equalsIgnoreCase("Principal"))
                    updatePrincipalPassword();
                else
                    updateFacultyPassword();

            }
        });

    }

    public void updatePrincipalPassword(){
        final String password = editTextnewpass.getText().toString().trim();
        final String email = SharedPrefManager.getInstance(getApplicationContext()).getUserEmail();


        progressDialog.setMessage("updating password...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Constants.URL_UPDATE_PRINCIPAL_PASSWORD ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                                startActivity(new Intent(getApplicationContext(), PrincipalActivity.class));


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
                params.put("password",password);
                params.put("email",email);

                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);


    }
    public  void updateFacultyPassword(){
        final String password = editTextnewpass.getText().toString().trim();
        final String email = SharedPrefManager.getInstance(getApplicationContext()).getUserEmail();


        progressDialog.setMessage("updating password...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Constants.URL_UPDATE_FACULTY_PASSWORD ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), FacultyActivity.class));
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
                params.put("password",password);
                params.put("email",email);

                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);



    }
}
