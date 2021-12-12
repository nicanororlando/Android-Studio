package example.com.tareasemana4.Mascotas;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Objects;

import example.com.tareasemana4.DetallesMascota.DetalleMascota;
import example.com.tareasemana4.MainActivity.MainActivity;
import example.com.tareasemana4.R;

public class ListaDeMascotas extends Fragment implements AdaptadorMascota.ItemClickListener{

    //  Importante declarar aca para poder utilizarlo fuera del onCreate.
    ArrayList<Mascotas> mascotas;
    private RecyclerView rvMascotas;
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
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvMascotas.setLayoutManager(llm);

        inicializarListaMascotas();
        inicializarAdaptador();

        return view;
    }

    public void inicializarListaMascotas(){
        mascotas = new ArrayList<>();

        mascotas.add(new Mascotas(R.drawable.gmail, "Nicanor"));
        mascotas.add(new Mascotas(R.drawable.phone, "pedrooo"));
        mascotas.add(new Mascotas(R.drawable.gmail, "roberto"));
        mascotas.add(new Mascotas(R.drawable.phone, "carlos"));
        mascotas.add(new Mascotas(R.drawable.gmail, "menem"));
        mascotas.add(new Mascotas(R.drawable.phone, "Messi"));
    }

    //  Instanciacion de ContactoAdaptador.
    /*  Este adaptador primero va a llamar al layout en el onCreate, despues en el onBind le pasa todos los datos
    de la lista de contactos que el viewHolder esta declarando.  */
    public void inicializarAdaptador() {
        AdaptadorMascota adaptador = new AdaptadorMascota(mascotas, this);
        rvMascotas.setAdapter(adaptador);
    }

    @Override
    public void onItemClick(Mascotas mascotas) {
        Fragment fragment = DetalleMascota.newInstance(mascotas.getNombre());
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
}