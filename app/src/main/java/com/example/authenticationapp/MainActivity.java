package com.example.authenticationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    EditText email,phone,password,confirmpassword = findViewById(R.id.txtpass1);
    Button btnregister;

    FirebaseAuth authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.txtemail);
        phone = findViewById(R.id.txtpass);
        password = findViewById(R.id.txtpass);
        btnregister = findViewById(R.id.btnregister);
        authentication = FirebaseAuth.getInstance();

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createuser();

            }
        });

    }

    private void createuser() {
        String Email = email.getText().toString();
        String Password = password.getText().toString();
        String Confirm = confirmpassword.getText().toString();

        if (TextUtils.isEmpty(Email)){
            email.setError("Field cannot be empty");
        } else if (TextUtils.isEmpty(Password)) {
            password.setError("Field Cannot be empty");
        }else if (TextUtils.isEmpty(Confirm)){
            confirmpassword.setError("Field cannot be empty");
        }else {
            authentication.createUserWithEmailAndPassword(Email,Password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }
}