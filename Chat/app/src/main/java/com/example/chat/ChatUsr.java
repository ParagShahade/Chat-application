package com.example.chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatUsr extends AppCompatActivity {
    int position = 0;
    FloatingActionButton sndbtn;

    FirebaseUser mfirebaseusr;
    DatabaseReference mdatabaseref;
    FirebaseDatabase firebaseDatabase;
    EditText editText;
    FirebaseAuth mfirebaseauth;
    ArrayList<String> arylst=new ArrayList<>();
    Message message;
    ArrayAdapter<String> myarrayadapter;
    TextView message_txt,msg_Usr,una;
    ListView lv;
    InfoUsr i;
    ArrayList<String> myarylst=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_usr);
        message=new Message();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sndbtn= findViewById(R.id.fbtn_snd);
        editText=findViewById(R.id.type_msg);
        una=findViewById(R.id.disp_username);
        myarrayadapter = new ArrayAdapter<String>(this,R.layout.info,R.id.txt_msg,arylst);
        message_txt=(TextView) findViewById(R.id.txt_msg);
        msg_Usr=(TextView) findViewById(R.id.u_msg);
        lv=findViewById(R.id.lst);
        mfirebaseusr=FirebaseAuth.getInstance().getCurrentUser();
        mdatabaseref=FirebaseDatabase.getInstance().getReference("InfoUsr");
        mdatabaseref=FirebaseDatabase.getInstance().getReference().child("Message");

        Intent i = getIntent();
        String product= i.getStringExtra("listviewvalue");
        una.setText(product);

        sndbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ms=editText.getText().toString();
                if(!ms.equals("")){
                    firebaseDatabase.getInstance().getReference().child("Message").push().setValue(new Message(editText.getText().toString(),mfirebaseauth.getInstance().getCurrentUser().getEmail()));
                    Toast.makeText(ChatUsr.this, "Message sent", Toast.LENGTH_LONG).show();
                    editText.setText("");
                }
                else
                {
                    Toast.makeText(ChatUsr.this, "Please enter Message ", Toast.LENGTH_LONG).show();
                }
            }
        });
        mdatabaseref=FirebaseDatabase.getInstance().getReference().child("Message");

        mdatabaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               // Map <String,Object> map = (HashMap<String, Object>) dataSnapshot.getValue();
                String u = null;
                for (DataSnapshot d:dataSnapshot.getChildren()){

                    u = d.child("msgTxt").getValue().toString();
                    Log.d("hello", "onDataChange: "+u);
                    message.setMsgTxt(u);
                    arylst.add(message.getMsgTxt());
                }
                lv.setAdapter(myarrayadapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ChatUsr.this,LoginApps.class));
                finish();
                return true;
        }
        return false;
    }
}
