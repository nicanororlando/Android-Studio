package example.com.tareasemana4.MenuOpciones;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import example.com.tareasemana4.MainActivity.MainActivity;
import example.com.tareasemana4.R;

public class Settings extends AppCompatActivity {

    private static final int CODIGO_SOLICITUD_PERMISO = 1;
    private static final int CODIGO_SOLICITUD_HABILITAR_BLUETOOTH = 0;
    private Context context;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        context = getApplicationContext();
        activity = this;
    }

    public void habilitarBluetooth(View v){
        solicitarPermiso();
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        //  Checkear si nuestro disp tiene bluetooth incluido.
        if (bluetoothAdapter == null){
            Toast.makeText(this, "Tu dispositivo no tiene Bluetooth", Toast.LENGTH_SHORT).show();
        }
        //  Checkear si esta activo.
        if (!bluetoothAdapter.isEnabled()){
            Intent habilitarBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(habilitarBluetoothIntent, CODIGO_SOLICITUD_HABILITAR_BLUETOOTH);
        }
    }

    //  Para verificar si ya se ha dado el permiso.
    public boolean checkStatusPermiso(){

        //Manifest de android, no de java.
        int resultado = ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH);
        if (resultado == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else {
            return false;
        }
    }

    public void solicitarPermiso(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.BLUETOOTH)){
            Toast.makeText(this, "El permiso ya fue otorgado", Toast.LENGTH_SHORT).show();
        }
        else {
            ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.BLUETOOTH}, CODIGO_SOLICITUD_PERMISO);
        }
    }

    //  Para gestionar el permiso que otorgamos.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CODIGO_SOLICITUD_PERMISO:
                if (checkStatusPermiso()) {
                    Toast.makeText(this, "Ya esta activo el permiso para Boluetooth", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, "No esta activo el permiso para Boluetooth", Toast.LENGTH_SHORT).show();
                }

        }
    }
}