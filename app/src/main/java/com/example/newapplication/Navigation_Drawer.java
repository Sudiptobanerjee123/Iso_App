package com.example.newapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

public class Navigation_Drawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;

    Toolbar toolbar;

    NavigationView navigationView;

    ActionBarDrawerToggle toggle;

    GoogleSignInClient mGoogleSignInClient;


    Button btn_langmuir_card, btn_freundlich_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation__drawer);

        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView =  findViewById(R.id.navigation_View);

        btn_langmuir_card = (Button)findViewById(R.id.btn_cardview3);

        btn_freundlich_card = (Button)findViewById(R.id.btn_cardview2);




        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(Navigation_Drawer.this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();


        }

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawerOpen, R.string.drawerClose);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (navigationView != null)
        {
            navigationView.setNavigationItemSelectedListener(this);
        }

         btn_langmuir_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String click_langmuir= btn_langmuir_card.getText().toString().trim();

                if(btn_langmuir_card.getText().equals(click_langmuir))
                {
                    startActivity(new Intent(getApplicationContext(),choose_equ_conc.class));
                }
            }
        });

        btn_freundlich_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String click_langmuir= btn_freundlich_card.getText().toString().trim();

                if(btn_freundlich_card.getText().equals(click_langmuir))
                {
                    startActivity(new Intent(getApplicationContext(),choose_Freundlich.class));
                }
            }
        });
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.home:
                Toast.makeText(Navigation_Drawer.this, "Home Selected" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.About:
                Toast.makeText(Navigation_Drawer.this, "About Selected" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Sign_out_:
                signOut();
                startActivity(new Intent(Navigation_Drawer.this,Login.class));
                return true;
           default:
                break;
        }
        return false;
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Navigation_Drawer.this,"Successfully signed out",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Navigation_Drawer.this, Login.class));
                        finish();
                    }
                });
    }
}
