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

public class Mass_varying extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    EditText m1,m2,m3,m4,m5;
    Button btn_next_mass, btn_back_mass, btn_save_mass;

    GoogleSignInClient mGoogleSignInClient;

    DrawerLayout drawerLayout;
    Toolbar toolbar;

    NavigationView navigationView;

    ActionBarDrawerToggle toggle;

    Mass_new_Database mass_new_database;

    DatabaseReference reff_mass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mass_varying);

        m1 = (EditText)findViewById(R.id.txt_time_m1);
        m2 = (EditText)findViewById(R.id.txt_time_m2);
        m3 = (EditText)findViewById(R.id.txt_time_m3);
        m4 = (EditText)findViewById(R.id.txt_time_m4);
        m5 = (EditText)findViewById(R.id.txt_time_m5);
        btn_next_mass = (Button)findViewById(R.id.next_mass);
        btn_back_mass = (Button)findViewById(R.id.back_mass);
        btn_save_mass = (Button)findViewById(R.id.save_mass);

        mass_new_database = new Mass_new_Database();
        reff_mass = FirebaseDatabase.getInstance().getReference().child("mass_newdatabase");


        drawerLayout = findViewById(R.id.mass_varying_conc);
        toolbar = findViewById(R.id.toolbar_mass_varying);
        navigationView =  findViewById(R.id.navigation_View_mass_varying);

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

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(Mass_varying.this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();


        }

        btn_next_mass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String next_mass_1= btn_next_mass.getText().toString().trim();

                String mass_new_1 = m1.getText().toString().trim();
                String mass_new_2 = m2.getText().toString().trim();
                String mass_new_3 = m3.getText().toString().trim();
                String mass_new_4 = m4.getText().toString().trim();
                String mass_new_5 = m5.getText().toString().trim();

                if (TextUtils.isEmpty(mass_new_1) || TextUtils.isEmpty(mass_new_2) || TextUtils.isEmpty(mass_new_3) || TextUtils.isEmpty(mass_new_4) || TextUtils.isEmpty(mass_new_5))
                {
                    Toast.makeText(Mass_varying.this, "Field Cannot Be Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(btn_next_mass.getText().equals(next_mass_1))
                    {
                        startActivity(new Intent(getApplicationContext(),InitialConcentration_Varying.class));
                        Intent intent = new Intent(Mass_varying.this, InitialConcentration_Varying.class);
                        intent.putExtra("M1",mass_new_1);
                        intent.putExtra("M2",mass_new_2);
                        intent.putExtra("M3",mass_new_3);
                        intent.putExtra("M4",mass_new_4);
                        intent.putExtra("M5",mass_new_5);
                        startActivity(intent);
                    }
                }



            }
        });

        btn_save_mass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String save = btn_save_mass.getText().toString().trim();

                String m_1 = m1.getText().toString().trim();
                String m_2 = m2.getText().toString().trim();
                String m_3 = m3.getText().toString().trim();
                String m_4 = m4.getText().toString().trim();
                String m_5 = m5.getText().toString().trim();

                if(btn_save_mass.getText().equals(save))
                {
                    reff_mass.child("Mass_varying_Concentration").setValue(mass_new_database);
                    Toast.makeText(Mass_varying.this, "data inserted successfully", Toast.LENGTH_LONG).show();
                }
                mass_new_database.setM1_new(m_1);
                mass_new_database.setM2_new(m_2);
                mass_new_database.setM3_new(m_3);
                mass_new_database.setM4_new(m_4);
                mass_new_database.setM5_new(m_5);
            }
        });

        btn_back_mass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String back_mass = btn_back_mass.getText().toString().trim();
                if (btn_back_mass.getText().equals(back_mass))
                {
                    startActivity(new Intent(getApplicationContext(),choose_equ_conc.class));
                }
            }
        });
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        Toast.makeText(Mass_varying.this,"Successfully signed out",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Mass_varying.this, Login.class));
                        finish();
                    }
                });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.home:
                Toast.makeText(Mass_varying.this, "Home Selected" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.About:
                Toast.makeText(Mass_varying.this, "About Selected" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Console__langmuir_freundlich:
                startActivity(new Intent(Mass_varying.this,Navigation_Drawer.class));
                return true;
            case R.id.Sign_out_:
                signOut();
                startActivity(new Intent(Mass_varying.this,Login.class));
                return true;
            default:
                break;
        }
        return false;
    }
}
