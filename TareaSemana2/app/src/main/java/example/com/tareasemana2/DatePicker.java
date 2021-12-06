package example.com.tareasemana2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
// Obtenido de: https://programacionymas.com/blog/como-pedir-fecha-android-usando-date-picker
import java.util.Calendar;

/*  La interfaz DatePickerDialog.OnDateSetListener es la encargada
de invocar el método onDateSet cada vez que una nueva fecha es seleccionada

    La idea es acceder a la fecha seleccionada desde nuestra actividad. */

public class DatePicker extends DialogFragment {

    private DatePickerDialog.OnDateSetListener listener;

    public static DatePicker newInstance(DatePickerDialog.OnDateSetListener listener) {
        DatePicker fragment = new DatePicker();
        fragment.setListener(listener);
        return fragment;
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        /* Estamos devolviendo una INSTANCIA de DatePickerDialog
           El "this" que utilizamos como segundo parametro es el que hace que la misma clase
        sea la que tenga que actuar de listener, por eso arriba implementamos DatePickerDialog.OnDateListener. */

        //return new DatePickerDialog(getActivity(), this, year, month, day);

        return new DatePickerDialog(getActivity(), listener, year, month, day);
    }

}