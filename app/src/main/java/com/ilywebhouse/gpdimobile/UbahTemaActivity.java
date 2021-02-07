package com.ilywebhouse.gpdimobile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UbahTemaActivity extends AppCompatActivity {

    public Spinner spinner;
    public String[] items;

    private Toolbar toolbar;
    public EditText editTextTema;
    public EditText editTextAyat;
    public Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_tema);
        initDropdownHari();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference tema = database.getReference("tema");

        editTextTema = findViewById(R.id.et_tema);
        editTextAyat = findViewById(R.id.et_ayat);

        toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("Ubah Tema Mingguan");
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        buttonSubmit = findViewById(R.id.btn_simpan);
        buttonSubmit.setOnClickListener(v -> {
            if(editTextTema.getText().toString().equals("")){
                Toast.makeText(this,"Tema Tidak Boleh Kosong", Toast.LENGTH_SHORT);
            }if(editTextAyat.getText().toString().equals("")){
                Toast.makeText(this,"Ayat Tidak Boleh Kosong", Toast.LENGTH_SHORT);
            }else{
                tema.child(spinner.getSelectedItem().toString().toLowerCase()).child("tema").setValue(editTextTema.getText().toString());
                tema.child(spinner.getSelectedItem().toString().toLowerCase()).child("ayat").setValue(editTextAyat.getText().toString(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        if (databaseError==null){
                            Toast.makeText(getApplicationContext(),"Berhasil Ubah Tema Hari "+spinner.getSelectedItem().toString()+".",Toast.LENGTH_SHORT).show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            },2000);
                        } else{
                            Toast.makeText(getApplicationContext(),"Gagal Update Data ",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    void initDropdownHari(){
        items = new String[]{"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"};
        spinner = findViewById(R.id.sp_hari);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
    }
}