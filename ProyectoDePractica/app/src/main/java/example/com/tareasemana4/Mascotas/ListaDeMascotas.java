package example.com.tareasemana4.Mascotas;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import example.com.tareasemana4.DetallesMascota.DetalleMascota;
import example.com.tareasemana4.Pojo.Mascota;
import example.com.tareasemana4.BaseDatos.Presentador.IRecyclerViewFragmentPresenter;
import example.com.tareasemana4.BaseDatos.Presentador.RecyclerViewFragmentPresenter;
import example.com.tareasemana4.R;

public class ListaDeMascotas extends Fragment implements AdaptadorMascota.ItemClickListener, IRecyclerViewFragmentView {

    //  Importante declarar aca para poder utilizarlo fuera del onCreate.
    ArrayList<Mascota> mascotas;
    private RecyclerView rvMascotas;
    private IRecyclerViewFragmentPresenter iRecyclerViewFragmentPresenter;
    ViewPager2 viewPager2;

    public ListaDeMascotas(ViewPager2 viewPager2){
        this.viewPager2 = viewPager2;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment, le asignamos nuestra clase java al layout.
        View view = inflater.inflate(R.layout.fragment_lista_de_mascotas, container, false);

        rvMascotas = view.findViewById(R.id.rvMascotas);

        /*  Al pasarle ya el objeto "iReciclerViewFragmentView", desde la otra clase podemos acceder a los metodos tambien
        entonces ya desde ahi llamamos al metodo "generarLinearLayoutVertical()" */
        iRecyclerViewFragmentPresenter = new RecyclerViewFragmentPresenter(this, getContext());

        return view;
    }

    @Override
    public void onItemClick(Mascota mascota) {
        Fragment fragment = DetalleMascota.newInstance(mascota.getNombre());
        viewPager2.setCurrentItem(1,true);

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onResume() {
        getParentFragmentManager().popBackStack();
        super.onResume();
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(RecyclerView.VERTICAL);
        rvMascotas.setLayoutManager(llm);
    }

    //  Instanciacion de ContactoAdaptador.
    /*  Este adaptador primero va a llamar al layout en el onCreate, despues en el onBind le pasa todos los datos
    de la lista de contactos que el viewHolder esta declarando.  */
    @Override
    public AdaptadorMascota crearAdaptador(ArrayList<Mascota> mascotas) {
        return new AdaptadorMascota(mascotas, this, getContext());
    }

    @Override
    public void inicializarAdaptadorRV(AdaptadorMascota adaptadorMascota) {
        rvMascotas.setAdapter(adaptadorMascota);
    }
}