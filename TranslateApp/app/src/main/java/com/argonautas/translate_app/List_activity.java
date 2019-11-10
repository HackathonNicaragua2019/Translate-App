package com.argonautas.translate_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class List_activity extends AppCompatActivity {
    ImageView imagevl;
    TextView textvl;
    TextView textidioma;
    TextView tvdescript;
    String lugarest;
    String descript;
    String idioma;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listactivity);

        imagevl = findViewById(R.id.iv_lugs);
        textvl = findViewById(R.id.tv_lugs);
        textidioma = findViewById(R.id.tv_idiomas);
        tvdescript = findViewById(R.id.tv_description);



        Intent intent= getIntent();
        int lugspic = intent.getIntExtra("Preview",0);
        lugarest = intent.getStringExtra("Lugar");
        idioma = intent.getStringExtra("idioma");
        descript = intent.getStringExtra("Descripcion");



        imagevl.setImageResource(lugspic);
        textvl.setText(lugarest);
        textidioma.setText(idioma);
        tvdescript.setText(descript);

    }

}


