package com.example.newapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signu extends AppCompatActivity {

    EditText txtEmail, txtPassword, txtConfirmPassword, txtFullName, txtUsername;
    Button btn_register;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signu);
//        Objects.requireNonNull(getSupportActionBar()).setTitle("Register");

        txtEmail = (EditText)findViewById(R.id.txt_email);
        txtPassword = (EditText)findViewById(R.id.txt_password);
        txtConfirmPassword = (EditText)findViewById(R.id.tx_confirm_password);
        txtFullName = (EditText)findViewById(R.id.txt_full_name);
        txtUsername = (EditText)findViewById(R.id.txt_username);
        btn_register = (Button) findViewById(R.id.registerButton);


        firebaseAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String fullname = txtFullName.getText().toString().trim();
                String username = txtUsername.getText().toString().trim();
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String confirmpassword = txtConfirmPassword.getText().toString().trim();


                if(TextUtils.isEmpty(fullname))
                {
                    Toast.makeText(Signu.this, "Please Enter Full Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(username))
                {
                    Toast.makeText(Signu.this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(Signu.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(Signu.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirmpassword))
                {
                    Toast.makeText(Signu.this, "Cannot be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length()<6)
                {
                    Toast.makeText(Signu.this,"Password too short", Toast.LENGTH_SHORT).show();
                }

                if(password.equals(confirmpassword))
                {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signu.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        startActivity(new Intent(getApplicationContext(),Login.class));
                                        Toast.makeText(Signu.this,"Registration Complete",Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(Signu.this,"Authentication Failed",Toast.LENGTH_SHORT).show();
                                    }

                                                                    }
                            });
                }
                else
                {
                    Toast.makeText(Signu.this,"Password do not match", Toast.LENGTH_SHORT).show();
                }

            }
        });






    }
}
