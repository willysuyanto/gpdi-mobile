package com.ilywebhouse.gpdimobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DetailPelayanan extends AppCompatActivity {

    private Toolbar toolbar;
    FirebaseFirestore fs;
    String menu;

    List<String> week1 = new ArrayList<>();
    List<String> week2 = new ArrayList<>();
    List<String> week3 = new ArrayList<>();
    List<String> week4 = new ArrayList<>();
    List<String> week5 = new ArrayList<>();
    List<String> week6 = new ArrayList<>();

    TextView tvWeek1,tvWeek2,tvWeek3,tvWeek4,tvWeek5,tvWeek6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pelayanan);

        fs = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        menu = bundle.getString("menu");

        tvWeek1 = findViewById(R.id.week1);
        tvWeek2 = findViewById(R.id.week2);
        tvWeek3 = findViewById(R.id.week3);
        tvWeek4 = findViewById(R.id.week4);
        tvWeek5 = findViewById(R.id.week5);
        tvWeek6 = findViewById(R.id.week6);

        toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle(menu);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d( "onResume: ", menu);
        fs.collection("jadwal").document("assign").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    task.getResult().getData().forEach((k,v)->{
                       HashMap ds = (HashMap) task.getResult().get(k);
                        ds.forEach((key,val)->{
                            if(k.equals(menu)){
                                Log.d(key.toString(),val.toString());
                                HashMap map = (HashMap) val;
                                map.forEach((ke,va)->{
                                    Log.d(ke.toString(),va.toString());
                                    if (ke.toString().equals("d1")){
                                        if(va.toString().equals("Masuk")){
                                            Log.d("Added to Week1: ", key.toString());
                                            week1.add(key.toString());
                                        }
                                        Log.d("Week1 ", week1.toString());
                                    }else if (ke.toString().equals("d2")){
                                        if(va.toString().equals("Masuk")){
                                            Log.d("Added to Week2: ", key.toString());
                                            week2.add(key.toString());
                                        }
                                        Log.d("Week2 ", week2.toString());
                                    }else if (ke.toString().equals("d3")){
                                        if(va.toString().equals("Masuk")){
                                            Log.d("Added to Week3: ", key.toString());
                                            week3.add(key.toString());
                                        }
                                        Log.d("Week3 ", week3.toString());
                                    }else if (ke.toString().equals("d4")){
                                        if(va.toString().equals("Masuk")){
                                            Log.d("Added to Week4: ", key.toString());
                                            week4.add(key.toString());
                                        }
                                        Log.d("Week4 ", week4.toString());
                                    }else if (ke.toString().equals("d5")){
                                        if(va.toString().equals("Masuk")){
                                            Log.d("Added to Week5: ", key.toString());
                                            week5.add(key.toString());
                                        }
                                        Log.d("Week5 ", week5.toString());
                                    }else if (ke.toString().equals("d6")){
                                        if(va.toString().equals("Masuk")){
                                            Log.d("Added to Week6: ", key.toString());
                                            week6.add(key.toString());
                                        }
                                        Log.d("Week6 ", week6.toString());
                                    }
                                });
                            }
                        });
                    });
                }
                tvWeek1.setText(getString(week1));
                tvWeek2.setText(getString(week2));
                tvWeek3.setText(getString(week3));
                tvWeek4.setText(getString(week4));
                tvWeek5.setText(getString(week5));
                tvWeek6.setText(getString(week6));
            }
        });

    }

    String getString(List<String> list){
        String result;
        result = list.stream().collect(Collectors.joining(", "));
        return result;
    }
}