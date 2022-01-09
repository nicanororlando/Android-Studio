package example.com.tareasemana4.Mascotas;

import java.util.ArrayList;

import example.com.tareasemana4.Pojo.Mascota;

public interface IRecyclerViewFragmentView {

    void generarLinearLayoutVertical();
    AdaptadorMascota crearAdaptador(ArrayList<Mascota> mascotas);
    void inicializarAdaptadorRV(AdaptadorMascota adaptadorMascota);
}
