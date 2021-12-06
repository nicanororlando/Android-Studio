package example.com.contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contacto> contactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactos = new ArrayList<Contacto>();
        contactos.add(new Contacto("Nicanor","93924942942", "nicanororlando@gmail.com"));
        contactos.add(new Contacto("pedrooo","2341414", "pedrocapo@gmail.com"));
        contactos.add(new Contacto("roberto","13414132", "robertocarlos@gmail.com"));
        contactos.add(new Contacto("carlos","1231423", "carlitos@gmail.com"));
        contactos.add(new Contacto("menem","55151342", "carlosmenem@gmail.com"));
        contactos.add(new Contacto("Messi","4131324132", "leomessi@gmail.com"));

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
    }
}