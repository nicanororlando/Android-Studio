package example.com.tareasemana4.Mascotas;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import example.com.tareasemana4.R;

public class AdaptadorMascota extends RecyclerView.Adapter<AdaptadorMascota.ContactoViewHolder>{

    ArrayList<Mascotas> mascotas;
    private final ItemClickListener clickListener;

    //  Constructor para cuando llamemos a la clase pasarle la lista de contactos y el contexto.
    public AdaptadorMascota(ArrayList<Mascotas> contactos, ItemClickListener clickListener){
        this.mascotas = contactos;
        this.clickListener = clickListener;
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
            clickListener.onItemClick(mascota);
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

    public interface ItemClickListener {
        void onItemClick(Mascotas mascotas);
    }
}
