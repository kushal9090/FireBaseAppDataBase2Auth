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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {

    EditText mEditText;
    Button mButton;
    TextView mTextView;


    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference condition = mRootRef.child("name");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.editText);
        mButton = (Button) findViewById(R.id.button2);
        mTextView = (TextView) findViewById(R.id.fbtxt);
    }

    @Override
    protected void onStart() {
        super.onStart();

        condition.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String name = dataSnapshot.getValue(String.class);
                mTextView.setText(name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String usersnm = mEditText.getText().toString();
                condition.setValue(usersnm);

            }
        });
    }
}
