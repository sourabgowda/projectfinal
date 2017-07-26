package com.example.chandan.projectfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class CreateNoticeActivity extends AppCompatActivity {
    Button buttonFaculty,buttonStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notice);
        buttonStudent=(Button)findViewById(R.id.student);
        buttonFaculty=(Button)findViewById(R.id.faculty);

        buttonFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CreateFacultyNoticeActivity.class));
            }
        });

        buttonStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CreateStudentNoticeActivity.class));
            }
        });
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
