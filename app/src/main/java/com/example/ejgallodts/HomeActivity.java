package com.example.ejgallodts;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.widget.ScrollView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class HomeActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    DTS dts1 = new DTS(); DTS dts2 = new DTS(); DTS dts3 = new DTS(); DTS dts4 = new DTS(); DTS dts5 = new DTS(); DTS dts6 = new DTS(); DTS dts7 = new DTS(); DTS dts8 = new DTS(); DTS dts9 = new DTS(); DTS dts10 = new DTS();
    Button DTS_1, DTS_2, DTS_3, DTS_4, DTS_5, DTS_6, DTS_7, DTS_8, DTS_9, DTS_10;
    ScrollView scroll;
    DTS dtsarray[] = new DTS[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        scroll = findViewById(R.id.scrollView);

        final Button buttonArray[] = new Button[5];

        DTS_1 = findViewById(R.id.DTS_1);
        DTS_2 = findViewById(R.id.DTS_2);
        DTS_3 = findViewById(R.id.DTS_3);
        DTS_4 = findViewById(R.id.DTS_4);
        DTS_5 = findViewById(R.id.DTS_5);
       // DTS_6 = findViewById(R.id.DTS_6);
       // DTS_7 = findViewById(R.id.DTS_7);
       // DTS_8 = findViewById(R.id.DTS_8);
       // DTS_9 = findViewById(R.id.DTS_9);
       // DTS_10 = findViewById(R.id.DTS_10);

        buttonArray[0] = DTS_1;
        buttonArray[1] = DTS_1;
        buttonArray[2] = DTS_1;
        buttonArray[3] = DTS_1;
        buttonArray[4] = DTS_1;
        dtsarray[0] = dts1;
        dtsarray[1] = dts1;
        dtsarray[2] = dts1;
        dtsarray[3] = dts1;
        dtsarray[4] = dts1;
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

                                        dts1.setDefect_name(document.getString("defect_name"));
                                        dts1.setPriority((long) document.get("priority"));
                                        String dts1text = "Name: " + dts1.getDefect_name() + "| Priority: " + dts1.getPriority();
                                        DTS_1.setText(dts1text);
                                        if (dts1.getPriority() == 1) {
                                            DTS_1.setBackgroundColor(0xFFB83838);
                                        }

                                        dts2.setDefect_name(document.getString("defect_name"));
                                        dts2.setPriority((long) document.get("priority"));
                                        String dts2text = "Name: " + dts2.getDefect_name() + "| Priority: " + dts2.getPriority();
                                        DTS_2.setText(dts2text);
                                        if (dts1.getPriority() == 1) {
                                            DTS_2.setBackgroundColor(0xFFB83838);
                                        }

                                        dts3.setDefect_name(document.getString("defect_name"));
                                        dts3.setPriority((long) document.get("priority"));
                                        String dts3text = "Name: " + dts3.getDefect_name() + "| Priority: " + dts3.getPriority();
                                        DTS_3.setText(dts3text);
                                        if (dts1.getPriority() == 1) {
                                            DTS_3.setBackgroundColor(0xFFB83838);
                                        }

                                        dts4.setDefect_name(document.getString("defect_name"));
                                        dts4.setPriority((long) document.get("priority"));
                                        String dts4text = "Name: " + dts4.getDefect_name() + "| Priority: " + dts4.getPriority();
                                        DTS_4.setText(dts4text);
                                        if (dts1.getPriority() == 1) {
                                            DTS_4.setBackgroundColor(0xFFB83838);
                                        }

                                        dts5.setDefect_name(document.getString("defect_name"));
                                        dts5.setPriority((long) document.get("priority"));
                                        String dts5text = "Name: " + dts5.getDefect_name() + "| Priority: " + dts5.getPriority();
                                        DTS_5.setText(dts5text);
                                        if (dts1.getPriority() == 1) {
                                            DTS_5.setBackgroundColor(0xFFB83838);
                                        }

                                        //Toast.makeText(HomeActivity.this, dts1.getDefect_name(), Toast.LENGTH_SHORT);
                                    }
                                } else {
                                    Log.d("TAG", "Error getting documents: ", task.getException());
                                }
                            }
                        });



    }



}