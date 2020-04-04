package com.example.newapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class choose_Freundlich extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    RadioGroup radioGroup;
    Button btn_next_choose, btn_back_choose;
    RadioButton btn_eq, btn_conc;
    DrawerLayout drawerLayout;

    GoogleSignInClient mGoogleSignInClient;
    Button sign_out;

    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_freundlich);
        drawerLayout = findViewById(R.id.drawer_choose_freundlich);
        toolbar = findViewById(R.id.toolbar_choose_freundlich);
        navigationView =  findViewById(R.id.navigation_View_choose_freundlich);

        btn_next_choose = (Button)findViewById(R.id.calculatebutton_choose_freundlich);
        btn_back_choose = (Button)findViewById(R.id.backbutton_choose_freundlich);

        btn_eq = (RadioButton) findViewById(R.id.eq_button_freundlich_1);
        btn_conc = (RadioButton) findViewById(R.id.time_button_freundlich_1);

        radioGroup = findViewById(R.id.radio_group_freundlich_1);

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

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(choose_Freundlich.this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();


        }

        btn_next_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(btn_eq.isChecked() || btn_conc.isChecked())
                {
                    String eq = btn_eq.getText().toString().trim();
                    String conc_1 = btn_conc.getText().toString().trim();
                    int radioid = radioGroup.getCheckedRadioButtonId();

                    btn_next_choose = findViewById(radioid);

                    if(btn_next_choose.getText().equals(eq))
                    {
                        startActivity(new Intent(getApplicationContext(),Freundlich.class));
                    }
                    if(btn_next_choose.getText().equals(conc_1))
                    {
                        startActivity(new Intent(getApplicationContext(),Mass_Varying_Freundlich.class));
                    }
                }
                else
                {
                    Toast.makeText(choose_Freundlich.this, "Field Cannot be Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }
    public void btn_next(View view) {
        int radioid = radioGroup.getCheckedRadioButtonId();

        btn_next_choose = findViewById(radioid);

    }



    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(choose_Freundlich.this,"Successfully signed out",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(choose_Freundlich.this, Login.class));
                        finish();
                    }
                });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.home:
                Toast.makeText(choose_Freundlich.this, "Home Selected" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.About:
                Toast.makeText(choose_Freundlich.this, "About Selected" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Console__langmuir_freundlich:
                startActivity(new Intent(choose_Freundlich.this,Navigation_Drawer.class));
                return true;
            case R.id.Sign_out_:
                signOut();
                startActivity(new Intent(choose_Freundlich.this,Login.class));
                return true;
            default:
                break;
        }


        return false;
    }
}
