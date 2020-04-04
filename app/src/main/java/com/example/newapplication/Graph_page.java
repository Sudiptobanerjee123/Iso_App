package com.example.newapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Objects;

public class Graph_page extends AppCompatActivity {

    String lnq1, lnq2, lnq3, lnq4, lnq5, lnc1, lnc2, lnc3, lnc4, lnc5;

    Button btn_sign_out;

    GoogleSignInClient mGoogleSignInClient;
    Double x1,x2,x3,x4,x5,y1,y2,y3,y4,y5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_page);

        btn_sign_out = (Button)findViewById(R.id.Sign_out_button_f);


        lnq1 = Objects.requireNonNull(getIntent().getExtras()).getString("Logq1");
        lnq2 = getIntent().getExtras().getString("Logq2");
        lnq3 = getIntent().getExtras().getString("Logq3");
        lnq4 = getIntent().getExtras().getString("Logq4");
        lnq5 = getIntent().getExtras().getString("Logq5");

        x1 = Double.parseDouble(lnq1);
        x2 = Double.parseDouble(lnq2);
        x3 = Double.parseDouble(lnq3);
        x4 = Double.parseDouble(lnq4);
        x5 = Double.parseDouble(lnq5);



        lnc1 = getIntent().getExtras().getString("Logc1");
        lnc2 = getIntent().getExtras().getString("Logc2");
        lnc3 = getIntent().getExtras().getString("Logc3");
        lnc4 = getIntent().getExtras().getString("Logc4");
        lnc5 = getIntent().getExtras().getString("Logc5");

        y1 = Double.parseDouble(lnc1);
        y2 = Double.parseDouble(lnc2);
        y3 = Double.parseDouble(lnc3);
        y4 = Double.parseDouble(lnc4);
        y5 = Double.parseDouble(lnc5);

        GraphView graph_view = (GraphView) findViewById(R.id.graph_1_f);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(x1, y1),
                new DataPoint(x2, y2),
                new DataPoint(x3, y3),
                new DataPoint(x4, y4),
                new DataPoint(x5, y5)
        });
        GridLabelRenderer gridLabel = graph_view.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Logqe");
        gridLabel.setVerticalAxisTitle("logCe");
        graph_view.addSeries(series);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(Graph_page.this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();


        }

        btn_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sign = btn_sign_out.getText().toString().trim();

                if (btn_sign_out.getText().equals(sign))
                {
                    signOut();
                }
            }
        });
    }


    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Graph_page.this,"Successfully signed out",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Graph_page.this, Login.class));
                        finish();
                    }
                });


    }
}