package example.com.tareasemana2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextTextPersonName = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText editTextDate = (EditText) findViewById(R.id.editTextDate);
        EditText editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        EditText editTextTextEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
        EditText editTextLongMessage = (EditText) findViewById(R.id.editTextLongMessage);

        // Date
        editTextDate.setOnClickListener(v -> {
            showDatePickerDialog(editTextDate);
        });

        // Siguiente
        Button boton = findViewById(R.id.boton);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConfirmarDatos.class);
                intent.putExtra("nombre", editTextTextPersonName.getText().toString());
                intent.putExtra("fecha", editTextDate.getText().toString());
                intent.putExtra("telefono", editTextPhone.getText().toString());
                intent.putExtra("email", editTextTextEmailAddress.getText().toString());
                intent.putExtra("descripcion", editTextLongMessage.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void showDatePickerDialog(final EditText editText) {
        DatePicker newFragment = DatePicker.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                final String selectedDate = twoDigits(dayOfMonth) + "/" + twoDigits(month+1) + "/" + year;
                editText.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }
}