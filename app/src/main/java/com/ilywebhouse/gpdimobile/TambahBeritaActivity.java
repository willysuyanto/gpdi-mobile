package com.ilywebhouse.gpdimobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ilywebhouse.gpdimobile.ui.main.Berita;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TambahBeritaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    EditText etJudul, etKonten;
    Button btnPublish;
    FirebaseFirestore fs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_berita);

        fs = FirebaseFirestore.getInstance();

        etJudul = findViewById(R.id.et_title);
        etKonten = findViewById(R.id.et_berita);
        btnPublish = findViewById(R.id.btn_simpan);

        toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("Tambah Berita");
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judul = etJudul.getText().toString();
                String konten = etKonten.getText().toString();
                if(judul.equals("")){
                    toaster("Judul Berita tidak boleh kosong. ");
                }else if(konten.equals("")){
                    toaster("Konten Berita tidak boleh kosong. ");
                }else{
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
                    String date = dateFormat.format(calendar.getTime());
                    Map<String, Object> data = new HashMap<>();
                    data.put("judul", judul);
                    data.put("konten", konten);
                    data.put("tanggal", date);
                    fs.collection("Berita").add(data).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if(task.isSuccessful()){
                                toaster("Berita berhasil dipublish");
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        finish();
                                    }
                                },1000);
                            }
                        }
                    });
                }
            }
        });
    }

    void toaster(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}