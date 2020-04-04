package com.example.newapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
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

public class InitConc_varying_Freundlich extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    EditText c1,c2,c3,c4,c5;
    Button btn_next_initconc, btn_back_initconc, btn_save_initconc;

    GoogleSignInClient mGoogleSignInClient;

    DrawerLayout drawerLayout;
    String mass_1, mass_2,mass_3,mass_4,mass_5;
    Toolbar toolbar;

    NavigationView navigationView;

    ActionBarDrawerToggle toggle;

    initconc_database initconc_database;

    DatabaseReference reff_initconc_f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_conc_varying__freundlich);

        c1 = (EditText)findViewById(R.id.txt_time_c1_f);
        c2 = (EditText)findViewById(R.id.txt_time_c2_f);
        c3 = (EditText)findViewById(R.id.txt_time_c3_f);
        c4 = (EditText)findViewById(R.id.txt_time_c4_f);
        c5 = (EditText)findViewById(R.id.txt_time_c5_f);
        btn_next_initconc = (Button)findViewById(R.id.next_initconc_f);
        btn_back_initconc= (Button)findViewById(R.id.back_initconc_f);
        btn_save_initconc= (Button)findViewById(R.id.save_initconc_f);

        mass_1 = (Objects.requireNonNull(getIntent().getExtras())).getString("M1");
        mass_2 = getIntent().getExtras().getString("M2");
        mass_3 = getIntent().getExtras().getString("M3");
        mass_4 = getIntent().getExtras().getString("M4");
        mass_5 = getIntent().getExtras().getString("M5");

        initconc_database = new initconc_database();
        reff_initconc_f = FirebaseDatabase.getInstance().getReference().child("Init_Conc");

        drawerLayout = findViewById(R.id.initial_varying_conc_f);
        toolbar = findViewById(R.id.toolbar_initconc_varying_f);
        navigationView =  findViewById(R.id.navigation_View_initconc_varying_f);

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

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(InitConc_varying_Freundlich.this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();


        }

        btn_next_initconc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String next_initconc_1= btn_next_initconc.getText().toString().trim();

                String initconc_new_1 = c1.getText().toString().trim();
                String initconc_new_2 = c2.getText().toString().trim();
                String initconc_new_3 = c3.getText().toString().trim();
                String initconc_new_4 = c4.getText().toString().trim();
                String initconc_new_5 = c5.getText().toString().trim();

                if (TextUtils.isEmpty(initconc_new_1) || TextUtils.isEmpty(initconc_new_2) || TextUtils.isEmpty(initconc_new_3) || TextUtils.isEmpty(initconc_new_4) || TextUtils.isEmpty(initconc_new_5))
                {
                    Toast.makeText(InitConc_varying_Freundlich.this, "Field Cannot Be Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(btn_next_initconc.getText().equals(next_initconc_1))
                    {
                        startActivity(new Intent(getApplicationContext(),time_varying_freundlich.class));
                        Intent intent = new Intent(InitConc_varying_Freundlich.this, time_varying_freundlich.class);
                        intent.putExtra("C1",initconc_new_1);
                        intent.putExtra("C2",initconc_new_2);
                        intent.putExtra("C3",initconc_new_3);
                        intent.putExtra("C4",initconc_new_4);
                        intent.putExtra("C5",initconc_new_5);

//                   new_intent.putExtras(Objects.requireNonNull(getIntent().getExtras()));

                        intent.putExtra("M1",mass_1);
                        intent.putExtra("M2",mass_2);
                        intent.putExtra("M3",mass_3);
                        intent.putExtra("M4",mass_4);
                        intent.putExtra("M5",mass_5);

                        startActivity(intent);
                    }
                }


            }
        });

        btn_save_initconc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String save = btn_save_initconc.getText().toString().trim();

                String c_1 = c1.getText().toString().trim();
                String c_2 = c2.getText().toString().trim();
                String c_3 = c3.getText().toString().trim();
                String c_4 = c4.getText().toString().trim();
                String c_5 = c5.getText().toString().trim();

                if(btn_save_initconc.getText().equals(save))
                {
                    reff_initconc_f.child("InitialConcentration_varying_Concentration").setValue(initconc_database);
                    Toast.makeText(InitConc_varying_Freundlich.this, "data inserted successfully", Toast.LENGTH_LONG).show();
                }
                initconc_database.setC_1(c_1);
                initconc_database.setC_2(c_2);
                initconc_database.setC_3(c_3);
                initconc_database.setC_4(c_4);
                initconc_database.setC_5(c_5);
            }
        });

        btn_back_initconc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String back_mass = btn_back_initconc.getText().toString().trim();
                if (btn_back_initconc.getText().equals(back_mass))
                {
                    startActivity(new Intent(getApplicationContext(),Mass_varying.class));
                }
            }
        });
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(InitConc_varying_Freundlich.this,"Successfully signed out",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(InitConc_varying_Freundlich.this, Login.class));
                        finish();
                    }
                });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.home:
                Toast.makeText(InitConc_varying_Freundlich.this, "Home Selected" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.About:
                Toast.makeText(InitConc_varying_Freundlich.this, "About Selected" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Console__langmuir_freundlich:
                startActivity(new Intent(InitConc_varying_Freundlich.this,Navigation_Drawer.class));
                return true;
            case R.id.Sign_out_:
                signOut();
                startActivity(new Intent(InitConc_varying_Freundlich.this,Login.class));
                return true;
            default:
                break;
        }
        return false;
    }
}
