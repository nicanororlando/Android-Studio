package example.com.tareasemana4.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import example.com.tareasemana4.Pojo.Mascota;

//  Usamos esta clase SQLiteOpenHelper para CREAR nuestra base de datos.
public class BaseDatos extends SQLiteOpenHelper {

    //  1°. Constructor.
    /*  Cuando yo genero un objeto BaseDatos, lo construimos aca, y en este constructor es donde
    se realiza toda la creacion de nuestra base de datos.
        Si la base de datos existe entonces la abre, y si no existe entonces crea una nueva base de datos. */
    public BaseDatos(@Nullable Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
    }

    //  2°. Creacion de tabla.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTAS  + "(" +
                ConstantesBaseDatos.TABLE_MASCOTAS_ID       + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE   + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_FOTO     + " INTEGER " +
                ")";

        //  Si hubo un error de escritura en la base de datos aca y compilo y no anda, ademas de cambiar
        //  el codigo, conviene desinstalar la app para que se reseteen todos los datos de la db.

        String queryCrearTablaLikeMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS + "(" +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA + " INTEGER, " +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES + " INTEGER, " +
                "FOREIGN KEY ("+ ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA + ")" +
                "REFERENCES " + ConstantesBaseDatos.TABLE_MASCOTAS + "(" + ConstantesBaseDatos.TABLE_MASCOTAS_ID + ")" +
                ")";

        //  Usamos este metodo cuando ejecutamos un query que no nos va a traer ningun valor de retorno.
        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikeMascota);
    }

    //  Se ejecuta solamente si necesitamos reestructurar nuestra base de datos.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_MASCOTAS);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS);
        onCreate(db);
    }

    //  4°. Consultar a la base de datos para agregar datos de la db a nuestro array.
    public ArrayList<Mascota> obtenerTodasLasMascotas(){
        ArrayList<Mascota> mascota = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();

        //  Para ejecutar las consultas. Necesitamos que nos devuelva la coleccion de datos que consulto.
        //  Nos da como resultado un Cursor.
        //  Este objeto nos ayuda a recorrer los registros.
        //  Le doy null por que no tengo ningun fiiltro para darle.
        //  El metodo rawQuery lo utilizamos cuando queremos un valor de retorno o respuesta.
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));

            //  Esta funcion es para recuperar los likes de cada item cuando la aplicacion se cierra y se vuelve a abrir.
            /*  Si no hacemos esto la aplicacion al reiniciarse no va a mostrar los likes que dimos anteriormente,
            por eso esto es clave para tener una persistencia de datos dinamicos. */
            insertarQueryLikesAlInicio(mascotaActual, db);

            mascota.add(mascotaActual);
        }

        db.close();
        return mascota;
    }

    //  3°. Insertar datos.
    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();

        //El objeto contentValues va a contener el nombre del campo, y su respectivo valor (Ejemplo: (nombre: "Messi")).
        db.insert(ConstantesBaseDatos.TABLE_MASCOTAS, null, contentValues);
        db.close();
    }

    public void insertarQueryLikesAlInicio(Mascota mascotaActual, SQLiteDatabase db){
        String queryLikes = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES+") as likes" +
                " FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS +
                " WHERE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA + "=" + mascotaActual.getId();

        Cursor registrosLikes = db.rawQuery(queryLikes, null);

        //  Si hay likes, los agrego, y sino lo seteo a cero.
        if (registrosLikes.moveToNext()){
            mascotaActual.setLikes(registrosLikes.getInt(0));
        }else {
            mascotaActual.setLikes(0);
        }
    }

    public void insertarLikeMascota(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES_MASCOTAS, null, contentValues);
        db.close();
    }

    //  Cuando llamamos a este metodo pasandole una mascota como parametro (cuando se hace click en el like), en
    //  esta funcion contamos los likes que tiene esa mascota.
    public int obtenerLikesMascota(Mascota mascota){
        int likes = 0;

        String query = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES+")" +
                " FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS +
                " WHERE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA + "=" + mascota.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        //  if por que es solo un registro y no varios como antes.
        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }

        db.close();
        return likes;
    }
}
