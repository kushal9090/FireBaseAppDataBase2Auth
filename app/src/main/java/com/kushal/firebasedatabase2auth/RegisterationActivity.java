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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterationActivity extends AppCompatActivity {

    Button mSigninbtn;
    Button mSignupbtn;

    EditText mEmailtb;
    EditText mPasstb;
    EditText mName;

    ProgressDialog mProgress;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        mProgress = new ProgressDialog(this);
        mEmailtb = (EditText) findViewById(R.id.emailTB);
        mPasstb = (EditText) findViewById(R.id.passTB);
        mName = (EditText) findViewById(R.id.nameTB);

        mSignupbtn = (Button) findViewById(R.id.signUpbtn);
        mSigninbtn = (Button) findViewById(R.id.signinBtn);

        mDatabase =FirebaseDatabase.getInstance().getReference().child("users");
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

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void registeration() {
        final String name = mName.getText().toString().trim();
        final String email = mEmailtb.getText().toString().trim();
        String password = mPasstb.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this , "PLEASE ENTER EMAIL ....",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this , "PLEASE ENTER NAME ....",Toast.LENGTH_LONG).show();
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
                             String user_id = mAuth.getCurrentUser().getUid();

                            DatabaseReference current_u_id = mDatabase.child(user_id);

                            current_u_id.child("name").setValue(name);
                            current_u_id.child("email").setValue(email);


                            mProgress.dismiss();
                            Toast.makeText(RegisterationActivity.this, "Sign up successful..",Toast.LENGTH_LONG ).show();




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



