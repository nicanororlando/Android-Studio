package example.com.tareasemana4.MainActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.ArrayList;

import example.com.tareasemana4.DetallesMascota.DetalleMascota;
import example.com.tareasemana4.Mascotas.ListaDeMascotas;

public class PageAdapter extends FragmentStateAdapter {

    ArrayList<Fragment> fragments;

    public PageAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<Fragment> fragments) {
        super(fragmentActivity);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
