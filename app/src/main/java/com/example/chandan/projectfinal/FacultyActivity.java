package com.example.chandan.projectfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class FacultyActivity extends AppCompatActivity {
    Button buttonPeople,buttonNotice,buttonInbox,buttonProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        buttonPeople=(Button)findViewById(R.id.People);
        buttonPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SelectAddActivity.class));
            }
        });

        buttonNotice=(Button)findViewById(R.id.Notifiy);
        buttonNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CreateNoticeActivity.class));
            }
        });

        buttonInbox=(Button)findViewById(R.id.Inbox);
        buttonInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FacultyNotices.class));
            }
        });
        buttonProfile=(Button)findViewById(R.id.profile);
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), FacultyProfileActivity.class));

                startActivity(new Intent(getApplicationContext(), SentNoticesActivity.class));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
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
            case R.id.changePassword:
                startActivity(new Intent(this, UpdatePasswordActivity.class));
                break;
            case R.id.profile:
                startActivity(new Intent(this, FacultyProfileActivity.class));
                break;


        }
        return true;
    }
}
