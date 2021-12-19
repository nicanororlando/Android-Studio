package example.com.tareasemana4.MainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Objects;

import example.com.tareasemana4.DetallesMascota.DetalleMascota;
import example.com.tareasemana4.Mascotas.ListaDeMascotas;
import example.com.tareasemana4.MenuOpciones.Memoria;
import example.com.tareasemana4.MenuOpciones.About;
import example.com.tareasemana4.MenuOpciones.Settings;
import example.com.tareasemana4.R;

//PARA DISEÑAR EL TABBAR: https://danielme.com/2020/12/07/diseno-android-toolbar-tablayout-viewpager2-fragment-materialcomponents-androidx/?unapproved=7678&moderation-hash=488acd3d4726f85c67a53380b1589f0d#comment-7678

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
        fragments.add(new ListaDeMascotas(viewPager2));
        fragments.add(new DetalleMascota());
        return fragments;
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private void setUpViewPager() {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider));
        viewPager2.addItemDecoration(dividerItemDecoration);

        PageAdapter adapter = new PageAdapter(this, agregarFragments());
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) ->
                tab.setText(""))
                .attach();
        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.gmail);
        Objects.requireNonNull(tabLayout.getTabAt(0)).setText(R.string.tab_list);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.phone);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setText(R.string.tab_details);
    }
    public void modificarTab(){
        viewPager2.setCurrentItem(1,false);
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

            case R.id.mFileIO:
                startActivity(new Intent(this, Memoria.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (DetalleMascota.backKeyPressedListener != null) {
            DetalleMascota.backKeyPressedListener.onBackPressed(viewPager2);
        } else {
            getFragmentManager().popBackStack();
        }
    }
}