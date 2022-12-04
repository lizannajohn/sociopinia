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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText email, pwd;
    Button btnlogin, signuprdrct, frgtpwd;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.textemail);
        pwd = findViewById(R.id.textpwd);

        btnlogin = findViewById(R.id.succlogin);
        signuprdrct = findViewById(R.id.signuprdrct);
        frgtpwd = findViewById(R.id.frgtpwd);

        //Firebase Authentication
        mAuth = FirebaseAuth.getInstance();
    }

    private boolean validateemail()
    {
        String flag = email.getText().toString();
        String emailformat = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        if (flag.isEmpty())
        {
            email.setError("Field cannot be Empty!");
            return false;
        }
        else if (!flag.matches(emailformat))
        {
            email.setError("Incorrect Email ID!");
            return false;
        }
        else
        {
            email.setError(null);
            return true;
        }
    }

    private boolean validatepwd()
    {
        String flag = pwd.getText().toString();

        if (flag.isEmpty())
        {
            pwd.setError("Field cannot be Empty!");
            return false;
        }
        else
        {
            pwd.setError(null);
            return true;
        }
    }

    public void onclickforgotpwd (View view)
    {
        Intent forgotpwd = new Intent(Login.this, ForgotPassword.class);
        startActivity(forgotpwd);
    }

    public void signuprdrct (View view)
    {
        Intent signuprdrct = new Intent(Login.this, SignUp.class);
        startActivity(signuprdrct);
    }

    public void onclicksucclogin(View view)
    {
        if (!validateemail() | !validatepwd())
        {
            return;
        }

        String mail = email.getText().toString();
        String password = pwd.getText().toString();

        //Authenticate User
        mAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(Login.this, "Sign in Successful!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), NavigationDrawer.class));
                }
                else
                {
                    Toast.makeText(Login.this, "Error: "+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}