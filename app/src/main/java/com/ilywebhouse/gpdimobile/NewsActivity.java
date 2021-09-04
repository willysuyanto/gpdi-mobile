package com.ilywebhouse.gpdimobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
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
    FirebaseFirestore fs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        fs = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
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

    void showDialog(Berita berita){
        Dialog dialog = new Dialog(NewsActivity.this);
        dialog.setContentView(R.layout.news_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.news_bg);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        lp.height = (int)(getResources().getDisplayMetrics().heightPixels*0.80);;
        dialog.getWindow().setAttributes(lp);
        TextView judul = dialog.findViewById(R.id.judul);
        TextView tanggal = dialog.findViewById(R.id.tanggal);
        TextView konten = dialog.findViewById(R.id.konten);

        judul.setText(berita.getJudulBerita());
        tanggal.setText(berita.getTanggal());
        konten.setText(berita.getKontenBerita());

        dialog.show();
    }

    @Override
    public void OnTitleClick(int position) {
        showDialog(beritaItems.get(position));
    }

    @Override
    protected void onResume() {
        super.onResume();

        fs.collection("Berita").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    beritaItems.clear();
                    for (DocumentSnapshot ds:
                         task.getResult()) {
                        beritaItems.add(new Berita(ds.getString("judul"), ds.getString("tanggal"), ds.getString("konten")));
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}