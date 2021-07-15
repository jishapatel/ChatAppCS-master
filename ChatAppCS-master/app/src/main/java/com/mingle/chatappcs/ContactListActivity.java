package com.mingle.chatappcs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mingle.chatappcs.Adapter.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ContactListActivity extends AppCompatActivity {

    com.getbase.floatingactionbutton.FloatingActionButton logout;
    TabLayout tl;
    ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        logout = findViewById(R.id.Fab_Action_Logout);
        tl = findViewById(R.id.tablayout);
        vp = findViewById(R.id.viewpager);
        vp.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        tl.setupWithViewPager(vp);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ContactListActivity.this,LoginActivity.class));
                Toast.makeText(ContactListActivity.this, "LogOut Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}