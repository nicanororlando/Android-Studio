package com.institutomisionero.miscontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetalleContacto extends AppCompatActivity {

    private TextView tvNombre;
    private TextView tvTelefono;
    private TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);

        Bundle parametros = getIntent().getExtras();

        String nombre = parametros.getString("Nombre");
        String telefono = parametros.getString("Telefono");
        String email = parametros.getString("Email");

        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvTelefono = (TextView) findViewById(R.id.tvTelefono);
        tvEmail = (TextView) findViewById(R.id.tvEmail);

        tvNombre.setText(nombre);
        tvTelefono.setText(telefono);
        tvEmail.setText(email);

    }

    public void llamar(View v) {
        String telefono = tvTelefono.getText().toString();

        //Para pasar el n° tengo que pasarlo como un recurso accesible (recurso Uri)
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + telefono)));
    }

    public void enviarMail(View v) {
        String email = tvEmail.getText().toString();
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("malito"));

        //Tambien puedo usar EXTRA_SUBJECT para asunto, o EXTRA_TEXT para definir un cuerpo.
        emailIntent.putExtra(Intent.EXTRA_EMAIL, email);

        //Con esto le digo que me coloque como un chooser todas las apps con email.
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Email"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(DetalleContacto.this, MainActivity.class);
        startActivity(intent);
    }
}