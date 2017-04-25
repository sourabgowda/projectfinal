package com.example.chandan.projectfinal;


import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FacultyInboxActivity extends AppCompatActivity {

    RecyclerView rvItemFaculty;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_inbox2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rvItemFaculty=(RecyclerView)findViewById(R.id.rvItemFaculty);
        rvItemFaculty.setHasFixedSize(true);

        LinearLayoutManager manager=new LinearLayoutManager(this);
        rvItemFaculty.setLayoutManager(manager);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");


        /* ArrayList<Fnotice> listitem=generatedDummy();

         FacultyAdapter adapter=new FacultyAdapter(getApplicationContext(),listitem);
        rvItemFaculty.setAdapter(adapter);
*/
        loadRecyclerViewData();

    }

  /*  private ArrayList<Fnotice> generatedDummy(){
        ArrayList<Fnotice> items=new ArrayList<>();
        for(int i=0;i<10;i++){
            Fnotice item=new Fnotice("asfafsj","asfafsj","asfafsj","asfafsj","asfafsj","asfafsj","asfafsj","asfafsj","asfafsj");
            items.add(item);

        }
        return items;
    }
*/
  private void loadRecyclerViewData(){
      final String receiver=SharedPrefManager.getInstance(this).getUsername();
      final String type=SharedPrefManager.getInstance(this).getUserType();
      final String dept=SharedPrefManager.getInstance(this).getUserDept();
      final String designation=SharedPrefManager.getInstance(this).getUserDesignation();




      progressDialog.show();

      StringRequest stringRequest = new StringRequest(
              Request.Method.POST,
              Constants.URL_GET_FACULTY_NOTICE,
              new Response.Listener<String>() {
                  @Override
                  public void onResponse(String response) {
                      progressDialog.dismiss();
                      Log.d(response,"responses");
                      try {

                          int j=1;
                          if(j==1){
                              JSONArray array=new JSONArray(response);
                              ArrayList<Fnotice> listitem=new ArrayList<>();
                              for(int i=0;i<array.length();i++){
                                  JSONObject o=array.getJSONObject(i);
                                  Fnotice item=new Fnotice(
                                          o.getString("datetime"),
                                          o.getString("title"),
                                          o.getString("content"),
                                          o.getString("sender"),
                                          o.getString("sendermail"),
                                          o.getString("receiver"),
                                          o.getString("type"),
                                          o.getString("dept"),
                                          o.getString("designation")
                                  );
                                  listitem.add(item);

                              }
                              FacultyAdapter adapter=new FacultyAdapter(getApplicationContext(),listitem);
                              rvItemFaculty.setAdapter(adapter);

                          }else{
                              Toast.makeText(
                                      getApplicationContext(),
                                      "error in json",
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
              params.put("receiver",receiver);
              params.put("type", type);
              params.put("dept", dept);
              params.put("designation", designation);
              Log.d(type,"what type?");
              return params;
          }

      };

      RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
  }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
