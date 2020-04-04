package com.example.newapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Time_Varying extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {


    Button btn_calculate_time, btn_back_time, btn_save_time_next;
    EditText  conc_1, conc_2, conc_3, conc_4, conc_5;
    String mass1, mass2, mass3,mass4, mass5, init_conc1, init_conc2, init_conc3, init_conc4, init_conc5, t, m_1_new, i_1_new;
    boolean c1, c2, c3, c4, c5;
    ViewGroup Time_Vary;
    int k;
//    TextView txt_langmuir_head, txt_freundlich_head;
    String co1, co2, co3, co4, co5;
    Double m_new_1,m_new_2,m_new_3,m_new_4,m_new_5, i_new1,i_new2,i_new3,i_new4,i_new5, m_double,i_double;

    Double slope, y2, y1, qm,y_intercept, Kl;

    Double qe1, qe2,qe3,qe4,qe5, c_1, c_2, c_3, c_4, c_5, R1, R2, R3, R4, R5;
    String qe_1,qe_2, qe_3, qe_4, qe_5,  R_1, R_2, R_3, R_4, R_5;

    String qm1, Kl1, C_1,C_2,C_3,C_4,C_5;
    GoogleSignInClient mGoogleSignInClient;

    DrawerLayout drawerLayout;
    Toolbar toolbar;

    NavigationView navigationView;

    ActionBarDrawerToggle toggle;


    Time_save_concentration time_save_concentration;
    DatabaseReference reff4;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time__varying);

        //time_1 = (EditText) findViewById(R.id.txt_time_min);
        conc_1 = (EditText) findViewById(R.id.txt_time_C1);
        conc_2 = (EditText) findViewById(R.id.txt_time_C2);
        conc_3 = (EditText) findViewById(R.id.txt_time_C3);
        conc_4 = (EditText) findViewById(R.id.txt_time_C4);
        conc_5 = (EditText) findViewById(R.id.txt_time_C5);
        Time_Vary = findViewById(R.id.time_varying);

        btn_save_time_next = (Button)findViewById(R.id.save_time);


        btn_calculate_time = (Button) findViewById(R.id.calculate_time);
        btn_back_time = (Button) findViewById(R.id.back_time);

        drawerLayout = findViewById(R.id.time_varying);
        toolbar = findViewById(R.id.toolbar_time_varying);
        navigationView =  findViewById(R.id.navigation_View_time_varying);

        init_conc1 = Objects.requireNonNull(getIntent().getExtras()).getString("C1");
        init_conc2 = getIntent().getExtras().getString("C2");
        init_conc3 = getIntent().getExtras().getString("C3");
        init_conc4 = getIntent().getExtras().getString("C4");
        init_conc5 = getIntent().getExtras().getString("C5");

//        m_1_new = getIntent().getExtras().getString("Mass");
//
//       i_1_new = getIntent().getExtras().getString("InitConc");

        mass1 = getIntent().getExtras().getString("M1");
        mass2 = getIntent().getExtras().getString("M2");
        mass3 = getIntent().getExtras().getString("M3");
        mass4 = getIntent().getExtras().getString("M4");
        mass5 = getIntent().getExtras().getString("M5");


        co1 = conc_1.getText().toString().trim();
        co2 = conc_2.getText().toString().trim();
        co3 = conc_3.getText().toString().trim();
        co4 = conc_4.getText().toString().trim();
        co5 = conc_5.getText().toString().trim();
//        t = time_1.getText().toString().trim();


        time_save_concentration = new Time_save_concentration();

       reff4 = FirebaseDatabase.getInstance().getReference().child("Time_save_concentration");

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawerOpen, R.string.drawerClose);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (navigationView != null)
        {
            navigationView.setNavigationItemSelectedListener(this);
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(Time_Varying.this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();


        }


        btn_save_time_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String time_database = btn_save_time_next.getText().toString().trim();

//                String time_data_new = time_1.getText().toString().trim();
                String C1_data_new = conc_1.getText().toString().trim();
                String C2_data_new = conc_2.getText().toString().trim();
                String C3_data_new = conc_3.getText().toString().trim();
                String C4_data_new = conc_4.getText().toString().trim();
                String C5_data_new = conc_5.getText().toString().trim();

                if (btn_save_time_next.getText().equals(time_database))
                {
                    reff4.child("Varying_Concentration").setValue(time_save_concentration);
                    Toast.makeText(Time_Varying.this, "data inserted successfully", Toast.LENGTH_LONG).show();
                }

                time_save_concentration.setC1_new_(C1_data_new);
                time_save_concentration.setC2_new(C2_data_new);
                time_save_concentration.setC3_new(C3_data_new);
                time_save_concentration.setC4_new(C4_data_new);
                time_save_concentration.setC5_new(C5_data_new);
//                time_save_concentration.setTime_new(time_data_new);
            }
        });


//        time_1.setOnKeyListener(this);
//        transition();
        btn_back_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String back_time = btn_back_time.getText().toString().trim();

                if (btn_back_time.getText().equals(back_time)) {
                    startActivity(new Intent(getApplicationContext(), InitialConcentration_Varying.class));
                }
            }
        });

        btn_calculate_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String time_page = btn_calculate_time.getText().toString().trim();
                C_1 = conc_1.getText().toString().trim();
                C_2 = conc_2.getText().toString().trim();
                C_3 = conc_3.getText().toString().trim();
                C_4 = conc_4.getText().toString().trim();
                C_5 = conc_5.getText().toString().trim();


                if (TextUtils.isEmpty(C_1) || TextUtils.isEmpty(C_2) || TextUtils.isEmpty(C_3) || TextUtils.isEmpty(C_4) || TextUtils.isEmpty(C_5))
                {
                    Toast.makeText(Time_Varying.this, "Field Cannot Be Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    calculate_time();
                    if (btn_calculate_time.getText().equals(time_page)) {
                        qe_1 = qe1.toString().trim();
                        qe_2 = qe2.toString().trim();
                        qe_3 = qe3.toString().trim();
                        qe_4 = qe4.toString().trim();
                        qe_5 = qe5.toString().trim();

                        R_1 = R1.toString().trim();
                        R_2 = R2.toString().trim();
                        R_3 = R3.toString().trim();
                        R_4 = R4.toString().trim();
                        R_5 = R5.toString().trim();

                        qm1 = qm.toString().trim();
                        Kl1 = Kl.toString().trim();

                        startActivity(new Intent(getApplicationContext(), test_langmuir_results.class));
                        Intent intent = new Intent(Time_Varying.this, test_langmuir_results.class);
                        intent.putExtra("q1",qe_1);
                        intent.putExtra("q2",qe_2);
                        intent.putExtra("q3",qe_3);
                        intent.putExtra("q4",qe_4);
                        intent.putExtra("q5",qe_5);

                        intent.putExtra("R1",R_1);
                        intent.putExtra("R2",R_2);
                        intent.putExtra("R3",R_3);
                        intent.putExtra("R4",R_4);
                        intent.putExtra("R5",R_5);

                        intent.putExtra("C1",C_1);
                        intent.putExtra("C2",C_2);
                        intent.putExtra("C3",C_3);
                        intent.putExtra("C4",C_4);
                        intent.putExtra("C5",C_5);

                        intent.putExtra("qm",qm1);
                        intent.putExtra("Kl",Kl1);
                        startActivity(intent);


                    }
                }



            }
        });
    }
//    @Override
//    public boolean onKey(View v, int keyCode, KeyEvent event) {
//
//        if(keyCode == KeyEvent.KEYCODE_ENTER)
//        {
//            Toast.makeText(this, "Go on", Toast.LENGTH_SHORT).show();
//            c1= !c1;
//            conc_1.setVisibility(c1 ? View.VISIBLE: View.GONE);
//            c2= !c2;
//            conc_2.setVisibility(c2 ? View.VISIBLE: View.GONE);
//            c3= !c3;
//            conc_3.setVisibility(c3 ? View.VISIBLE: View.GONE);
//            c4= !c4;
//            conc_4.setVisibility(c4 ? View.VISIBLE: View.GONE);
//            c5= !c5;
//            conc_5.setVisibility(c5 ? View.VISIBLE: View.GONE);
////            transition();
//            return true;
//        }
//        return false;
//    }
    public void calculate_time()
        {
//             m_double = Double.parseDouble(m_1_new);
//             i_double = Double.parseDouble(i_1_new);
            m_new_1 = Double.parseDouble(mass1);
            m_new_2 = Double.parseDouble(mass2);
            m_new_3 = Double.parseDouble(mass3);
            m_new_4 = Double.parseDouble(mass4);
            m_new_5 = Double.parseDouble(mass5);

            i_new1 = Double.parseDouble(init_conc1);
            i_new2 = Double.parseDouble(init_conc2);
            i_new3 = Double.parseDouble(init_conc3);
            i_new4 = Double.parseDouble(init_conc4);
            i_new5 = Double.parseDouble(init_conc5);


            c_1 = Double.parseDouble(conc_1.getText().toString().trim());
            c_2 = Double.parseDouble(conc_2.getText().toString().trim());
            c_3 = Double.parseDouble(conc_3.getText().toString().trim());
            c_4 = Double.parseDouble(conc_4.getText().toString().trim());
            c_5 = Double.parseDouble(conc_5.getText().toString().trim());

            qe1 = (100 * ((i_new1 - c_1) / (m_new_1*1000)));
            qe2 = (100 * ((i_new2 - c_2) / (m_new_2*1000)));
            qe3 = (100 * ((i_new3 - c_3) / (m_new_3*1000)));
            qe4 = (100 * ((i_new4 - c_4) / (m_new_4*1000)));
            qe5 = (100 * ((i_new5 - c_5) / (m_new_5*1000)));

            R1 = (100 * ((i_new1 - c_1) / i_new1));
            R2 = (100 * ((i_new2 - c_2) / i_new2));
            R3 = (100 * ((i_new3 - c_3) / i_new3));
            R4 = (100 * ((i_new4 - c_4) / i_new4));
            R5 = (100 * ((i_new5 - c_5) / i_new5));

            y2 = c_5/qe5;
            y1 = c_1/qe1;

            slope = ((y2-y1)/(c_5-c_1));

            qm = (1/slope);

            y_intercept = (y1 - (slope*c_1));

            Kl = (1/(qm*y_intercept));




        }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        Toast.makeText(Time_Varying.this,"Successfully signed out",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Time_Varying.this, Login.class));
                        finish();
                    }
                });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.home:
                Toast.makeText(Time_Varying.this, "Home Selected" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.About:
                Toast.makeText(Time_Varying.this, "About Selected" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Console__langmuir_freundlich:
                startActivity(new Intent(Time_Varying.this,Navigation_Drawer.class));
                return true;
            case R.id.Sign_out_:
                signOut();
                startActivity(new Intent(Time_Varying.this,Login.class));
                return true;
            default:
                break;
        }
        return false;
    }







}
