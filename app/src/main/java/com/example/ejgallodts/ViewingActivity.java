package com.example.ejgallodts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ViewingActivity extends AppCompatActivity {

    final static String title = "New Defect Broadcasted";
    final static String CHANNEL_ID = "pushing";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.nextpage)
            .setContentTitle(title)
            .setContentText("Admin(s) Have Pushed a new DTS")
            .setStyle(new NotificationCompat.BigTextStyle().bigText("Admin(s) Have Pushed a new DTS"))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

    Notification built = new Notification();

    TextView viewName, viewImpact, viewDepartment, viewDescription, viewIncidentClass, viewIncidentDate, viewOverdue, viewItemNum, viewLotNum, viewMaterial, viewOrderkey, viewPOnum, viewPrimLoc, viewPriority, viewProdCause, viewSuppCause, viewSecLoc, viewSite, viewSubDepartment, viewSupplier, viewRecommendation;
    Uri imageUploaded;
    ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewing);
        Button push = findViewById(R.id.pushButton);
        viewName = findViewById(R.id.textView18);
        viewIncidentDate = findViewById(R.id.textView19);
        viewImpact = findViewById(R.id.textView21);
        viewDepartment = findViewById(R.id.textView40);
        viewPriority = findViewById(R.id.textView22);
        viewDescription = findViewById(R.id.textView23);
        viewIncidentClass = findViewById(R.id.textView24);
        viewItemNum = findViewById(R.id.textView25);
        viewLotNum = findViewById(R.id.textView26);
        viewMaterial = findViewById(R.id.textView27);
        viewOrderkey = findViewById(R.id.textView28);
        viewOverdue = findViewById(R.id.textView29);
        viewPOnum = findViewById(R.id.textView30);
        viewPrimLoc = findViewById(R.id.textView31);
        viewSecLoc = findViewById(R.id.textView32);
        viewSite = findViewById(R.id.textView33);
        viewSubDepartment = findViewById(R.id.textView34);
        viewSuppCause = findViewById(R.id.textView35);
        viewSupplier = findViewById(R.id.textView36);
        viewProdCause = findViewById(R.id.textView37);
        viewRecommendation = findViewById(R.id.textView38);
        imgView = findViewById(R.id.imageView2);

        Intent intent = getIntent();
        long id = intent.getLongExtra("id", -1);
        if (id < 0) {
            //trap exception...
        }

        boolean isAdmin = intent.getBooleanExtra("isAdmin", false);
        if (isAdmin) {
            push.setVisibility(View.VISIBLE);
        }

        db.collection("Incidents")
                .whereEqualTo("id", id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String tempDate, tempPriority, tempClass, tempLotNum, tempOrderkey, tempOverdue, tempPOnum;

                                long date = document.getLong("incident_date");
                                tempDate = "" + date;

                                long priority = document.getLong("priority");
                                if (priority == 1) {
                                    tempPriority = "Major";
                                } else if (priority == 2) {
                                    tempPriority = "Medium";
                                } else {
                                    tempPriority = "Minor";
                                }

                                imageUploaded = Uri.parse(document.get("url").toString());

                                viewName.setText((String) document.get("defect_name"));
                                viewIncidentDate.setText(tempDate);
                                viewImpact.setText((String) document.get("defect_impact"));
                                viewDepartment.setText((String) document.get("department"));
                                viewPriority.setText(tempPriority);
                                viewDescription.setText((String) document.get("description"));
                                viewIncidentClass.setText((String) document.getBoolean("incident_class").toString());
                                viewItemNum.setText((String) document.get("item_num"));
                                viewLotNum.setText((String) document.getLong("lot_num").toString());
                                viewMaterial.setText((String) document.get("material_group"));
                                viewOrderkey.setText((String) document.getLong("orderkey").toString());
                                viewOverdue.setText((String) document.getBoolean("overdue").toString());
                                viewPOnum.setText((String) document.getString("po_num").toString());
                                viewPrimLoc.setText((String) document.get("primary_location"));
                                viewSecLoc.setText((String) document.get("secondary_location"));
                                viewSite.setText((String) document.get("site"));
                                viewSubDepartment.setText((String) document.get("subdepartment"));
                                viewSuppCause.setText((String) document.get("supp_suggested_cause"));
                                viewSupplier.setText((String) document.get("supplier"));
                                viewProdCause.setText((String) document.get("prod_suggested_cause"));
                                viewRecommendation.setText((String) document.get("recommendation"));
                                if (imageUploaded.toString() != "") {
                                    imgView.setImageURI(imageUploaded);
                                }

                            }
                        } else {
                            //Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });


        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.build();
                    mNotificationManager.notify(0, builder.build());
                }
        });

    }
}
