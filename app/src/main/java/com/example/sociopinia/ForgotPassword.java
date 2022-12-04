package com.example.sociopinia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    EditText resetemail;
    Button resetpwdemail;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        resetemail = findViewById(R.id.textresetemail);
        resetpwdemail = findViewById(R.id.btnfrgtpwd);

        mAuth = FirebaseAuth.getInstance();

        resetpwdemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usermail = resetemail.getText().toString().trim();

                if (usermail.equals(""))
                {
                    Toast.makeText(ForgotPassword.this, "Please enter your Email!", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    mAuth.sendPasswordResetEmail(usermail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful())
                            {
                                Toast.makeText(ForgotPassword.this, "Please check your email account for Reset Password link!", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(ForgotPassword.this, Login.class));
                            }

                            else
                            {
                                String message = task.getException().getMessage();
                                Toast.makeText(ForgotPassword.this, "Error Occurred"+message, Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

    }
}