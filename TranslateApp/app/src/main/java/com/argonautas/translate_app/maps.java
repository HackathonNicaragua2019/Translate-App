package com.argonautas.translate_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class maps extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;//1
    ListView listView1;
    Button bt_mayagna, bt_miskito;
    FirebaseFirestore db;


    String[] lugarest=new  String[200];
    String[] idiomas= new String[200];
    String[] descript= new String[200];


    int[] lugpics= {R.drawable.common_google_signin_btn_icon_dark_normal,R.drawable.common_google_signin_btn_icon_dark,R.drawable.common_google_signin_btn_icon_dark_normal,R.drawable.common_google_signin_btn_icon_dark, R.drawable.common_google_signin_btn_icon_dark_normal};

    public maps() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        // assert mapFragment != null;
        mapFragment.getMapAsync(this);

        db = FirebaseFirestore.getInstance();
        listView1 = findViewById(R.id.lv_lugares);
        /*MainAdapter adapter = new MainAdapter(maps.this,lugarest,lugpics,idiomas,descript);//(maps.this,lugarest,lugpics,descripts);
        listView1.setAdapter(adapter);*/

        db.collection("Lugares")
                .get()//obtenemos los documentos de la coleccion
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i=0;
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                lugarest[i] =  document.getData().get("Lugar_t").toString();
                                descript[i]=  document.getData().get("descripcion").toString();
                                idiomas[i]=  document.getData().get("idioma").toString();
                                Log.e("idioma1",idiomas[i]);

                                i++;
                            }
                            MainAdapter adapter = new MainAdapter(maps.this,lugarest,lugpics,idiomas,descript);
                            listView1.setAdapter(adapter);

                        } else {
                            Log.w("migna", "Error getting documents.", task.getException());
                        }
                    }
                });



        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentlug = new Intent(maps.this, List_activity.class);

                intentlug.putExtra("preview",lugpics[position]);
                intentlug.putExtra("Lugar",lugarest[position]);
                intentlug.putExtra("idiomas",idiomas[position]);
                intentlug.putExtra("descripcion",descript[position]);
            }
        });
        //Asignacion a los botones
        bt_mayagna= (Button) findViewById(R.id.bt_mayagna);
        bt_miskito= (Button) findViewById(R.id.bt_miskito);





        //Boton a otra vista





        bt_miskito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(maps.this, Miskito_activity.class));
            }
        });




    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map= googleMap;

        LatLng Lagunaazul = new LatLng(13.922853, -84.387067);
        LatLng Cerro = new LatLng(14.152207, -84.459161);
        LatLng Musawas =new LatLng(14.150873, -84.706840);
        map.addMarker(new MarkerOptions().position(Lagunaazul).title("Laguna Azul"));
        map.addMarker(new MarkerOptions().position(Cerro).title("Cerro Cola Blanca"));
        map.addMarker(new MarkerOptions().position(Musawas).title("Capital de Comunidades Mayagnas"));


        map.moveCamera(CameraUpdateFactory.newLatLng(Lagunaazul));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(Cerro,9));//para visualizar en el mapa el lugar y la distancia a la que verlo
    }
}

