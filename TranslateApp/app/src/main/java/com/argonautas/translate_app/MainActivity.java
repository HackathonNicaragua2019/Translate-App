package com.argonautas.translate_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //declaracion de variables para los spinners
    int id1ant, id2ant;
    Spinner spinner1;
    Spinner spinner2;
    String [] idiomas;

    Button btn1, btn2;
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
}