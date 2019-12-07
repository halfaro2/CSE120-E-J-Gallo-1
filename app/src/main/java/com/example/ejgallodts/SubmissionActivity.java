package com.example.ejgallodts;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.HashMap;
import java.util.Map;

public class SubmissionActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    DTS newDTS;
    Button submitButton;
    ProgressBar loadingSubmission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        submitButton = findViewById(R.id.submitbutton);
        loadingSubmission = findViewById(R.id.loadSub);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //Gurpinder: When you make the submission page, make a new DTS variable call it newDTS
        //which takes all your fields and turns it into a DTS object.
        //on the submission button's onClickListener, you can use this code below to add something
        // to our database:

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadingSubmission.setVisibility(View.VISIBLE);

                //checking for correct fields...
                //if (editDefect_name.isEmpty()) {
                    //error
                //}
                //etc...

                //newDTS.setDefect_name(editDefect_name.getText().toString());
                //newDTS.setDefect_name(editIncident_date.getText().toString());
                //etc...

                CollectionReference IncidentsRef = db.collection("Incidents");
                Map<String, Object> newDts = new HashMap<>();
                newDts.put("defect_impact", newDTS.getDefect_impact());
                newDts.put("defect_name", newDTS.getDefect_name());
                newDts.put("department", newDTS.getDepartment());
                newDts.put("description", newDTS.getDescription());
                newDts.put("incident_class", newDTS.getIncident_class());
                newDts.put("incident_date", newDTS.getIncident_date());
                newDts.put("item_num", newDTS.getItem_num());
                newDts.put("lot_num", newDTS.getLot_num());
                newDts.put("material_group", newDTS.getMaterial_group());
                newDts.put("orderkey", newDTS.getOrderkey());
                newDts.put("overdue", newDTS.getOverdue());
                newDts.put("po_num", newDTS.getPo_num());
                newDts.put("primary_location", newDTS.getPrimary_location());
                newDts.put("priority", newDTS.getPriority());
                newDts.put("prod_suggested_cause", newDTS.getProd_suggested_cause());
                newDts.put("secondary_location", newDTS.getSecondary_location());
                newDts.put("site", newDTS.getSite());
                newDts.put("subdepartment", newDTS.getSubdepartment());
                newDts.put("supp_suggested_cause", newDTS.getSupp_suggested_cause());
                newDts.put("supplier", newDTS.getSupplier());
                IncidentsRef.add(newDts);   //sends DTS to Database

                loadingSubmission.setVisibility(View.INVISIBLE);

            }
        });



    }
}
