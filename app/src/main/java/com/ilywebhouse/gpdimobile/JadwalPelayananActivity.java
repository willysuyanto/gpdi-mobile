package com.ilywebhouse.gpdimobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class JadwalPelayananActivity extends AppCompatActivity implements SubMenuAdapter.OnTitleListener {

    public ArrayList<SubMenuItem> subMenuItems = new ArrayList<>();
    public SubMenuAdapter subMenuAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_pelayanan);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        initMenu();

        recyclerView = findViewById(R.id.rv_submenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SubMenuAdapter(subMenuItems,this);
        recyclerView.setAdapter(mAdapter);

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
            toolbar.inflateMenu(R.menu.toolbar_menu_jadwal);
            toolbar.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.action_edit:
                        Intent intent = new Intent(JadwalPelayananActivity.this, KelolaJadwal.class);
                        intent.putExtra("menu", "Kelola Jadwal");
                        startActivity(intent);
                        return true;
                    default:
                        return true;
                }
            });
        }


    }

    void initMenu(){
        subMenuItems.add(new SubMenuItem("Music","",R.drawable.music));
        subMenuItems.add(new SubMenuItem("Praise and Worship","",R.drawable.music));
        subMenuItems.add(new SubMenuItem("Rebana and Banners","",R.drawable.music));
        subMenuItems.add(new SubMenuItem("Choir","",R.drawable.music));
        subMenuItems.add(new SubMenuItem("Multimedia","",R.drawable.music));
        subMenuItems.add(new SubMenuItem("Sound System","",R.drawable.music));
    }

    @Override
    public void OnTitleClick(int position) {
        Intent intent = new Intent(JadwalPelayananActivity.this, DetailPelayanan.class);
        intent.putExtra("menu", subMenuItems.get(position).getTitle());
        startActivity(intent);
    }
}
