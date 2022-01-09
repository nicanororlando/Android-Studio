package example.com.tareasemana4.BaseDatos.Presentador;

import android.content.Context;

import java.util.ArrayList;

import example.com.tareasemana4.BaseDatos.ConstructorMascotas;
import example.com.tareasemana4.Mascotas.IRecyclerViewFragmentView;
import example.com.tareasemana4.Pojo.Mascota;

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter{

    private final IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private final Context context;
    private ArrayList<Mascota> mascotas;

    //  Esta es la primera clase que se llama desde Lista de mascotas, de esta clase deriva el resto.
    //  Obtenemos la instancia y el contexto. Importante tambien llamar a la funcion que es la que va a iniciar
    //  la obtencion de los datos.
    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerMascotasBaseDatos();
    }

    @Override
    public void obtenerMascotasBaseDatos() {

        //  Creo una instancia del constructor de mascotas para acceder a sus metodos.
        ConstructorMascotas constructorMascotas = new ConstructorMascotas(context);

        //  Los datos creados se asignan a nuestro objeto global, y luego inicializamos el RV pasandole el objeto global mascotas.
        mascotas = constructorMascotas.obtenerDatos();

        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {

        //Inicializamos adaptador, pasandole como parametro la creacion de nuestro adaptador de mascotas.
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascotas));
        iRecyclerViewFragmentView.generarLinearLayoutVertical();
    }
}
