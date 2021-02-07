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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ilywebhouse.gpdimobile.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public TextView textTema;
    public TextView textAyat;
    public String hariterpilih;


    public static PlaceholderFragment newInstance(int index, String hari) {
        Log.d( "newInstance: ",hari.toLowerCase());
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
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
        DatabaseReference refs = database.getReference("tema");
        return root;
    }
}