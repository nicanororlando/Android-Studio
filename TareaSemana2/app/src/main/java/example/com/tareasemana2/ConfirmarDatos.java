package example.com.tareasemana2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmarDatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_datos);

        Bundle datos = getIntent().getExtras();

        String nombre       = datos.getString("nombre");
        String fecha        = datos.getString("fecha");
        String telefono     = datos.getString("telefono");
        String email        = datos.getString("email");
        String descripcion  = datos.getString("descripcion");

        TextView tvNombre       = findViewById(R.id.nombre);
        TextView tvFecha        = findViewById(R.id.fecha);
        TextView tvTelefono     = findViewById(R.id.telefono);
        TextView tvEmail        = findViewById(R.id.email);
        TextView tvDescripcion  = findViewById(R.id.descripcion);

        tvNombre.setText(nombre);
        tvFecha.setText(fecha);
        tvTelefono.setText(telefono);
        tvEmail.setText(email);
        tvDescripcion.setText(descripcion);

        Button editar   = findViewById(R.id.editardatos);
        editar.setOnClickListener(v -> {
            Intent intent = new Intent(ConfirmarDatos.this, MainActivity.class);
            startActivity(intent);
        });
    }
}