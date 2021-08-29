package com.ilywebhouse.gpdimobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity {

    private Toolbar toolbar;
    CardView cvTelepon, cvEmail, cvFacebook, cvInstagram, cvLokasi;
    TextView txTelepon, txEmail, txFacebook, txInstagram, txLokasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        cvTelepon = findViewById(R.id.cvTelepon);
        cvEmail = findViewById(R.id.cvEmail);
        cvFacebook = findViewById(R.id.cvFacebook);
        cvInstagram = findViewById(R.id.cvInstagram);
        cvLokasi = findViewById(R.id.cvLokasi);

        txTelepon = findViewById(R.id.telepon);
        txEmail = findViewById(R.id.telepon);
        txFacebook = findViewById(R.id.facebook);
        txInstagram = findViewById(R.id.instagram);
        txLokasi = findViewById(R.id.lokasi);


        Bundle bundle = getIntent().getExtras();
        String toolbarTitle = bundle.getString("menu");
        toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle(toolbarTitle);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        String telepon = txTelepon.getText().toString();
        String email = txEmail.getText().toString();
        String facebook = txFacebook.getText().toString();
        String instagram = txInstagram.getText().toString();
        String lokasi = txLokasi.getText().toString();


        cvTelepon.setOnClickListener(v -> {
            copyToClipboard(telepon);
        });

        cvEmail.setOnClickListener(v -> {
            copyToClipboard(email);
        });

        cvFacebook.setOnClickListener(v -> {
            copyToClipboard(facebook);
        });

        cvInstagram.setOnClickListener(v -> {
            copyToClipboard(instagram);
        });

        cvLokasi.setOnClickListener(v -> {
            copyToClipboard(lokasi);
        });


    }

    public void copyToClipboard(String copyText) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager)
                    getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(copyText);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager)
                    getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData
                    .newPlainText("Salinan", copyText);
            clipboard.setPrimaryClip(clip);
        }
        Toast toast = Toast.makeText(getApplicationContext(),
                "Text disalin ke Clipboard", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 50, 50);
        toast.show();
    }
}