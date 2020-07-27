package com.example.chat;

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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginApps extends AppCompatActivity {

    EditText ed_mail,ed_pass;
    Button login;
    FirebaseAuth mfirebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_apps);

        ed_mail=findViewById(R.id.t_mail);
        ed_pass=findViewById(R.id.t_password);
        login =findViewById(R.id.bt_login);
        mfirebaseauth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ed_mail.getText().toString().trim();
                String pass = ed_pass.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginApps.this, "Please enter your email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(LoginApps.this, "Please enter your Password", Toast.LENGTH_LONG).show();
                    return;
                }
                if (pass.length()<6){
                    Toast.makeText(LoginApps.this,"Password too Short",Toast.LENGTH_LONG).show();
                    return;

                }
                mfirebaseauth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(LoginApps.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    startActivity(new Intent(getApplicationContext(),users.class));
                                    Toast.makeText(LoginApps.this, "Authentication Successfully.",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(LoginApps.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }


                            }
                        });

            }
        });
    }
    public void registerform(View view) {
        startActivity(new Intent(getApplicationContext(),Register.class));
    }
}
