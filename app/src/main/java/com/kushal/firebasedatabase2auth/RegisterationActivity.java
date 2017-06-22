package com.kushal.firebasedatabase2auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterationActivity extends AppCompatActivity {

    Button mSigninbtn;
    Button mSignupbtn;
    EditText mEmailtb;
    EditText mPasstb;
    ProgressDialog mProgress;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        mProgress = new ProgressDialog(this);
        mEmailtb = (EditText) findViewById(R.id.emailTB);
        mPasstb = (EditText) findViewById(R.id.passTB);

        mSignupbtn = (Button) findViewById(R.id.signUpbtn);
        mSigninbtn = (Button) findViewById(R.id.signinBtn);

        mAuth = FirebaseAuth.getInstance();

        mSignupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registeration();
            }
        });

        mSigninbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signIn();

            }
        });

    }


    private void registeration() {

        String email = mEmailtb.getText().toString().trim();
        String password = mPasstb.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this , "PLEASE ENTER EMAIL ....",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this , "PLEASE ENTER A VALID PASSWORD ....",Toast.LENGTH_LONG).show();
          return;
        }

      mProgress.setMessage("Signing up.....");
        mProgress.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            Intent intent = new Intent(RegisterationActivity.this , MainActivity.class);
                            Toast.makeText(RegisterationActivity.this, "Sign up successful..",Toast.LENGTH_LONG ).show();
                            startActivity(intent);
                            finish();


                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(RegisterationActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }

    private void signIn() {
        String email = mEmailtb.getText().toString().trim();
        String password = mPasstb.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this , "PLEASE ENTER EMAIL ....",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this , "PLEASE ENTER A VALID PASSWORD ....",Toast.LENGTH_LONG).show();
            return;
        }

        mProgress.setMessage("Signing in...");
        mProgress.show();
        mAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Intent intent2 = new Intent(RegisterationActivity.this , MainActivity.class);
                    Toast.makeText(RegisterationActivity.this , "Successfull sign in",Toast.LENGTH_LONG).show();
                    startActivity(intent2);
                    finish();
                    return;

                }else {
                    Toast.makeText(RegisterationActivity.this , "enter correct email and passsword",Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });


    }

 }



