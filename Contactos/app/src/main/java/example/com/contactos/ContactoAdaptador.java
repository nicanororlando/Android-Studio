/*
 * Created by Nicanor Orlando.
 * Copyright (c) 7/12/21 09:24.
 */

package example.com.contactos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//  Este adaptador va a recibir una coleccion de contacterViewHolder
public class ContactoAdaptador extends RecyclerView.Adapter<ContactoAdaptador.ContactoViewHolder>{

    ArrayList<Contacto> contactos;

    //  Constructor para cuando llamemos a la clase pasarle la lista de contactos.
    public ContactoAdaptador(ArrayList<Contacto> contactos){
        this.contactos = contactos;
    }

    @NonNull
    @Override
    //  Para darle vida a nuestro Layout CardView.
    /*  Retornamos nuestra vista al ViewHolder al constructor del ContactoViewHolder
    para poder tomar cada elemento del Layout. */
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contacto, parent, false);
        return new ContactoViewHolder(v);
    }

    //  Este elemento se va invocando cada vez que se recorre la lista de contactos.
    //  Lo utilizamos para setear todos los datos accediendo al ViewHolder.
    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder contactoViewHolder, int position) {
        Contacto contacto = contactos.get(position);
        contactoViewHolder.imgFoto.setImageResource(contacto.getFoto());
        contactoViewHolder.tvNombreCV.setText(contacto.getNombre());
        contactoViewHolder.tvTelefonoCV.setText(contacto.getTelefono());
    }

    @Override
    public int getItemCount() { //Cantidad de elementos de mi lista
        return contactos.size();
    }

    //  Para darle vida a mis Views
    /*  Cualquier elemento estatico como este, siempre se va a accesar a partir del nombre de la clase
    que contiene este elemento, en este caso, ContactoAdaptador. */
    public static class ContactoViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private TextView tvNombreCV;
        private TextView tvTelefonoCV;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFoto       = itemView.findViewById(R.id.imgFoto);
            tvNombreCV    = itemView.findViewById(R.id.tvNombreCV);
            tvTelefonoCV  = itemView.findViewById(R.id.tvTelefonoCV);
        }
    }
}
