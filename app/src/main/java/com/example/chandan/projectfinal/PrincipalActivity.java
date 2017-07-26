package com.example.chandan.projectfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class PrincipalActivity extends AppCompatActivity {
    Button buttonAddPeople,buttonNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        buttonAddPeople=(Button)findViewById(R.id.People);
        buttonAddPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CreateNoticeActivity.class));
            }
        });

        buttonNotify=(Button)findViewById(R.id.Notify);
        buttonNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), SentNoticesActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main2, menu);
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
            case R.id.profile:
                startActivity(new Intent(getApplicationContext(), PrincipalProfileActivity.class));

                break;

            case R.id.AddPeople:
                startActivity(new Intent(getApplicationContext(), SelectAddActivity.class));

                break;
            case R.id.changePassword:
                startActivity(new Intent(this, UpdatePasswordActivity.class));
                break;


        }
        return true;
    }

}
