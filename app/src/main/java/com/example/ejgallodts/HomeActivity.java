package com.example.ejgallodts;

import android.nfc.Tag;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ejgallodts.DTS;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejgallodts.ui.main.SectionsPagerAdapter;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Collection;

public class HomeActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    DTS dts1 = new DTS(); DTS dts2 = new DTS(); DTS dts3 = new DTS(); DTS dts4 = new DTS(); DTS dts5 = new DTS(); DTS dts6 = new DTS(); DTS dts7 = new DTS(); DTS dts8 = new DTS(); DTS dts9 = new DTS(); DTS dts10 = new DTS();
    Button DTS_1, DTS_2, DTS_3, DTS_4, DTS_5, DTS_6, DTS_7, DTS_8, DTS_9, DTS_10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        DTS_1 = findViewById(R.id.DTS_1);
        DTS_2 = findViewById(R.id.DTS_2);
        //DTS_3 = findViewById(R.id.DTS_3);
       // DTS_4 = findViewById(R.id.DTS_4);
       // DTS_5 = findViewById(R.id.DTS_5);
       // DTS_6 = findViewById(R.id.DTS_6);
       // DTS_7 = findViewById(R.id.DTS_7);
       // DTS_8 = findViewById(R.id.DTS_8);
       // DTS_9 = findViewById(R.id.DTS_9);
       // DTS_10 = findViewById(R.id.DTS_10);

        /*
        DocumentReference docRef = db.collection("Incidents").document();
        db.collection("Incidents")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error receiving documents.", task.getException());
                        }
                    }
                });

         */

        TabItem mainTab = findViewById(R.id.mainTab);
        TabItem priorityTab1 = findViewById(R.id.priorityTab1);
        TabItem priorityTab2 = findViewById(R.id.priorityTab2);
        TabItem priorityTab3 = findViewById(R.id.priorityTab3);
        TabItem overdueTab = findViewById(R.id.overdueTab);

        //tabs.addView(mainTab, 0); tabs.addView(priorityTab1, 1); tabs.addView(priorityTab2, 2); tabs.addView(priorityTab3, 3); tabs.addView(overdueTab, 4);

        CollectionReference dtsRef = db.collection("Incidents");
        //Query query = dtsRef.whereEqualTo("priority", 1); // Simple Query Example (no execution/results)


        //Priority 1 TAB ///////////////////////////////////////////////
                db.collection("Incidents")
                        .whereEqualTo("priority", 1)
                        .orderBy("incident_date")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        //Log.d(TAG, document.getId() + " => " + document.getData());
                                        dts1.setIncident_date(document.getString("incident_date"));
                                        dts1.setDefect_name(document.getString("defect_name"));
                                        dts1.setPriority((long) document.get("priority"));
                                        DTS_1.setText(dts1.getIncident_date() + " " + dts1.getDefect_name() + " " + dts1.getPriority());
                                        dts2.setIncident_date(document.getString("incident_date"));
                                        dts2.setDefect_name(document.getString("defect_name"));
                                        dts2.setPriority((long) document.get("priority"));
                                        DTS_2.setText(dts2.getIncident_date() + " " + dts2.getDefect_name() + " " + dts2.getPriority());
                                        //Toast.makeText(HomeActivity.this, dts1.getDefect_name(), Toast.LENGTH_SHORT);
                                    }
                                } else {
                                    Log.d("TAG", "Error getting documents: ", task.getException());
                                }
                            }
                        });


    }



}