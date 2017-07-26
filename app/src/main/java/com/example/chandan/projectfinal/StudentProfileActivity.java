package com.example.chandan.projectfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.KeyListener;
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

import static com.example.chandan.projectfinal.R.id.textView;

public class StudentProfileActivity extends AppCompatActivity {
    EditText editTextName, editTextEmail, editTextUsn,editTextMobileno,editTextDept,editTextSem,editTextSection;
    Button buttonAdd;
    ProgressDialog progressDialog;
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
        buttonAdd=(Button)findViewById(R.id.update);
        progressDialog = new ProgressDialog(this);

        editTextMobileno.setEnabled(false);
        editTextSem.setEnabled(false);
        editTextSection.setEnabled(false);


        editTextEmail.setText(SharedPrefManager.getInstance(this).getUserEmail());
        editTextName.setText(SharedPrefManager.getInstance(this).getUsername());
        editTextUsn.setText(SharedPrefManager.getInstance(this).getUserUsn());
        editTextMobileno.setText(SharedPrefManager.getInstance(this).getUserPhone());
        editTextDept.setText(SharedPrefManager.getInstance(this).getUserDept());
        editTextSem.setText(SharedPrefManager.getInstance(this).getUserSem());
        editTextSection.setText(SharedPrefManager.getInstance(this).getUserSection());
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sectionValidator(editTextSection.getText().toString())) {
                    if (semValidator(editTextSem.getText().toString())) {
                        if (mobilenoValidator(editTextMobileno.getText().toString()))
                            updateStudent();
                        else
                            Toast.makeText(getApplicationContext(), "enter 10 digit mobile number", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "semester can be 1st|2nd|3rd|4th|5th|6th|7th|8th ", Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(getApplicationContext(), "section can be A|B|C|D ", Toast.LENGTH_LONG).show();
                }
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

    public boolean semValidator(String sem)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^(1st|2nd|3rd|4th|5th|6th|7th|8th)$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(sem);
        return matcher.matches();
    }

    public boolean sectionValidator(String section)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^(A|B|C|D)$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(section);
        return matcher.matches();
    }
    public void updateStudent(){
        final String name = editTextName.getText().toString().trim();
        final String usn = editTextUsn.getText().toString().trim();
        final String mobileno = editTextMobileno.getText().toString().trim();
        final String sem = editTextSem.getText().toString().trim();
        final String section = editTextSection.getText().toString().trim();


        progressDialog.setMessage("updating profile...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Constants.URL_UPDATE_STUDENT ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            SharedPrefManager.getInstance(getApplicationContext()).setUserPhone(editTextMobileno.getText().toString());
                            SharedPrefManager.getInstance(getApplicationContext()).setUserSem(editTextSem.getText().toString());
                            SharedPrefManager.getInstance(getApplicationContext()).setUserSection(editTextSection.getText().toString());
                            editTextMobileno.setEnabled(false);
                            editTextSem.setEnabled(false);
                            editTextSection.setEnabled(false);
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
                params.put("name",name);
                params.put("mobileno",mobileno);
                params.put("sem", sem);
                params.put("section", section);
                params.put("usn",usn);

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
                startActivity(new Intent(this, StudentActivity.class));
                finish();
                break;
            case R.id.edit:
                editTextMobileno.setEnabled(true);
                editTextSem.setEnabled(true);
                editTextSection.setEnabled(true);
                buttonAdd.setVisibility(View.VISIBLE);


        }
        return true;
    }
}
