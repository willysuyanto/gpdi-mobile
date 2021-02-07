package com.ilywebhouse.gpdimobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MenuAdapter.OnTitleListener {
    public ArrayList<MenuItem> menuItems = new ArrayList<>();
    public MenuAdapter menuAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMenu();
        recyclerView = findViewById(R.id.rv_menu);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new MenuAdapter(menuItems,this);
        recyclerView.setAdapter(mAdapter);
    }

    void initMenu(){
        menuItems.add(new MenuItem("Tema Mingguan","tema",R.drawable.img_tema));
        menuItems.add(new MenuItem("Jadwal Pelayanan","jadwal",R.drawable.img_jadwal));
        menuItems.add(new MenuItem("Profil Gereja","profil",R.drawable.img_profil));
        menuItems.add(new MenuItem("DPdI News","news",R.drawable.img_news));
        menuItems.add(new MenuItem("Contact Us","contact",R.drawable.contact));
    }

    @Override
    public void OnTitleClick(int position) {
        Intent intent = new Intent();
        switch (menuItems.get(position).getMenuCode()) {
            case "tema":
                intent = new Intent(this, TemaSemingguActivity.class);
                intent.putExtra("menu", menuItems.get(position).getMenuLabel());
                break;
            case "jadwal":
                intent = new Intent(this, JadwalPelayananActivity.class);
                intent.putExtra("menu", menuItems.get(position).getMenuLabel());
                intent.putExtra("image", menuItems.get(position).getMenuIcon());
                break;
            case "profil":
                intent = new Intent(this, ProfilGerejaActivity.class);
                intent.putExtra("menu", menuItems.get(position).getMenuLabel());
                break;
            case "news":
                intent = new Intent(this, NewsActivity.class);
                intent.putExtra("menu", menuItems.get(position).getMenuLabel());
                break;
            case "contact":
                intent = new Intent(this, ContactActivity.class);
                intent.putExtra("menu", menuItems.get(position).getMenuLabel());
                break;
        }
        startActivity(intent);
    }
}