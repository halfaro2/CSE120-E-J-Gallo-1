package com.example.ejgallodts;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.type.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class SubmissionActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    DTS newDTS;
    Button submitButton;
    ProgressBar loadingSubmission;
    EditText defectName;
    EditText defectImpact;
    EditText defectDescription;
    RadioButton defectIncidentClassFALSE;
    RadioButton defectIncidentClassTRUE;
    EditText defectIncidentDate;
    EditText defectItemNum;
    EditText defectLotNum;
    EditText defectMaterialGrp;
    EditText defectOrderKey;
    EditText defectPONum;
    EditText defectPrimaryLoc;
    RadioButton defectPriority1;
    RadioButton defectPriority2;
    RadioButton defectPriority3;
    EditText defectProdSuggCause;
    EditText defectRecommend;
    EditText defectSecondaryLoc;
    EditText defectSite;
    EditText defectSubDepartment;
    EditText defectSuppSuggCause;
    EditText defectSupplier;
    EditText defectOverdue;
    EditText defectDepartment;
    CalendarView defectDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        submitButton = findViewById(R.id.submitbutton);
        loadingSubmission = findViewById(R.id.loadsubmit);
        defectName = findViewById(R.id.defectName);
        defectImpact = findViewById(R.id.defectImpact);
        defectDescription = findViewById(R.id.defectDescription);
        defectIncidentClassTRUE = findViewById(R.id.class_yes);
        defectIncidentClassFALSE = findViewById(R.id.class_no);
        defectIncidentDate = findViewById(R.id.defectIncidentDate);
        defectItemNum = findViewById(R.id.defectItemNum);
        defectLotNum = findViewById(R.id.defectLotNum);
        defectMaterialGrp = findViewById(R.id.defectMaterialGrp);
        defectOrderKey = findViewById(R.id.defectOrderKey);
        defectPONum = findViewById(R.id.defectPONum);
        defectPrimaryLoc = findViewById(R.id.defectPrimaryLoc);
        defectProdSuggCause = findViewById(R.id.defectProdSuggCause);
        defectPriority1 = findViewById(R.id.priority1);
        defectPriority2 = findViewById(R.id.priority2);
        defectPriority3 = findViewById(R.id.priority3);
        defectRecommend = findViewById(R.id.defectRecommend);
        defectSecondaryLoc = findViewById(R.id.defectSecondaryLoc);
        defectSite = findViewById(R.id.defectSite);
        defectSubDepartment = findViewById(R.id.defectSubDepartment);
        defectSuppSuggCause = findViewById(R.id.defectSuppSuggCause);
        defectSupplier = findViewById(R.id.defectSupplier);
        defectDepartment = findViewById(R.id.defectDepartment);
        defectDate = findViewById(R.id.calendarView);

        loadingSubmission.setVisibility(View.INVISIBLE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadingSubmission.setVisibility(View.VISIBLE);
                boolean incident_class = false;         //defect case
                long priority = 3;                      //default case

                if (defectName.getText().toString().isEmpty() || defectName.length() > 30) {     //name is longer than button length
                    Toast.makeText(SubmissionActivity.this, "Name Exceeds Character Limit", Toast.LENGTH_SHORT).show();
                } else {

                /*
                if (defectPriority != 1 || defectPriority != 2 || defectPriority != 3) {    //invalid priority
                    //error
                }
                */

                    long defectDateEnter = defectDate.getDate();

                    if (defectPriority1.isChecked()) {
                        priority = 1;
                    } else if (defectPriority2.isChecked()) {
                        priority = 2;
                    } else {
                        priority = 3;
                    }

                    if (defectIncidentClassTRUE.isChecked()) {
                        incident_class = true;
                    } else {
                        incident_class = false;
                    }

                    CollectionReference IncidentsRef = db.collection("Incidents");
                    Map<String, Object> newDts = new HashMap<>();
                    newDts.put("defect_impact", defectImpact.getText().toString());
                    newDts.put("defect_name", defectName.getText().toString());
                    newDts.put("department", defectDepartment.getText().toString());
                    newDts.put("description", defectDescription.getText().toString());
                    newDts.put("incident_class", incident_class);
                    newDts.put("incident_date", defectDateEnter);
                    newDts.put("item_num", defectItemNum.getText().toString());
                    newDts.put("lot_num", defectLotNum.getText().toString());
                    newDts.put("material_group", defectMaterialGrp.getText().toString());
                    newDts.put("orderkey", defectOrderKey.getText().toString());
                    //newDts.put("overdue", defectOverdue.getText().toString());
                    newDts.put("po_num", defectPONum.getText().toString());
                    newDts.put("primary_location", defectPrimaryLoc.getText().toString());
                    newDts.put("priority", priority);
                    newDts.put("prod_suggested_cause", defectProdSuggCause.getText().toString());
                    newDts.put("secondary_location", defectSecondaryLoc.getText().toString());
                    newDts.put("site", defectSite.getText().toString());
                    newDts.put("subdepartment", defectSubDepartment.getText().toString());
                    newDts.put("supp_suggested_cause", defectSuppSuggCause.getText().toString());
                    newDts.put("supplier", defectSupplier.getText().toString());
                    IncidentsRef.add(newDts);   //sends DTS to Database


                    loadingSubmission.setVisibility(View.INVISIBLE);

                    Intent i = new Intent(SubmissionActivity.this, HomeActivity.class);
                    startActivity(i);
                }
            }
        });



    }
}
