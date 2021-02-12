package com.ilywebhouse.gpdimobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ilywebhouse.gpdimobile.ui.main.Berita;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class NewsActivity extends AppCompatActivity implements NewsAdapter.OnTitleListener{
    public ArrayList<Berita> beritaItems = new ArrayList<>();
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        initBerita();
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
            toolbar.inflateMenu(R.menu.toolbar_menu_news);
            toolbar.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.action_edit:
                        Intent intent = new Intent(NewsActivity.this, TambahBeritaActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.action_settings:
                        return true;
                    default:
                        return true;
                }
            });
        }

        Collections.reverse(beritaItems);
        recyclerView = findViewById(R.id.recBerita);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new NewsAdapter(beritaItems,this);
        recyclerView.setAdapter(mAdapter);
    }


    void initBerita(){
        beritaItems.add(new Berita("Ketum di Sulawesi Selatan 6-8 Okt 2020","09 October 2020"));
        beritaItems.add(new Berita("Kunjungan Ketum ke Jatim 23 Sep 2020","09 October 2020"));
        beritaItems.add(new Berita("Kafiar Di Bengkulu 28-29 Sep 2020.","09 October 2020"));
        beritaItems.add(new Berita("RIP Pdt. Dr. Rudy F. Makal MA, MTh Tgl 2 Okt 2020.","02 October 2020"));
        beritaItems.add(new Berita("Kafiar Di Bengkulu 28-29 Sep 2020.","09 October 2020"));
        beritaItems.add(new Berita("Ketum Resmikan GPdI Anugerah, Sulut, Nop 2020","27 December 2020"));
        beritaItems.add(new Berita("Sambutan Tahun Baru 2021 Dari Ketua Umum MP GPdI","31 December 2020"));
        beritaItems.add(new Berita("Renungan dan Arahan Ketum di Morning Prayer 25 Jan 2021","25 January 2020"));
        beritaItems.add(new Berita("Ibadah ditunda karena lockdown pandemi","09 february 2021"));
        beritaItems.add(new Berita("Perubahan jadwal ibadah tanggal 15 februari","12 february 2021"));
    }

    @Override
    public void OnTitleClick(int position) {

    }
}