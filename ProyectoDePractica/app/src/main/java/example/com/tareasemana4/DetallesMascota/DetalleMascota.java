package example.com.tareasemana4.DetallesMascota;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.com.tareasemana4.R;

public class DetalleMascota extends Fragment implements BackKeyPressedListener{

    public ViewPager2 viewPager2;
    private String nombre= "";
    public static BackKeyPressedListener backKeyPressedListener;

    public static DetalleMascota newInstance(String nombre) {

        DetalleMascota detalleMascota = new DetalleMascota();

        Bundle args = new Bundle(); //* Bundle a recibir con datos.
        args.putString("nombre", nombre);

        detalleMascota.setArguments(args);

        return detalleMascota;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_mascota, container, false);

        Bundle data = this.getArguments();
        if(data != null){
            nombre = data.getString("nombre");
        }

        TextView nombreMascota = view.findViewById(R.id.nombreMascota);
        nombreMascota.setText(nombre);

        return view;
    }

    @Override
    public void onPause() {
        backKeyPressedListener = null;
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        backKeyPressedListener = this;
    }

    @Override
    public void onBackPressed(ViewPager2 viewPager2) {
        this.viewPager2 = viewPager2;
        getParentFragmentManager().popBackStack();
        viewPager2.setCurrentItem(0,true);

    }
}