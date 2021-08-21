package com.ilywebhouse.gpdimobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddPeserta extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    Spinner spinner;
    ArrayList<String> option = new ArrayList<>();
    EditText editText;
    Button button;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_peserta);

        FirebaseFirestore fs = FirebaseFirestore.getInstance();

        spinner = findViewById(R.id.spnPeserta);
        editText = findViewById(R.id.et_nama);
        button = findViewById(R.id.btn_simpan);

        toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("Form Tambah Data Peserta");
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        option.add("Pilih Bagian");
        option.add("Music");
        option.add("Praise and Worship");
        option.add("Rebana and Banners");
        option.add("Choir");
        option.add("Multimedia");
        option.add("Sound System");

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, option);
        spinner.setAdapter(adapter);

        button.setOnClickListener(v -> {
            String nama = editText.getText().toString();
            String bagian = spinner.getSelectedItem().toString();
            if(nama.equals("")){
                Toast.makeText(getApplicationContext(), "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }else if(bagian.equals("Pilih Bagian")){
                Toast.makeText(getApplicationContext(), "Pilih Bagian terlebih dahulu", Toast.LENGTH_SHORT).show();
            }else {
                Map<String, Object> data = new HashMap<>();
                data.put("nama", nama);
                data.put("bagian", bagian);
                fs.collection("Peserta").add(data).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Berhasil Simpan Data", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
            }
        });

    }
}