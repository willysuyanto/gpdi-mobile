package com.ilywebhouse.gpdimobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class DetailPelayanan extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pelayanan);

        Bundle bundle = getIntent().getExtras();
        String toolbarTitle = bundle.getString("menu");

        toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle(toolbarTitle);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
    }
}