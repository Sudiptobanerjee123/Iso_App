package com.example.newapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText txtEmail, txtPassword;
    Button btn_login, btn_googlesignin;
    private FirebaseAuth firebaseAuth;
    int rc_sign_in = 0;
    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        getSupportActionBar().setTitle("Login");


        signInButton = findViewById(R.id.google_signin_main);
        signInButton.setSize(SignInButton.SIZE_STANDARD);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();


        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(Login.this);

        if(acct !=null)

        {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
        }

       mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        signInButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        }));


        txtEmail = (EditText)findViewById(R.id.txt_Email);
        txtPassword = (EditText)findViewById(R.id.txt_Password);
        btn_login = (Button) findViewById(R.id.loginbutton);
        firebaseAuth = FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();


                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(Login.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(Login.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length()<6)
                {
                    Toast.makeText(Login.this,"Password too short", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    startActivity(new Intent(getApplicationContext(),Navigation_Drawer.class));
                                    Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(Login.this,"Login Failed or User not available",Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });

    }

    public void btn_signupForm(View view) {

        startActivity(new Intent(getApplicationContext(),Signu.class));
    }

    private void signIn()
    {
        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent,rc_sign_in);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == rc_sign_in)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handlesigninresult(task);
        }
    }
    private void handlesigninresult(Task<GoogleSignInAccount> completedTask)
    {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            startActivity(new Intent(Login.this,Navigation_Drawer.class));
        }
        catch (ApiException e) {
            Log.w("Google Sign in Error", "signInResult:failed code =" + e.getStatusCode());
            Toast.makeText(Login.this, "Failed", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    @Override
    protected void onStart() {

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);


        if (account != null)
        {
            startActivity(new Intent(Login.this,Navigation_Drawer.class));
        }
        super.onStart();
    }
}
