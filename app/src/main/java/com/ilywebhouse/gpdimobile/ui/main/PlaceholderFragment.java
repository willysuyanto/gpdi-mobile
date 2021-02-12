package com.ilywebhouse.gpdimobile.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ilywebhouse.gpdimobile.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_SECTION_DAY = "section_day";


    public TextView textTema;
    public TextView textAyat;


    public static PlaceholderFragment newInstance(int index, String hari) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        bundle.putString(ARG_SECTION_DAY, hari);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tema_seminggu, container, false);
        textTema = root.findViewById(R.id.section_label_tema);
        textAyat = root.findViewById(R.id.section_label_ayat);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Bundle bundle = getArguments();
        int index = bundle.getInt(ARG_SECTION_NUMBER);
        String hari = bundle.getString(ARG_SECTION_DAY,"");
        Log.d("onCreateView: ", hari+index);
        DatabaseReference refs = database.getReference("tema");
        refs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d( "onDataChange: ", dataSnapshot.child(hari.toLowerCase()).toString());
                textTema.setText(dataSnapshot.child(hari.toLowerCase()).child("tema").getValue(String.class));
                textAyat.setText(dataSnapshot.child(hari.toLowerCase()).child("ayat").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return root;
    }
}