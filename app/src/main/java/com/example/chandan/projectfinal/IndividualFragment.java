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

public class IndividualFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.individual_student_notice,null);

//        loadRecyclerViewData();

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        final String receiver=SharedPrefManager.getInstance(this.getActivity()).getUsername();
        final String type=SharedPrefManager.getInstance(this.getActivity()).getUserType();
        final String dept=SharedPrefManager.getInstance(this.getActivity()).getUserDept();
        final String sem=SharedPrefManager.getInstance(this.getActivity()).getUserSem();
        final String section=SharedPrefManager.getInstance(this.getActivity()).getUserSection();


        loadRecyclerViewData(receiver,type,dept,sem,section);
    }

    private void loadRecyclerViewData(final String receiver, final String type, final String dept, final String sem, final String section){





//            progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_INDIVIDUAL_STUDENT_NOTICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                            progressDialog.dismiss();
                        Log.d(response,"responses");
                        try {

                            int j=1;
                            if(j==1){
                                JSONArray array=new JSONArray(response);
                                ArrayList<Item> listitem=new ArrayList<>();
                                for(int i=0;i<array.length();i++){
                                    JSONObject o=array.getJSONObject(i);
                                    Item item=new Item(
                                            o.getString("datetime"),
                                            o.getString("title"),
                                            o.getString("content"),
                                            o.getString("sender"),
                                            o.getString("sendermail"),
                                            o.getString("receiver"),
                                            o.getString("type"),
                                            o.getString("dept"),
                                            o.getString("sem"),
                                            o.getString("section")
                                    );
                                    listitem.add(item);

                                }
                                View v=getView();
                                RecyclerView rv= (RecyclerView) v.findViewById(R.id.rvItemIndiStudentNotice);
                                rv.setLayoutManager(new LinearLayoutManager(getActivity()));


                                ItemAdapter adapter=new ItemAdapter(getActivity(),listitem);
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
                params.put("receiver",receiver);
                params.put("type", "Individual");
                params.put("dept", dept);
                params.put("sem", sem);
                params.put("section", section);
                Log.d(type,"what type?");
                return params;
            }

        };

        RequestHandler.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    @Override
    public String toString() {
        return "Individual";
    }
}
