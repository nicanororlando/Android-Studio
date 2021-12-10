/*
 * Created by Nicanor Orlando.
 * Copyright (c) 7/12/21 09:24.
 */

package example.com.contactos.Principal;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import example.com.contactos.DetalleContactos.DetalleContacto;
import example.com.contactos.R;

//  Este adaptador va a recibir una coleccion de contacterViewHolder
public class ContactoAdaptador extends RecyclerView.Adapter<ContactoAdaptador.ContactoViewHolder>{

    ArrayList<Contacto> contactos;
    Activity activity;

    //  Constructor para cuando llamemos a la clase pasarle la lista de contactos y el contexto.
    public ContactoAdaptador(ArrayList<Contacto> contactos, Activity activity){
        this.contactos = contactos;
        this.activity = activity;
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

        contactoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, contacto.getNombre(), Toast.LENGTH_SHORT).show();

                //  Como tenemos "activity" como contexto de la actividad fuera de este adaptador, podemos
                //  referir el contexto en esta clase Adaptador.
                Intent intent = new Intent(activity, DetalleContacto.class);
                intent.putExtra("Nombre", contacto.getNombre());
                intent.putExtra("Telefono", contacto.getTelefono());
                intent.putExtra("Email", contacto.getEmail());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { //Cantidad de elementos de mi lista
        return contactos.size();
    }

    //  Para darle vida a mis Views
    /*  Cualquier elemento estatico como este, siempre se va a accesar a partir del nombre de la clase
    que contiene este elemento, en este caso, ContactoAdaptador. */
    public static class ContactoViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgFoto;
        private final TextView tvNombreCV;
        private final TextView tvTelefonoCV;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFoto       = itemView.findViewById(R.id.imgFoto);
            tvNombreCV    = itemView.findViewById(R.id.tvNombreCV);
            tvTelefonoCV  = itemView.findViewById(R.id.tvTelefonoCV);
        }
    }
}
