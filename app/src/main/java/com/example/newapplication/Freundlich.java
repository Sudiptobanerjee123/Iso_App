package com.example.newapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
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

public class Freundlich extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Spinner spinner_mass, spinner_conc;
    ArrayAdapter<CharSequence> adapter_mass, adapter_conc;
    EditText txtMass, txtInitConce;
    Button btn_calculate, btn_back_freundlich, btn_time, btn_save_freundlich;
    RadioGroup radioGroup;


    mass_database mass_database;
    DatabaseReference reff2;
    GoogleSignInClient mGoogleSignInClient;
    Button sign_out;

    DrawerLayout drawerLayout;

    Toolbar toolbar;

    NavigationView navigationView;

    ActionBarDrawerToggle toggle;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freundlich);
//            getSupportActionBar().setTitle("Langmuir Isotherm Modelling");


        drawerLayout = findViewById(R.id.drawer_freundlich);
        toolbar = findViewById(R.id.toolbar_freundlich);
        navigationView =  findViewById(R.id.navigation_View_freundlich);
//
//        sign_out = findViewById(R.id.sign_out_google_freundlich);
        txtMass = (EditText)findViewById(R.id.txt_mass_freundlich);
        txtInitConce = (EditText)findViewById(R.id.txt_initconc_freundlich);
        btn_calculate = (Button)findViewById(R.id.calculatebutton_freundlich);
        btn_back_freundlich = (Button)findViewById(R.id.backbutton_choose_freundlich_1);
        btn_save_freundlich = (Button)findViewById(R.id.save_freundlich);

        mass_database = new mass_database();
        reff2 = FirebaseDatabase.getInstance().getReference().child("mass_database");

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

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(Freundlich.this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();


        }

//        sign_out.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                signOut();
//            }
//        });



        btn_save_freundlich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String save_freundlich = btn_save_freundlich.getText().toString().trim();

                String mass_data = txtMass.getText().toString().trim();
                String Co_data = txtInitConce.getText().toString().trim();

                if(btn_save_freundlich.getText().equals(save_freundlich))
                {
                    reff2.child("Mass").setValue(mass_database);
                    Toast.makeText(Freundlich.this, "data inserted successfully", Toast.LENGTH_LONG).show();
                }

                mass_database.setMass_new(mass_data);
                mass_database.setCo_new(Co_data);
            }
        });
        btn_back_freundlich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String back = btn_back_freundlich.getText().toString().trim();

                if (btn_back_freundlich.getText().equals(back))
                {
                    startActivity(new Intent(getApplicationContext(),choose_Freundlich.class));
                }
            }
        });
        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mass = txtMass.getText().toString().trim();
                String initconc = txtInitConce.getText().toString().trim();
                String equilibrium = btn_calculate.getText().toString().trim();
                if(TextUtils.isEmpty(mass) || TextUtils.isEmpty(initconc))
                {
                    Toast.makeText(Freundlich.this, "Field Cannot be Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(btn_calculate.getText().equals(equilibrium))
                    {
                        startActivity(new Intent(getApplicationContext(),New_equilibrium.class));
                        Intent intent = new Intent(Freundlich.this, New_equilibrium.class);
                        intent.putExtra("Mass",mass);
                        intent.putExtra("InitConc",initconc);
                        startActivity(intent);
                    }
                }

            }
        });

        spinner_mass=  (Spinner)findViewById(R.id.spinner_mass_freundlich);
        adapter_mass = ArrayAdapter.createFromResource(this,R.array.Mass_value, android.R.layout.simple_spinner_item);
        adapter_mass.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_mass.setAdapter(adapter_mass);

        spinner_mass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


//                Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + " Mass_selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_conc=  (Spinner)findViewById(R.id.spinner_conc_freundlich);
        adapter_conc = ArrayAdapter.createFromResource(this,R.array.Conc_value, android.R.layout.simple_spinner_item);
        adapter_conc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_conc.setAdapter(adapter_conc);

        spinner_conc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + " Concentration_selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }




    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        Toast.makeText(Freundlich.this,"Successfully signed out",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Freundlich.this, Login.class));
                        finish();
                    }
                });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.home:
                Toast.makeText(Freundlich.this, "Home Selected" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.About:
                Toast.makeText(Freundlich.this, "About Selected" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Console__langmuir_freundlich:
                startActivity(new Intent(Freundlich.this,Navigation_Drawer.class));
                return true;
            case R.id.Sign_out_:
                signOut();
                startActivity(new Intent(Freundlich.this,Login.class));
                return true;
            default:
                break;
        }
        return false;
    }
}
