package com.kushal.firebasedatabase2auth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static android.R.attr.inAnimation;
import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {

    EditText mEditText;
    Button mButton;
    TextView mTextView;
    TextView mEtext;
    EditText mEmailText;
    Button mInfobtn;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference name = mRootRef.child("name");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.editText);
        mButton = (Button) findViewById(R.id.button2);
        mTextView = (TextView) findViewById(R.id.fbtxt);
        mEtext = (TextView) findViewById(R.id.emfbtxt);
        mEmailText = (EditText) findViewById(R.id.emailfb);
        mInfobtn = (Button) findViewById(R.id.infobtn);

    }

    @Override
    protected void onStart() {
        super.onStart();


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String usersnm = mEditText.getText().toString();
                String email = mEmailText.getText().toString();




                name.child("username").setValue(usersnm);
                name.child("email").setValue(email);

                mTextView.setText(usersnm);
                mEtext.setText(email);



            }
        });
        mInfobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this ,InfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
