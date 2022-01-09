package example.com.tareasemana4.MenuOpciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

import example.com.tareasemana4.R;

public class Memoria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoria);
    }

    public void generarArchivo(View view){
        try {
            EditText edtNombre = findViewById(R.id.edtNombre);
            String nombre = edtNombre.getText().toString();

            FileOutputStream outputStream;

            //  En este caso NO se sobreescribe el archivo
            //outputStream = openFileOutput("MiArchive.txt", Context.MODE_PRIVATE);

            //  En este caso se agrega texto al archivo existente.
            outputStream = openFileOutput("MiArchive.txt", Context.MODE_APPEND);

            outputStream.write(nombre.getBytes());      //para que nuestro archivo se escriba mucho mas rapido.
            outputStream.close();                       //importante cerrar el archivo.

            Toast.makeText(Memoria.this, "El archivo se ha creado", Toast.LENGTH_SHORT).show();
            edtNombre.setText("");

        } catch (Exception e){
            e.printStackTrace();                        //Para identificar el error.
            Toast.makeText(Memoria.this, "Error en la escritura de archivo", Toast.LENGTH_SHORT).show();
        }
    }
    public void guardarPreferencias(View view){

        //  Crea un xml
        SharedPreferences sharedPreferences =
                getSharedPreferences("MisDatos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        EditText edtNombre = findViewById(R.id.edtNombre);
        EditText edtCorreo = findViewById(R.id.edtCorreo);
        String nombre = edtNombre.getText().toString();
        String correo = edtCorreo.getText().toString();

        editor.putString("nombre", nombre);
        editor.putString("correo", correo);
        editor.commit();

        Toast.makeText(Memoria.this, "Guardado!", Toast.LENGTH_SHORT).show();
    }
    public void mostrarPreferencias(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("MisDatos", Context.MODE_PRIVATE);

        String nombre = sharedPreferences.getString("nombre", "No existe esta variable");
        String correo = sharedPreferences.getString("correo", "No existe esta variable");

        TextView preferenciaCompartida = findViewById(R.id.preferenciaCompartida);

        String preferencia = "\nNombre: " + nombre + "\nCorreo: " + correo;
        preferenciaCompartida.setText(preferencia);
    }
}