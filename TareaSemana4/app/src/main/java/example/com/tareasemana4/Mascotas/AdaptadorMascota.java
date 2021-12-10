package example.com.tareasemana4.Mascotas;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import example.com.tareasemana4.DetallesMascota.DetalleMascota;
import example.com.tareasemana4.MainActivity.MainActivity;
import example.com.tareasemana4.MainActivity.PageAdapter;
import example.com.tareasemana4.R;

public class AdaptadorMascota extends RecyclerView.Adapter<AdaptadorMascota.ContactoViewHolder>{

    ArrayList<Mascotas> mascotas;
    Activity activity;

    //  Constructor para cuando llamemos a la clase pasarle la lista de contactos y el contexto.
    public AdaptadorMascota(ArrayList<Mascotas> contactos, Activity activity){
        this.mascotas = contactos;
        this.activity = activity;
    }

    @NonNull
    @Override
    //  Para darle vida a nuestro Layout CardView.
    /*  Retornamos nuestra vista al ViewHolder al constructor del ContactoViewHolder
    para poder tomar cada elemento del Layout. */
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new ContactoViewHolder(v);
    }

    //  Este elemento se va invocando cada vez que se recorre la lista de contactos.
    //  Lo utilizamos para setear todos los datos accediendo al ViewHolder.
    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder contactoViewHolder, int position) {
        Mascotas mascota = mascotas.get(position);
        contactoViewHolder.imgFoto.setImageResource(mascota.getFoto());
        contactoViewHolder.tvNombreCV.setText(mascota.getNombre());

        contactoViewHolder.itemView.setOnClickListener(v -> {
            
        });
    }

    @Override
    public int getItemCount() { //Cantidad de elementos de mi lista
        return mascotas.size();
    }

    //  Para darle vida a mis Views
    /*  Cualquier elemento estatico como este, siempre se va a accesar a partir del nombre de la clase
    que contiene este elemento, en este caso, ContactoAdaptador. */
    public static class ContactoViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgFoto;
        private final TextView tvNombreCV;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFoto       = itemView.findViewById(R.id.imgFoto);
            tvNombreCV    = itemView.findViewById(R.id.tvNombreCV);
        }
    }
}
