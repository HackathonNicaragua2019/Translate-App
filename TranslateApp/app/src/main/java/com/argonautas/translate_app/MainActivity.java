package com.argonautas.translate_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //declaracion de variables para los spinners
    int id1ant, id2ant;
    Spinner spinner1;
    Spinner spinner2;
    String[] idiomas;

    Button btn1, btn2;

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private EditText mEntradaVoz;
    private Button mBotonHablar;

    private EditText miCaja;
    private TextView miSalida;
    private Button botonMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //refencia a los id de los spinnes y el boton para cambiar los idiomas
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);

        //referencia a boton para intercambiar los idiomas
        btn1 = findViewById(R.id.btn_cambiar);

        //le decimos al spinner 2 que muestre el elemento 2 del array
        spinner2.setSelection(1);

        //asignamos a las variables la posiciones de los elementos en los spinners
        id1ant = spinner1.getSelectedItemPosition();
        id2ant = spinner2.getSelectedItemPosition();

        //hacemos una refencia a nuestro array de idiomas
        idiomas = getResources().getStringArray(R.array.idiomas);

        //para hacer refencia al metodo para trabajar con la posicion de los elementos del array
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);

        //referencia al edittext
        mEntradaVoz = findViewById(R.id.et_input);

        //referencia al boton del microfono
        mBotonHablar = findViewById(R.id.btn_micro);

        //para cuando se preciona el boton del microfono
        mBotonHablar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarEntradaVoz();
            }
        });

        //asigna a una variable lo que tenga el edittext
        miCaja = mEntradaVoz;

        //referencia al textview donde se mostrara las traducciones y al boton para traducir
        miSalida = findViewById(R.id.tv_Salida);
        botonMostrar = findViewById(R.id.btnTraducir);

        //para cuando se preciona el boton traducir
        botonMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String miNombre = null;

                miNombre = miCaja.getText().toString();
                miSalida.setText(miNombre);
            }
        });


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //para que el usuario no pueda seleccionar el mismo idioma
        //en cada case hacemos el intercambio de los elementos
        switch (parent.getId()) {
            case R.id.spinner1:
                if (position == spinner2.getSelectedItemPosition()) {
                    spinner2.setSelection(id1ant);
                }
                id1ant = spinner1.getSelectedItemPosition();

            case R.id.spinner2:
                if (position == spinner1.getSelectedItemPosition()) {
                    spinner1.setSelection(id2ant);
                }
                id2ant = spinner2.getSelectedItemPosition();
                break;

        }
        //para que el boton de intercambiar los idiomas
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cambiar1;
                int cambiar2;

                cambiar1 = spinner1.getSelectedItemPosition();
                cambiar2 = spinner2.getSelectedItemPosition();

                spinner2.setSelection(cambiar1);
                spinner1.setSelection(cambiar2);
            }
        });

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

//funciones para poder activar el microfono y poder escribir en el edittext lo capturado
    private void iniciarEntradaVoz() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Escuchando");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException e) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mEntradaVoz.setText(result.get(0));
                }
                break;
            }
        }
    }
}