/*
 * Created by Nicanor Orlando.
 * Copyright (c) 7/12/21 09:27.
 */

package example.com.contactos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contacto> contactos;
    private RecyclerView listaContactos;
    int spanCount = 2; // 3 columns
    int spacing = 50; // 50px
    boolean includeEdge = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaContactos = findViewById(R.id.rvContacto);

        //LinearLayoutManager llm = new LinearLayoutManager(this);
        //llm.setOrientation(LinearLayoutManager.VERTICAL);

        GridLayoutManager glm = new GridLayoutManager(this,2);

        listaContactos.setLayoutManager(glm);
        inicializarListaContactos();
        inicializarAdaptador();
        listaContactos.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        /*
        ArrayList<String> nombresContacto = new ArrayList<>();
        for (Contacto contacto: contactos) {
            nombresContacto.add(contacto.getNombre());
        }
        ListView lstContactos = (ListView) findViewById(R.id.lstContactos);
        lstContactos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombresContacto));

        lstContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, DetalleContacto.class);
                intent.putExtra("Contacto", contactos.get(position).getNombre());
                intent.putExtra("Telefono", contactos.get(position).getTelefono());
                intent.putExtra("Email", contactos.get(position).getEmail());
                startActivity(intent);
                finish();
            }
        });
        */
    }
    public void inicializarListaContactos(){
        contactos = new ArrayList<Contacto>();

        contactos.add(new Contacto(R.drawable.gmail, "Nicanor","93924942942", "nicanororlando@gmail.com"));
        contactos.add(new Contacto(R.drawable.phone, "pedrooo","2341414", "pedrocapo@gmail.com"));
        contactos.add(new Contacto(R.drawable.gmail, "roberto","13414132", "robertocarlos@gmail.com"));
        contactos.add(new Contacto(R.drawable.phone, "carlos","1231423", "carlitos@gmail.com"));
        contactos.add(new Contacto(R.drawable.gmail, "menem","55151342", "carlosmenem@gmail.com"));
        contactos.add(new Contacto(R.drawable.phone, "Messi","4131324132", "leomessi@gmail.com"));
    }

    //  Instanciacion de ContactoAdaptador.
    /*  Este adaptador primero va a llamar al layout en el onCreate, despues en el onBind le pasa todos los datos
    de la lista de contactos que el viewHolder esta declarando.  */
    public void inicializarAdaptador(){
        ContactoAdaptador adaptador = new ContactoAdaptador(contactos);
        listaContactos.setAdapter(adaptador);
    }

}