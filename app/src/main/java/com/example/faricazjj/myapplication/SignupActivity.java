package com.example.faricazjj.myapplication;

import android.support.v7.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;
import android.support.annotation.*;
import com.google.firebase.database.*;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override

            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    //Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    //Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    public void onSignUp(View Button) {
        //after clicking the button
        EditText firstBox = (EditText)findViewById(R.id.editText2);
        EditText lastBox = (EditText)findViewById(R.id.editText4);
        EditText usernameBox = (EditText)findViewById(R.id.editText7);
        EditText emailBox = (EditText)findViewById(R.id.editText11);
        EditText passwordBox = (EditText)findViewById(R.id.editText10);
        String first = firstBox.getText().toString();
        String last = lastBox.getText().toString();
        String username = usernameBox.getText().toString();
        String email = emailBox.getText().toString();
        String password = passwordBox.getText().toString();

        Task<AuthResult> userEvent = mAuth.createUserWithEmailAndPassword(email, password);
        String uid = userEvent.getResult().getUser().getUid();


        Map<String,Object> map = new HashMap<>();
        map.put("Email", email);
        map.put("Firstname", first);
        map.put("Lastname", last);
        map.put("Username", username);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("Users");

        userRef.setValue(map);
    }
}
