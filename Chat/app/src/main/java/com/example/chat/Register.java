package com.example.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    EditText  txt_email, txt_passwd,txt_conf,txt_username;
    Button btn_register;
    private String   userId;
    private DatabaseReference mFireBasedatabase;
    private FirebaseDatabase mFirebaseinstance;
    InfoUsr infoUsr;
    FirebaseAuth mFirebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Your Register");

        txt_email = (EditText) findViewById(R.id.email);
        txt_passwd = (EditText) findViewById(R.id.password);
        txt_conf=(EditText)findViewById(R.id.confirm_pass);
        txt_username=(EditText)findViewById(R.id.uname);
        btn_register = (Button) findViewById(R.id.Reg);
        mFirebaseauth=FirebaseAuth.getInstance();
         infoUsr = new InfoUsr();
        mFireBasedatabase=FirebaseDatabase.getInstance().getReference().child("InfoUsr");

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=txt_username.getText().toString().trim();
                 String email = txt_email.getText().toString().trim();
                String pass = txt_passwd.getText().toString().trim();
                String confpass=txt_conf.getText().toString().trim();

                infoUsr.setUsremail(txt_email.getText().toString().trim());
                infoUsr.setUsrname(txt_username.getText().toString().trim());
                infoUsr.setUsrpass(txt_passwd.getText().toString().trim());
                mFireBasedatabase.push().setValue(infoUsr);

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(Register.this, "Please enter your name", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Register.this, "Please enter your email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(Register.this, "Please enter your  Password", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(confpass)) {
                    Toast.makeText(Register.this, "Please enter your Confirm password", Toast.LENGTH_LONG).show();
                    return;
                }
                if (pass.length()<8){
                    Toast.makeText(Register.this,"Password Short",Toast.LENGTH_LONG).show();
                    return;

                }

                if (pass.equals(confpass)){
                    mFirebaseauth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        startActivity(new Intent(getApplicationContext(),LoginApps.class));
                                        Toast.makeText(Register.this,"Successfully Register",Toast.LENGTH_LONG).show();
                                        return;

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Register.this,"Registration failure",Toast.LENGTH_LONG).show();
                                        return;

                                    }

                                }
                            });
                }


            }
        });


    }


}
