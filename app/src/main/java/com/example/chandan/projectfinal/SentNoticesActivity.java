package com.example.chandan.projectfinal;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class SentNoticesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_notices);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager vp=(ViewPager)findViewById(R.id.container);
        this.addPages(vp);

        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(vp);
        tabLayout.addOnTabSelectedListener(listener(vp));

    }

    private void addPages(ViewPager pager){
        FragPagerAdapter adapter=new FragPagerAdapter(getSupportFragmentManager());



        adapter.addPaging(new SentStudentFragment());
        adapter.addPaging(new SentFacultyFragment());

        pager.setAdapter(adapter);
    }

    private TabLayout.OnTabSelectedListener listener(final ViewPager pager){
        return new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
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
