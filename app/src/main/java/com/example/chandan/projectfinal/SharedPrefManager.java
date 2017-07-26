package com.example.chandan.projectfinal;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Belal on 26/11/16.
 */

public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "mysharedpref12";
    private static final String KEY_USERNAME = "name";
    private static final String KEY_USER_EMAIL = "email";
    private static final String KEY_USER_MOBILENO = "mobileno";
    private static final String KEY_USER_USN = "usn";
    private static final String KEY_USER_DEPT= "dept";
    private static final String KEY_USER_DESIGNATION= "designation";
    private static final String KEY_USER_SEM= "sem";
    private static final String KEY_USER_SECTION= "section";
    private static final String KEY_USER_TYPE="individual";



    private SharedPrefManager(Context context) {
        mCtx = context;

    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }
//Principal Login-------------------------------------------------------------
    public boolean userLogin(String mobileno, String name, String email){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_USER_MOBILENO, mobileno);
        editor.putString(KEY_USER_EMAIL, email);
        editor.putString(KEY_USERNAME, name);
        editor.putString(KEY_USER_DESIGNATION,"principal");
        editor.putString(KEY_USER_TYPE,"individual");

        editor.apply();

        return true;
    }

//Faculty Login--------------------------------------------------------------------------------------------
    public boolean facultyLogin(String mobileno, String name, String email, String dept, String designation){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.putString(KEY_USER_MOBILENO, mobileno);
        editor.putString(KEY_USER_EMAIL, email);
        editor.putString(KEY_USERNAME, name);
        editor.putString(KEY_USER_DESIGNATION,designation);
        editor.putString(KEY_USER_DEPT,dept);
        editor.putString(KEY_USER_TYPE,"individual");


        editor.apply();

        return true;
    }

//Student login-----------------------------------------------------------------------------------------------
    public boolean studentLogin(String mobileno, String name, String email, String dept, String sem, String usn, String section){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.putString(KEY_USER_MOBILENO, mobileno);
        editor.putString(KEY_USER_EMAIL, email);
        editor.putString(KEY_USERNAME, name);
        editor.putString(KEY_USER_DEPT,dept);
        editor.putString(KEY_USER_SEM,sem);
        editor.putString(KEY_USER_USN,usn);
        editor.putString(KEY_USER_SECTION,section);
        editor.putString(KEY_USER_DESIGNATION,"student");
        editor.putString(KEY_USER_TYPE,"individual");


        editor.apply();

        return true;
    }

//Check if user is already logged in-------------------------------------------------------------------------------
    public int isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USER_USN, null) != null){
            return 1;
        }
        else if(sharedPreferences.getString(KEY_USER_DEPT, null) != null){
            return 2;
        }
        else if(sharedPreferences.getString(KEY_USERNAME, null) != null){
            return 3;
        }
        return 0;
    }

 //Logging out of the profile---------------------------------------------------------------------------------------
    public boolean logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true; 
    }


    public String getUsername(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null);
    }

    public String getUserEmail(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_EMAIL, null);
    }

    public String getUserUsn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_USN, null);
    }
    public String getUserPhone(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_MOBILENO, null);
    }
    public String getUserDept(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_DEPT, null);
    }

    public String getUserDesignation(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_DESIGNATION, null);
    }

    public String getUserSem(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_SEM, null);
    }
    public String getUserSection(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_SECTION, null);
    }
    public String getUserType(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_TYPE, null);
    }
    public void setUserSem(String sem){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(KEY_USER_SEM,sem);
        editor.apply();
    }
    public void setUserSection(String section){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(KEY_USER_SECTION,section);
        editor.apply();
    }

    public void setUserPhone(String mobile){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(KEY_USER_MOBILENO,mobile);
        editor.apply();

    }

}
