package example.com.tareasemana4.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import example.com.tareasemana4.Pojo.Mascota;
import example.com.tareasemana4.R;

//  Clase "interactor", es como una clase intermedia que sirve como intermediario con la clase que consulta los datos.
public class ConstructorMascotas {

    private static final int LIKE = 1;
    Context context;

    public ConstructorMascotas(Context context){
        this.context = context;
    }

    //  Primero se rellena los datos Domi
    public ArrayList<Mascota> obtenerDatos(){
        /*
        ArrayList<Mascotas> mascotas = new ArrayList<>();
        mascotas.add(new Mascotas(R.drawable.gmail, "peron", false, 5));
        mascotas.add(new Mascotas(R.drawable.phone, "pedrooo", false, 3));
        mascotas.add(new Mascotas(R.drawable.gmail, "roberto", false, 2));
        mascotas.add(new Mascotas(R.drawable.phone, "carlos", false, 25));
        mascotas.add(new Mascotas(R.drawable.gmail, "menem", false, 100));
        mascotas.add(new Mascotas(R.drawable.phone, "Messi", false, 1000));
*/
        BaseDatos db = new BaseDatos(context);
        insertarSeisMascotas(db);

        return db.obtenerTodasLasMascotas();
    }

    public void insertarSeisMascotas(BaseDatos db){
        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Messi");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.gmail);
        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Pedro");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.phone);
        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Menem");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.phone);
        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Peron");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.gmail);
        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Ronaldinho");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.phone);
        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Messi");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.gmail);
        db.insertarMascota(contentValues);
    }

    public void darLikeMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES, LIKE);
        db.insertarLikeMascota(contentValues);
    }

    public int obtenerLikesMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascota(mascota);
    }
}
