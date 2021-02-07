package com.ilywebhouse.gpdimobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

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
        Toast.makeText(this,"Dalam Pengembangan", Toast.LENGTH_SHORT).show();
    }
}
