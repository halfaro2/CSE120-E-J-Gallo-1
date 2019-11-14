package com.example.ejgallodts;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.ejgallodts.ui.main.SectionsPagerAdapter;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        TabItem mainTab = findViewById(R.id.mainTab);
        TabItem priorityTab1 = findViewById(R.id.priorityTab1);
        TabItem priorityTab2 = findViewById(R.id.priorityTab2);
        TabItem priorityTab3 = findViewById(R.id.priorityTab3);
        TabItem overdueTab = findViewById(R.id.overdueTab);

        //tabs.addView(mainTab, 0); tabs.addView(priorityTab1, 1); tabs.addView(priorityTab2, 2); tabs.addView(priorityTab3, 3); tabs.addView(overdueTab, 4);

    }
}