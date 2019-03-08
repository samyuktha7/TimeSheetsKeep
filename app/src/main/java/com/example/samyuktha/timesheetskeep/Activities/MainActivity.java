package com.example.samyuktha.timesheetskeep.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.samyuktha.timesheetskeep.Activities.ProfileActivity;
import com.example.samyuktha.timesheetskeep.Fragments.Sheets_Fragment;
import com.example.samyuktha.timesheetskeep.Fragments.Today_Fragment;
import com.example.samyuktha.timesheetskeep.Fragments.Week_Fragment;
import com.example.samyuktha.timesheetskeep.R;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView mBottomNavigation;
    Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("XXX", "onCreate() ");
        setContentView(R.layout.activity_main);

        mBottomNavigation = (BottomNavigationView)findViewById(R.id.navigationBar);

        final Today_Fragment todayFragment = new Today_Fragment();
        final Week_Fragment weekFragment = new Week_Fragment();
        final Sheets_Fragment sheetsFragment = new Sheets_Fragment();
        addOrReplaceFragment(todayFragment);

        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.todayTAB:
                        addOrReplaceFragment(todayFragment);
                        break;
                    case R.id.weekTAB:
                        addOrReplaceFragment(weekFragment);
                        break;
                    case R.id.sheetsTAB:
                        addOrReplaceFragment(sheetsFragment);
                        break;
                }
                return true;
            }
        });
    }

    public void addOrReplaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(mFragment == null) {
            fragmentTransaction.add(R.id.frameLayout , fragment , "todaysFragment");
        } else {
           fragmentTransaction.replace(R.id.frameLayout , fragment);
        }
        fragmentTransaction.commit();
        mFragment = fragment;
        return;
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater inflater = getMenuInflater();
       inflater.inflate(R.menu.profile , menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.profilebutton) {
            Intent profileNavigationIntent = new Intent(this , ProfileActivity.class);
            startActivity(profileNavigationIntent);
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
