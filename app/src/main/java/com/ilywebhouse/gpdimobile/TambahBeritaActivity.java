package com.ilywebhouse.gpdimobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class TambahBeritaActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_berita);

        toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("Tambah Berita");
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
    }
}