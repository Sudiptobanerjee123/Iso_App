package com.example.newapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class test_freundlich_results extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    TextView q1,q2,q3,q4,q5,R1,R2,R3,R4,R5,C1,C2,C3,C4,C5,Kl1,Kl2,Kl3,Kl4,Kl5,qm1,qm2,qm3,qm4,qm5,lq1,lq2,lq3,lq4,lq5,lc1,lc2,lc3,lc4,lc5;;
    String lnq1,lnq2,lnq3,lnq4,lnq5,lnc1,lnc2,lnc3,lnc4,lnc5;
    TextView viewq1,viewq2,viewq3,viewq4,viewq5;
    TextView viewR1,viewR2,viewR3,viewR4,viewR5;
    TextView viewC1,viewC2,viewC3,viewC4,viewC5;
    TextView viewKl1,viewKl2,viewKl3,viewKl4,viewKl5;
    TextView viewqm1,viewqm2,viewqm3,viewqm4,viewqm5;
    TextView viewlq1,viewlq2,viewlq3,viewlq4,viewlq5;
    TextView viewl_c1,viewl_c2,viewl_c3,viewl_c4,viewl_c5;

    Button btn_nextgraph,btn_back_time_varying, btn_save_database;


    DrawerLayout drawerLayout;
    Toolbar toolbar;

    NavigationView navigationView;

    ActionBarDrawerToggle toggle;
    GoogleSignInClient mGoogleSignInClient;

    String q_1,q_2,q_3,q_4,q_5,R_1,R_2,R_3,R_4,R_5,C_1,C_2,C_3,C_4,C_5,Kl_1,Kl_2,Kl_3,Kl_4,Kl_5,qm_1,qm_2,qm_3,qm_4,qm_5;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_freundlich_results);

        btn_nextgraph = (Button)findViewById(R.id.next_graph_1_f);
        btn_back_time_varying = (Button)findViewById(R.id.back_time_1_f);
        btn_save_database = (Button)findViewById(R.id.save_results_f);



        lq1 = (TextView)findViewById(R.id.lnq1_new_text_f);
        lq2 = (TextView)findViewById(R.id.lnq2_new_text_f);
        lq3 = (TextView)findViewById(R.id.lnq4_new_text_f);
        lq4 = (TextView)findViewById(R.id.lnq4_new_text_f);
        lq5 = (TextView)findViewById(R.id.lnq5_new_text_f);

        viewlq1 = (TextView)findViewById(R.id.new_lnq1_text_f);
        viewlq2 = (TextView)findViewById(R.id.new_lnq2_text_f);
        viewlq3 = (TextView)findViewById(R.id.new_lnq3_text_f);
        viewlq4 = (TextView)findViewById(R.id.new_lnq4_text_f);
        viewlq5 = (TextView)findViewById(R.id.new_lnq5_text_f);


        lc1 = (TextView)findViewById(R.id.lnc1_new_text_f);
        lc2 = (TextView)findViewById(R.id.lnc2_new_text_f);
        lc3 = (TextView)findViewById(R.id.lnc3_new_text_f);
        lc4 = (TextView)findViewById(R.id.lnc4_new_text_f);
        lc5 = (TextView)findViewById(R.id.lnc5_new_text_f);

        viewl_c1 = (TextView)findViewById(R.id.new_lnc1_text_f);
        viewl_c2 = (TextView)findViewById(R.id.new_lnc2_text_f);
        viewl_c3 = (TextView)findViewById(R.id.new_lnc3_text_f);
        viewl_c4 = (TextView)findViewById(R.id.new_lnc4_text_f);
        viewl_c5 = (TextView)findViewById(R.id.new_lnc5_text_f);



        q1 = (TextView)findViewById(R.id.q1_new_text_f);
        q2 = (TextView)findViewById(R.id.q2_new_text_f);
        q3 = (TextView)findViewById(R.id.q3_new_text_f);
        q4 = (TextView)findViewById(R.id.q4_new_text_f);
        q5 = (TextView)findViewById(R.id.q5_new_text_f);

        viewq1 = (TextView)findViewById(R.id.new_q1_text_f);
        viewq2 = (TextView)findViewById(R.id.new_q2_text_f);
        viewq3 = (TextView)findViewById(R.id.new_q3_text_f);
        viewq4 = (TextView)findViewById(R.id.new_q4_text_f);
        viewq5 = (TextView)findViewById(R.id.new_q5_text_f);



        R1 = (TextView)findViewById(R.id.R1_new_text_f);
        R2 = (TextView)findViewById(R.id.R2_new_text_f);
        R3 = (TextView)findViewById(R.id.R3_new_text_f);
        R4 = (TextView)findViewById(R.id.R4_new_text_f);
        R5 = (TextView)findViewById(R.id.R5_new_text_f);

        viewR1 = (TextView)findViewById(R.id.new_R1_text_f);
        viewR2 = (TextView)findViewById(R.id.new_R2_text_f);
        viewR3 = (TextView)findViewById(R.id.new_R3_text_f);
        viewR4 = (TextView)findViewById(R.id.new_R4_text_f);
        viewR5 = (TextView)findViewById(R.id.new_R5_text_f);



        C1 = (TextView)findViewById(R.id.C1_new_text_f);
        C2 = (TextView)findViewById(R.id.C2_new_text_f);
        C3 = (TextView)findViewById(R.id.C3_new_text_f);
        C4 = (TextView)findViewById(R.id.C4_new_text_f);
        C5 = (TextView)findViewById(R.id.C5_new_text_f);

        viewC1 = (TextView)findViewById(R.id.new_C1_text_f);
        viewC2 = (TextView)findViewById(R.id.new_C2_text_f);
        viewC3 = (TextView)findViewById(R.id.new_C3_text_f);
        viewC4 = (TextView)findViewById(R.id.new_C4_text_f);
        viewC5 = (TextView)findViewById(R.id.new_C5_text_f);



        Kl1 = (TextView)findViewById(R.id.Kl1_new_text_f);
        Kl2 = (TextView)findViewById(R.id.Kl2_new_text_f);
        Kl3 = (TextView)findViewById(R.id.Kl3_new_text_f);
        Kl4 = (TextView)findViewById(R.id.Kl4_new_text_f);
        Kl5 = (TextView)findViewById(R.id.Kl5_new_text_f);

        viewKl1 = (TextView)findViewById(R.id.new_Kl1_text_f);
        viewKl2 = (TextView)findViewById(R.id.new_Kl2_text_f);
        viewKl3 = (TextView)findViewById(R.id.new_Kl3_text_f);
        viewKl4 = (TextView)findViewById(R.id.new_Kl4_text_f);
        viewKl5 = (TextView)findViewById(R.id.new_Kl5_text_f);



        qm1 = (TextView)findViewById(R.id.qm1_new_text_f);
        qm2 = (TextView)findViewById(R.id.qm2_new_text_f);
        qm3 = (TextView)findViewById(R.id.qm3_new_text_f);
        qm4 = (TextView)findViewById(R.id.qm4_new_text_f);
        qm5 = (TextView)findViewById(R.id.qm5_new_text_f);

        viewqm1 = (TextView)findViewById(R.id.new_qm1_text_f);
        viewqm2 = (TextView)findViewById(R.id.new_qm2_text_f);
        viewqm3 = (TextView)findViewById(R.id.new_qm3_text_f);
        viewqm4 = (TextView)findViewById(R.id.new_qm4_text_f);
        viewqm5 = (TextView)findViewById(R.id.new_qm5_text_f);


        q_1 = Objects.requireNonNull(getIntent().getExtras()).getString("q1");
        q_2 = getIntent().getExtras().getString("q2");
        q_3 = getIntent().getExtras().getString("q3");
        q_4 = getIntent().getExtras().getString("q4");
        q_5 = getIntent().getExtras().getString("q5");

        R_1 = getIntent().getExtras().getString("R1");
        R_2 = getIntent().getExtras().getString("R2");
        R_3 = getIntent().getExtras().getString("R3");
        R_4 = getIntent().getExtras().getString("R4");
        R_5 = getIntent().getExtras().getString("R5");


        C_1 = getIntent().getExtras().getString("C1");
        C_2 = getIntent().getExtras().getString("C2");
        C_3 = getIntent().getExtras().getString("C3");
        C_4 = getIntent().getExtras().getString("C4");
        C_5 = getIntent().getExtras().getString("C5");

        Kl_1 = getIntent().getExtras().getString("Kl");
        qm_1 = getIntent().getExtras().getString("qm");

        lnq1 = getIntent().getExtras().getString("Logq1");
        lnq2 = getIntent().getExtras().getString("Logq2");
        lnq3 = getIntent().getExtras().getString("Logq3");
        lnq4 = getIntent().getExtras().getString("Logq4");
        lnq5 = getIntent().getExtras().getString("Logq5");

        lnc1 = getIntent().getExtras().getString("Logc1");
        lnc2 = getIntent().getExtras().getString("Logc2");
        lnc3 = getIntent().getExtras().getString("Logc3");
        lnc4 = getIntent().getExtras().getString("Logc4");
        lnc5 = getIntent().getExtras().getString("Logc5");


        viewq1.setText(q_1);
        viewq2.setText(q_2);
        viewq3.setText(q_3);
        viewq4.setText(q_4);
        viewq5.setText(q_5);

        viewR1.setText(R_1);
        viewR2.setText(R_2);
        viewR3.setText(R_3);
        viewR4.setText(R_4);
        viewR5.setText(R_5);

        viewC1.setText(C_1);
        viewC2.setText(C_2);
        viewC3.setText(C_3);
        viewC4.setText(C_4);
        viewC5.setText(C_5);

        viewKl1.setText(Kl_1);
        viewKl2.setText(Kl_1);
        viewKl3.setText(Kl_1);
        viewKl4.setText(Kl_1);
        viewKl5.setText(Kl_1);

        viewqm1.setText(qm_1);
        viewqm2.setText(qm_1);
        viewqm3.setText(qm_1);
        viewqm4.setText(qm_1);
        viewqm5.setText(qm_1);

        viewlq1.setText(lnq1);
        viewlq2.setText(lnq2);
        viewlq3.setText(lnq3);
        viewlq4.setText(lnq4);
        viewlq5.setText(lnq5);

        viewl_c1.setText(lnc1);
        viewl_c2.setText(lnc2);
        viewl_c3.setText(lnc3);
        viewl_c4.setText(lnc4);
        viewl_c5.setText(lnc5);


        drawerLayout = findViewById(R.id.conc_varying_results_f);
        toolbar = findViewById(R.id.toolbar_conc_varying_f);
        navigationView =  findViewById(R.id.navigation_View_conc_varying_f);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(test_freundlich_results.this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();


        }

        btn_nextgraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String next_graph = btn_nextgraph.getText().toString().trim();

                String q_new_1,q_new_2,q_new_3,q_new_4,q_new_5, c_new_1,c_new_2,c_new_3,_c_new_4_c_new_5;


                if (btn_nextgraph.getText().equals(next_graph))
                {
                    startActivity(new Intent(getApplicationContext(), Graph_page.class));
                    Intent intent = new Intent(test_freundlich_results.this, Graph_page.class);

                    intent.putExtra("Logq1",lnq1);
                    intent.putExtra("Logq2",lnq2);
                    intent.putExtra("Logq3",lnq3);
                    intent.putExtra("Logq4",lnq4);
                    intent.putExtra("Logq5",lnq5);

                    intent.putExtra("Logc1",lnc1);
                    intent.putExtra("Logc2",lnc2);
                    intent.putExtra("Logc3",lnc3);
                    intent.putExtra("Logc4",lnc4);
                    intent.putExtra("Logc5",lnc5);

                    startActivity(intent);
                }
            }
        });


        btn_back_time_varying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String back_results = btn_back_time_varying.getText().toString().trim();

                if (btn_back_time_varying.getText().equals(back_results))
                {
                    startActivity(new Intent(getApplicationContext(),Mass_Varying_Freundlich.class));
                }
            }
        });

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawerOpen, R.string.drawerClose);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (navigationView != null)
        {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(test_freundlich_results.this,"Successfully signed out",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(test_freundlich_results.this, Login.class));
                        finish();
                    }
                });
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.home:
                Toast.makeText(test_freundlich_results.this, "Home Selected" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.About:
                Toast.makeText(test_freundlich_results.this, "About Selected" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Console__langmuir_freundlich:
                startActivity(new Intent(test_freundlich_results.this,Navigation_Drawer.class));
                return true;
            case R.id.Sign_out_:
                signOut();
                startActivity(new Intent(test_freundlich_results.this,Login.class));
                return true;
            default:
                break;
        }
        return false;
    }
    }

