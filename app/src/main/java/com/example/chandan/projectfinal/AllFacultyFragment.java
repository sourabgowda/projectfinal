package com.example.chandan.projectfinal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * Created by Chandan on 19-05-2017.
 */

public class AllFacultyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.general_faculty_notice,null);

//        loadRecyclerViewData();

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        final String receiver=SharedPrefManager.getInstance(this.getActivity()).getUsername();
        final String type=SharedPrefManager.getInstance(this.getActivity()).getUserType();
        final String dept=SharedPrefManager.getInstance(this.getActivity()).getUserDept();
        final String designation=SharedPrefManager.getInstance(this.getActivity()).getUserDesignation();



        loadRecyclerViewData(receiver,type,dept,designation);
    }

    private void loadRecyclerViewData(final String receiver, final String type, final String dept, final String designation){





//            progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_FACULTY_NOTICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                            progressDialog.dismiss();
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
                                View v=getView();
                                RecyclerView rv= (RecyclerView) v.findViewById(R.id.rvItemAllFacultyNotice);
                                rv.setLayoutManager(new LinearLayoutManager(getActivity()));


                                FacultyAdapter adapter=new FacultyAdapter(getActivity(),listitem);
                                rv.setAdapter(adapter);
                            }else{
                                Toast.makeText(
                                        getActivity(),
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
//                            progressDialog.dismiss();

                        Toast.makeText(getActivity(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("receiver","All");
                params.put("type", "All");
                params.put("dept", dept);
                params.put("designation", designation);
                Log.d(type,"what type?");
                return params;
            }

        };

        RequestHandler.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    @Override
    public String toString() {
        return "General";
    }
}
