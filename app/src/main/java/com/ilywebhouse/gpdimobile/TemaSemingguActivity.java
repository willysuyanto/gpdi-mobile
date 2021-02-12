package com.ilywebhouse.gpdimobile;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ilywebhouse.gpdimobile.ui.main.SectionsPagerAdapter;

public class TemaSemingguActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema_seminggu);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        Bundle bundle = getIntent().getExtras();
        String toolbarTitle = bundle.getString("menu");
        toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle(toolbarTitle);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
        if(currentUser==null){
            Log.d( "onCreate: ","Belum Login");
        }else{
            Log.d( "onCreate: ",currentUser.getEmail());
            toolbar.inflateMenu(R.menu.toolbar_menu);
            toolbar.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.action_edit:
                        Intent intent = new Intent(TemaSemingguActivity.this, UbahTemaActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.action_settings:
                        //lakukan sesuatu saat opsi settin di tap
                        return true;
                    default:
                        return true;
                }
            });
        }


    }
}