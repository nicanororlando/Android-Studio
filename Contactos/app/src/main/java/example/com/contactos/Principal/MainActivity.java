/*
 * Created by Nicanor Orlando.
 * Copyright (c) 7/12/21 09:27.
 */

package example.com.contactos.Principal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import example.com.contactos.Mas.About;
import example.com.contactos.R;

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

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        setTitle("Contactos");

        TextView nombreClick = findViewById(R.id.nombreClick);
        registerForContextMenu(nombreClick);    //Dejamos disponible el textView para levantar un meu de contexto.

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
        contactos = new ArrayList<>();

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
        ContactoAdaptador adaptador = new ContactoAdaptador(contactos, this);
        listaContactos.setAdapter(adaptador);
    }

    //  MENU DE OPCIONES:
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mAbout:
                Intent intent = new Intent(this, About.class);
                startActivity(intent);
                break;
            case R.id.mSettings:
                Intent i = new Intent(this, About.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //  MENU DE CONTEXTO:
    //  Para cuando mantengo presionado un boton, texto, o lo q sea.
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contexto, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        //UTILIZAMOS LA MISMA LOGICA QUE USAMOS EN EL MENU DE OPCIONES.

        switch (item.getItemId()){
            case R.id.mEdit:
                Toast.makeText(this, getResources().getString(R.string.menu_edit), Toast.LENGTH_SHORT).show();
                break;
            case R.id.mDelete:
                Toast.makeText(this, getResources().getString(R.string.menu_delete), Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onContextItemSelected(item);
    }
    public void levantarMenuPopUp(View v){
        ImageView imagen = findViewById(R.id.imagePopUp);
        PopupMenu popupMenu = new PopupMenu(this, imagen);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mView:

                        //Como estoy dentro de una interfaz, mi contexto es "getBaseContext()"
                        Toast.makeText(getBaseContext(), getResources().getString(R.string.menu_about), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mViewDetail:
                        Toast.makeText(getBaseContext(), getResources().getString(R.string.menu_settings), Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        popupMenu.show();
    }

}