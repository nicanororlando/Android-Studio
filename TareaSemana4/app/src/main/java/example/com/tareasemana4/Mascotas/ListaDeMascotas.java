package example.com.tareasemana4.Mascotas;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import example.com.tareasemana4.R;

public class ListaDeMascotas extends Fragment {

    //  Importante declarar aca para poder utilizarlo fuera del onCreate.
    ArrayList<Mascotas> mascotas;
    private RecyclerView rvMascotas;

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
    public void inicializarAdaptador(){
        AdaptadorMascota adaptador = new AdaptadorMascota(mascotas, getActivity());
        rvMascotas.setAdapter(adaptador);
    }
}