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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    EditText uname, email, pwd;
    Button btnsignup, loginrdrct;
    String userid;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        uname = findViewById(R.id.textuname);
        email = findViewById(R.id.textemail);
        pwd = findViewById(R.id.textpwd);

        btnsignup = findViewById(R.id.succsignup);
        loginrdrct = findViewById(R.id.loginrdrct);
    }

    private boolean validateuname(){
        String flag = uname.getText().toString();

        if (flag.isEmpty())
        {
            uname.setError("Field cannot be Empty!");
            return false;
        } else
        {
            uname.setError(null);
            return true;
        }
    }

    private boolean validateemail(){
        String flag = email.getText().toString();
        String emailformat = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        if (flag.isEmpty()) {
            email.setError("Field cannot be Empty!");
            return false;
        } else if (!flag.matches(emailformat)) {
            email.setError("Incorrect Email ID!");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    private boolean validatepwd() {
        String flag = pwd.getText().toString();

        if (flag.isEmpty()) {
            pwd.setError("Field cannot be Empty!");
            return false;
        } else {
            pwd.setError(null);
            return true;
        }
    }

    public void loginrdrct(View view)
    {
        Intent loginredirect = new Intent(SignUp.this, Login.class);
        startActivity(loginredirect);
    }

    public void onclicksuccsignup(View view)
    {
        if (!validateuname() | !validateemail() | !validatepwd())
        {
            return;
        }

        //Variable fields

        String username = uname.getText().toString();
        String mail = email.getText().toString();
        String password = pwd.getText().toString();

        //Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        //Create object of dataholder
        final DataHolder dbdataholder = new DataHolder(username, mail, password);

        //Create object of Firebase Database
        FirebaseDatabase dbfirebase = FirebaseDatabase.getInstance();

        //Create object of Data Reference
        final DatabaseReference dbreference = dbfirebase.getReference("User");

        mAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    userid = mAuth.getCurrentUser().getUid();
                    dbreference.child(userid).setValue(dbdataholder);

                    uname.setText("");
                    email.setText("");
                    pwd.setText("");

                    Toast.makeText(SignUp.this, "User Sign up Successful!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Login.class));
                }
                else
                {
                    Toast.makeText(SignUp.this, "Process Error!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}