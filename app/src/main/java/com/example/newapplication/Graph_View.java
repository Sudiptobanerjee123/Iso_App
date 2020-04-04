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

public class Graph_View extends AppCompatActivity {

    LineGraphSeries<DataPoint> series, series1;

    String q1, q2, q3, q4, q5, c1, c2, c3, c4, c5;

    Button btn_sign_out;

    GoogleSignInClient mGoogleSignInClient;

    Double q_1, q_2, q_3, q_4, q_5, c_1, c_2, c_3, c_4, c_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph__view);

        btn_sign_out = (Button)findViewById(R.id.Sign_out_button);

        q1 = Objects.requireNonNull(getIntent().getExtras()).getString("q1");
        q2 = getIntent().getExtras().getString("q2");
        q3 = getIntent().getExtras().getString("q3");
        q4 = getIntent().getExtras().getString("q4");
        q5 = getIntent().getExtras().getString("q5");

        c1 = getIntent().getExtras().getString("C1");
        c2 = getIntent().getExtras().getString("C2");
        c3 = getIntent().getExtras().getString("C3");
        c4 = getIntent().getExtras().getString("C4");
        c5 = getIntent().getExtras().getString("C5");

        q_1 = Double.parseDouble(q1);
        q_2 = Double.parseDouble(q2);
        q_3 = Double.parseDouble(q3);
        q_4 = Double.parseDouble(q4);
        q_5 = Double.parseDouble(q5);

        c_1 = Double.parseDouble(c1);
        c_2 = Double.parseDouble(c2);
        c_3 = Double.parseDouble(c3);
        c_4 = Double.parseDouble(c4);
        c_5 = Double.parseDouble(c5);


        double y1, y2, y3, y4, y5, z1,z2,z3,z4,z5, g1,g2,g3,g4,g5, h1,h2,h3,h4,h5;
        GraphView graph_view = (GraphView) findViewById(R.id.graph_1);
        GraphView graph_view1 = (GraphView) findViewById(R.id.graph_2);
        GraphView graph_view2 = (GraphView) findViewById(R.id.graph_3);
        GraphView graph_view3 = (GraphView) findViewById(R.id.graph_4);

        y1 = c_1 / q_1;
        y2 = c_2 / q_2;
        y3 = c_3 / q_3;
        y4 = c_4 / q_4;
        y5 = c_5 / q_5;

        z1 = 1/y1;
        z2 = 1/y2;
        z3 = 1/y3;
        z4 = 1/y4;
        z5 = 1/y5;

        g1 = 1/q_1;
        g2 = 1/q_2;
        g3 = 1/q_3;
        g4 = 1/q_4;
        g5 = 1/q_5;

        h1 = 1/c_1;
        h2 = 1/c_2;
        h3 = 1/c_3;
        h4 = 1/c_4;
        h5 = 1/c_5;
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(c_1, y1),
                new DataPoint(c_2, y2),
                new DataPoint(c_3, y3),
                new DataPoint(c_4, y4),
                new DataPoint(c_5, y5)
        });
        GridLabelRenderer gridLabel = graph_view.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Ce");
        gridLabel.setVerticalAxisTitle("Ce/Qe");
        LineGraphSeries<DataPoint> series1 = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(h5, g5),
                new DataPoint(h4, g4),
                new DataPoint(h3, g3),
                new DataPoint(h2, g2),
                new DataPoint(h1, g1)
        });
        GridLabelRenderer gridLabel1 = graph_view1.getGridLabelRenderer();
        gridLabel1.setHorizontalAxisTitle("1/Ce");
        gridLabel1.setVerticalAxisTitle("1/qe");
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(z5, q_5),
                new DataPoint(z4, q_4),
                new DataPoint(z3, q_3),
                new DataPoint(z2, q_2),
                new DataPoint(z1, q_1)
        });
        GridLabelRenderer gridLabel2 = graph_view2.getGridLabelRenderer();
        gridLabel2.setHorizontalAxisTitle("qe/Ce");
        gridLabel2.setVerticalAxisTitle("qe");
        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(q_1, z1),
                new DataPoint(q_2, z2),
                new DataPoint(q_3, z3),
                new DataPoint(q_4, z4),
                new DataPoint(q_5, z5)
        });
        GridLabelRenderer gridLabel3 = graph_view3.getGridLabelRenderer();
        gridLabel3.setHorizontalAxisTitle("qe");
        gridLabel3.setVerticalAxisTitle("qe/Ce");
        graph_view.addSeries(series);
        graph_view1.addSeries(series1);
        graph_view2.addSeries(series2);
        graph_view3.addSeries(series3);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(Graph_View.this);
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
                        Toast.makeText(Graph_View.this,"Successfully signed out",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Graph_View.this, Login.class));
                        finish();
                    }
                });
    }

}











