package example.com.tareasemana4.MainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

import example.com.tareasemana4.DetallesMascota.DetalleMascota;
import example.com.tareasemana4.Mascotas.ListaDeMascotas;
import example.com.tareasemana4.MenuOpciones.About;
import example.com.tareasemana4.MenuOpciones.Settings;
import example.com.tareasemana4.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar     = findViewById(R.id.my_toolbar);
        tabLayout   = findViewById(R.id.tabLayout);
        viewPager2  = findViewById(R.id.viewPager);

        setUpViewPager();

        if (toolbar != null){
            setSupportActionBar(toolbar);
            //setTitle("Contactos");
        }
    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ListaDeMascotas());
        fragments.add(new DetalleMascota());
        return fragments;
    }
    private void setUpViewPager() {
        PageAdapter adapter = new PageAdapter(this, agregarFragments());
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) ->
                tab.setText(""))
                .attach();
        tabLayout.getTabAt(0).setIcon(R.drawable.gmail);
        tabLayout.getTabAt(1).setIcon(R.drawable.phone);
    }
    //**********************************************************************************************
    //MENU DE OPCIONES:
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mAbout:
                startActivity(new Intent(this, About.class));
                break;

            case R.id.mSettings:
                startActivity(new Intent(this, Settings.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}