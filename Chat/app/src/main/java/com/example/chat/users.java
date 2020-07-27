package com.example.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class users extends AppCompatActivity {
    ListView list;
    ArrayList<String> myarylst=new ArrayList<>();
    private DatabaseReference mDatabase;
    FirebaseUser mfirebaseauth;
    InfoUsr usr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        final ArrayAdapter<String> myarrayadapter = new ArrayAdapter<String>(this,R.layout.user_list,R.id.username,myarylst);
        usr= new InfoUsr();
        list=(ListView)findViewById(R.id.list_msg);
        list.setAdapter(myarrayadapter);
        mfirebaseauth=FirebaseAuth.getInstance().getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("InfoUsr");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot d:dataSnapshot.getChildren()){
                    usr =d.getValue(InfoUsr.class);

                    if (!usr.getUsremail().equals(mfirebaseauth.getEmail())){
                        myarylst.add(usr.getUsrname());
                    }

                }
                list.setAdapter(myarrayadapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String tmplistview= myarylst.get(position);
                Intent i= new Intent(getApplicationContext(),ChatUsr.class);
                i.putExtra("listviewvalue", tmplistview);
                startActivity(i);
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
                startActivity(new Intent(users.this,LoginApps.class));
                finish();
                return true;
        }
        return false;
    }
}