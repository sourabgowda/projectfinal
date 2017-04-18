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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateFacultyNoticeActivity extends AppCompatActivity {
    EditText editTextTitle, editTextContent, editTextSenderDesignation,editTextSenderEmail;
    Spinner spinnerDept,spinnerDesignation,spinnerType,spinnerReceiver;
    Button buttonAdd;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_faculty_notice);

        editTextTitle = (EditText) findViewById(R.id.title);
        editTextContent = (EditText) findViewById(R.id.content);
        editTextSenderDesignation = (EditText) findViewById(R.id.sender);
        editTextSenderEmail=(EditText)findViewById(R.id.email);

        spinnerType = (Spinner) findViewById(R.id.type);
        spinnerReceiver=(Spinner)findViewById(R.id.receiver);
        spinnerDept=(Spinner) findViewById(R.id.department);
        spinnerDesignation=(Spinner)findViewById(R.id.designation);

        editTextSenderEmail.setText(SharedPrefManager.getInstance(this).getUserEmail());
        editTextSenderDesignation.setText(SharedPrefManager.getInstance(this).getUserDesignation());

        buttonAdd=(Button)findViewById(R.id.Add);
        progressDialog = new ProgressDialog(this);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(editTextTitle.getText().toString()) || !TextUtils.isEmpty(editTextContent.getText().toString())){
                    addFacultyNotice();
                }
                else{
                    if (TextUtils.isEmpty(editTextTitle.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "you should enter a Title", Toast.LENGTH_LONG).show();

                    } else if (TextUtils.isEmpty(editTextContent.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "you should enter the Content", Toast.LENGTH_LONG).show();

                    }

                }

            }
        });

    }

    private void addFacultyNotice() {
        final String title = editTextTitle.getText().toString().trim();
        final String content = editTextContent.getText().toString().trim();
        final String sender = editTextSenderDesignation.getText().toString().trim();
        final String sendermail = editTextSenderEmail.getText().toString().trim();
        final String receiver = spinnerReceiver.getSelectedItem().toString().trim();
        final String type = spinnerType.getSelectedItem().toString().trim();
        final String dept = spinnerDept.getSelectedItem().toString().trim();
        final String designation = spinnerDesignation.getSelectedItem().toString().trim();


        progressDialog.setMessage("Adding notice.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Constants.URL_ADD_FACULTY_NOTICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            String desig=SharedPrefManager.getInstance(getApplicationContext()).getUserDesignation();
                            if(desig.equalsIgnoreCase("principal")){
                                startActivity(new Intent(getApplicationContext(), PrincipalActivity.class));
                            }
                            else
                            {
                                startActivity(new Intent(getApplicationContext(), FacultyActivity.class));
                            }

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
                params.put("title", title);
                params.put("content",content);
                params.put("sender", sender);
                params.put("sendermail", sendermail);
                params.put("receiver", receiver);
                params.put("type", type);
                params.put("dept", dept);
                params.put("designation", designation);

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
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;

        }
        return true;
    }
}