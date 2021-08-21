package com.ilywebhouse.gpdimobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MenuAdapter.OnTitleListener {
    public ArrayList<MenuItem> menuItems = new ArrayList<>();
    public MenuAdapter menuAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null){
            Log.d( "onCreate: ","Belum Login");
            initMenu(false);
        }else{
            Log.d( "onCreate: ",currentUser.getEmail());
            initMenu(true);
        }
        recyclerView = findViewById(R.id.rv_menu);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new MenuAdapter(menuItems,this);
        recyclerView.setAdapter(mAdapter);


    }



    void initMenu(boolean isLoggedIn){
        menuItems.removeAll(menuItems);
        menuItems.add(new MenuItem("Renungan Harian","tema",R.drawable.img_tema));
        menuItems.add(new MenuItem("Jadwal Pelayanan","jadwal",R.drawable.img_jadwal));
        menuItems.add(new MenuItem("Profil Gereja","profil",R.drawable.img_profil));
        menuItems.add(new MenuItem("GPdI News","news",R.drawable.img_news));
        menuItems.add(new MenuItem("Contact Us","contact",R.drawable.contact));
        if(!isLoggedIn){
        menuItems.add(new MenuItem("Login","login",R.drawable.ic_login));
        }else{
            menuItems.add(new MenuItem("Logout","logout",R.drawable.ic_logout));
        }
    }

    @Override
    public void OnTitleClick(int position) {
        Intent intent = new Intent();
        switch (menuItems.get(position).getMenuCode()) {
            case "tema":
                intent = new Intent(this, TemaSemingguActivity.class);
                intent.putExtra("menu", menuItems.get(position).getMenuLabel());
                startActivity(intent);
                break;
            case "jadwal":
                intent = new Intent(this, JadwalPelayananActivity.class);
                intent.putExtra("menu", menuItems.get(position).getMenuLabel());
                intent.putExtra("image", menuItems.get(position).getMenuIcon());
                startActivity(intent);
                break;
            case "profil":
                intent = new Intent(this, ProfilGerejaActivity.class);
                intent.putExtra("menu", menuItems.get(position).getMenuLabel());
                startActivity(intent);
                break;
            case "news":
                intent = new Intent(this, NewsActivity.class);
                intent.putExtra("menu", menuItems.get(position).getMenuLabel());
                startActivity(intent);
                break;
            case "contact":
                intent = new Intent(this, ContactActivity.class);
                intent.putExtra("menu", menuItems.get(position).getMenuLabel());
                startActivity(intent);
                break;
            case "login":
                intent = new Intent(this, LoginScreen.class);
                startActivity(intent);
                break;
            case "logout":
                mAuth.signOut();
                initMenu(false);
                mAdapter.notifyDataSetChanged();
                break;

        }
    }
}