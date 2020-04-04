package com.example.newapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.TransitionManager;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class New_equilibrium extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    Spinner spinner_equ_conc;
    ArrayAdapter<CharSequence> adapter_equ_conc;
    EditText txt_equilibrium_conc;
    Button btn_calculate_equilibrium, btn_back_equilibrium, btn_save;
    TextView text_qe, text_R, txtqe,txtR, textlnqe, textlnce, txtlnqe, txtlnce;
    ViewGroup equilibriume;
    String mass1, init_conc;
    Double CE,m,i,q,r, l_q, l_c;

    GoogleSignInClient mGoogleSignInClient;
    DrawerLayout drawerLayout;

    Toolbar toolbar;

    NavigationView navigationView;

    ActionBarDrawerToggle toggle;

    Equilibrium_database equilibrium_database;
    DatabaseReference reff1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_equilibrium);
//        getSupportActionBar().setTitle("Equilibrium");

        drawerLayout = findViewById(R.id.new_Equilibrium);
        toolbar = findViewById(R.id.toolbar_new_equlibrium);
        navigationView =  findViewById(R.id.navigation_View_new_equilibrium);

        txt_equilibrium_conc = (EditText)findViewById(R.id.txt_equ_conc_freundlich);
        equilibriume = findViewById(R.id.new_Equilibrium);
        txtqe = (TextView) findViewById(R.id.text_qe_freundlich);
        txtR = (TextView) findViewById(R.id.text_R_freundlich);
        btn_calculate_equilibrium = (Button)findViewById(R.id.calculate_equilibrium_freundlich);
        btn_back_equilibrium = (Button)findViewById(R.id.back_equilibrium_freundlich);
        btn_save = (Button)findViewById(R.id.save_databasse_freundlich);
        text_qe = (TextView)findViewById(R.id.qe_text_freundlich);
        text_R = (TextView)findViewById(R.id.R_text_freundlich);

        textlnqe = (TextView)findViewById(R.id.lnqe_text_freundlich);
        textlnce = (TextView)findViewById(R.id.lnce_text_freundlich);
        txtlnqe = (TextView)findViewById(R.id.text_lnqe_freundlich);
        txtlnce = (TextView)findViewById(R.id.text_lnce_freundlich);

        init_conc = Objects.requireNonNull(getIntent().getExtras()).getString("InitConc");
        mass1 = getIntent().getExtras().getString("Mass");

        equilibrium_database = new Equilibrium_database();

        reff1 = FirebaseDatabase.getInstance().getReference().child("Equilibrium_database");

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

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(New_equilibrium.this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();


        }


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String save = btn_save.getText().toString().trim();

                String qe_data = txtqe.getText().toString().trim();
                String Re_data = txtR.getText().toString().trim();
                String Ce_data = txt_equilibrium_conc.getText().toString().trim();

                String lnqe_data = txtlnqe.getText().toString().trim();
                String lnce_data = txtlnce.getText().toString().trim();

                if (btn_save.getText().equals(save))
                {
                    reff1.child("Equilibrium").setValue(equilibrium_database);
                    Toast.makeText(New_equilibrium.this, "data inserted successfully", Toast.LENGTH_LONG).show();
                }
                equilibrium_database.setQe_new(qe_data);
                equilibrium_database.setRe_new(Re_data);
                equilibrium_database.setCe_new(Ce_data);
                equilibrium_database.setLnqe_new(lnqe_data);
                equilibrium_database.setLnce_new(lnce_data);

            }
        });


        btn_calculate_equilibrium.setOnClickListener(new View.OnClickListener() {

            boolean visible_qe, visible_R, visible_lnqe, visible_lnce;
            @Override
            public void onClick(View v) {


                String eq_conc = txt_equilibrium_conc.getText().toString().trim();

                if(TextUtils.isEmpty(eq_conc))
                {
                    Toast.makeText(New_equilibrium.this, "Invalid Entry", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    calculate();
                    TransitionManager.beginDelayedTransition(equilibriume);
                    visible_qe= !visible_qe;
                    text_qe.setVisibility(visible_qe ? View.VISIBLE: View.GONE);

                    TransitionManager.beginDelayedTransition(equilibriume);
                    visible_R = !visible_R;
                    text_R.setVisibility(visible_R ? View.VISIBLE: View.GONE);

                    TransitionManager.beginDelayedTransition(equilibriume);
                    visible_lnqe= !visible_lnqe;
                    textlnqe.setVisibility(visible_lnqe ? View.VISIBLE: View.GONE);

                    TransitionManager.beginDelayedTransition(equilibriume);
                    visible_lnce= !visible_lnce;
                    textlnce.setVisibility(visible_lnce ? View.VISIBLE: View.GONE);


                }

            }
        });
        btn_back_equilibrium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String back_equilibrium= btn_back_equilibrium.getText().toString().trim();

                if(btn_back_equilibrium.getText().equals(back_equilibrium))
                {
                    startActivity(new Intent(getApplicationContext(),Freundlich.class));
                }

            }
        });

        spinner_equ_conc=  (Spinner)findViewById(R.id.spinner_equilibrium_conc_freundlich);
        adapter_equ_conc = ArrayAdapter.createFromResource(this,R.array.Equilibrium_Conc, android.R.layout.simple_spinner_item);
        adapter_equ_conc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_equ_conc.setAdapter(adapter_equ_conc);

        spinner_equ_conc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + " Equilibrium_Conc_selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @SuppressLint("SetTextI18n")
    public void calculate()
    {
        m = Double.parseDouble(mass1);
        i = Double.parseDouble(init_conc);

        CE = Double.parseDouble(txt_equilibrium_conc.getText().toString().trim());
        q = (100*((i-CE)/m));
        r = (100*((i-CE)/i));
        l_q = Math.log(q);
        l_c= Math.log(CE);
        txtqe.setText(q.toString().trim());
        txtR.setText(r.toString().trim());
        txtlnqe.setText(l_q.toString().trim());
        txtlnce.setText(l_c.toString().trim());

    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        Toast.makeText(New_equilibrium.this,"Successfully signed out",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(New_equilibrium.this, Login.class));
                        finish();
                    }
                });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.home:
                Toast.makeText(New_equilibrium.this, "Home Selected" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.About:
                Toast.makeText(New_equilibrium.this, "About Selected" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Console__langmuir_freundlich:
                startActivity(new Intent(New_equilibrium.this,Navigation_Drawer.class));
                return true;
            case R.id.Sign_out_:
                signOut();
                startActivity(new Intent(New_equilibrium.this,Login.class));
                return true;
            default:
                break;
        }
        return false;
    }
}
