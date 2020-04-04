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

public class Langmuir extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Spinner spinner_mass, spinner_conc;
    ArrayAdapter<CharSequence> adapter_mass, adapter_conc;
    EditText txtMass, txtInitConce;
    Button btn_calculate, btn_equilibrium, btn_back_lang, btn_save_langmuir;
//    RadioGroup radioGroup;


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
        setContentView(R.layout.activity_langmuir);
//            getSupportActionBar().setTitle("Langmuir Isotherm Modelling");

            drawerLayout = findViewById(R.id.drawer_langmuir);
            toolbar = findViewById(R.id.toolbar_langmuir);
            navigationView =  findViewById(R.id.navigation_View_langmuir);
//
//            sign_out = findViewById(R.id.sign_out_google);
            txtMass = (EditText)findViewById(R.id.txt_massads);
            txtInitConce = (EditText)findViewById(R.id.txt_initconc);
            btn_calculate = (Button)findViewById(R.id.calculatebutton);
//            radioGroup = findViewById(R.id.radio_group);
//            btn_equilibrium = (Button)findViewById(R.id.eq_button);
            btn_back_lang = (Button)findViewById(R.id.back_langmuir_button);
            btn_save_langmuir = (Button)findViewById(R.id.save_langmuir);

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

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(Langmuir.this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();


            }

//            sign_out.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    signOut();
//                }
//            });

            btn_back_lang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String back_lang_1 = btn_back_lang.getText().toString().trim();

                    if (btn_back_lang.getText().equals(back_lang_1))
                    {
                        startActivity(new Intent(getApplicationContext(),choose_equ_conc.class));
                    }
                }
            });



            btn_save_langmuir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String save_langmuir = btn_save_langmuir.getText().toString().trim();

                    String mass_data = txtMass.getText().toString().trim();
                    String Co_data = txtInitConce.getText().toString().trim();

                    if(btn_save_langmuir.getText().equals(save_langmuir))
                    {
                        reff2.child("Mass").setValue(mass_database);
                        Toast.makeText(Langmuir.this, "data inserted successfully", Toast.LENGTH_LONG).show();
                    }

                    mass_database.setMass_new(mass_data);
                    mass_database.setCo_new(Co_data);
                }
            });
               btn_calculate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String lang_1 = btn_calculate.getText().toString().trim();

                    String mass = txtMass.getText().toString().trim();
                    String initconc = txtInitConce.getText().toString().trim();

                    if(TextUtils.isEmpty(mass) || TextUtils.isEmpty(initconc) )
                    {
                        Toast.makeText(Langmuir.this, "Field Cannot be Empty", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if(btn_calculate.getText().equals(lang_1))
                        {
                            startActivity(new Intent(getApplicationContext(),Equilibrium.class));
                            Intent intent = new Intent(Langmuir.this, Equilibrium.class);
                            intent.putExtra("Mass",mass);
                            intent.putExtra("InitConc",initconc);
                            startActivity(intent);
                        }
                    }



                }
            });

           spinner_mass=  (Spinner)findViewById(R.id.spinner_mass );
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
            spinner_conc=  (Spinner)findViewById(R.id.spinner_conc );
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
                    Toast.makeText(getBaseContext(), " Invalid Input", Toast.LENGTH_LONG).show();

                }
            });

    }



//    public void btn_next(View view) {
//            int radioid = radioGroup.getCheckedRadioButtonId();
//
//            btn_calculate = findViewById(radioid);
//
//    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Langmuir.this,"Successfully signed out",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Langmuir.this, Login.class));
                        finish();
                    }
                });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.home:
                Toast.makeText(Langmuir.this, "Home Selected" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.About:
                Toast.makeText(Langmuir.this, "About Selected" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Console__langmuir_freundlich:
                startActivity(new Intent(Langmuir.this,Navigation_Drawer.class));
                return true;
            case R.id.Sign_out_:
                signOut();
                startActivity(new Intent(Langmuir.this,Login.class));
                return true;
            default:
                break;
        }
        return false;
    }
}
