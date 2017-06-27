package com.kushal.firebasedatabase2auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InformationActivity extends AppCompatActivity {

    EditText mAddress , mAge , mCity;
    Button mInformationbtn;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        mAddress = (EditText) findViewById(R.id.address);
        mAge = (EditText) findViewById(R.id.age);
        mCity =(EditText) findViewById(R.id.city);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("info");
        mAuth = FirebaseAuth.getInstance();

        mInformationbtn = (Button) findViewById(R.id.informationbtn);
        mInformationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                information();
            }
        });
    }

    private void information() {
        String address = mAddress.getText().toString().trim();
        String age = mAge.getText().toString().trim();
        String city = mCity.getText().toString().trim();

        String user_info = mAuth.getCurrentUser().getUid();

        DatabaseReference current_user_db = mDatabase.child(user_info).push();

        current_user_db.child("address").setValue(address);
        current_user_db.child("age").setValue(age);
        current_user_db.child("city").setValue(city);

        Intent intent = new Intent(InformationActivity.this , MainActivity.class);
        startActivity(intent);

    }
}
