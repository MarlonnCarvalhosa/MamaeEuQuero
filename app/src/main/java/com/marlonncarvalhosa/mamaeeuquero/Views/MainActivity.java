package com.marlonncarvalhosa.mamaeeuquero.Views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.marlonncarvalhosa.mamaeeuquero.utils.BottomNavigationViewHelper;
import com.marlonncarvalhosa.mamaeeuquero.R;
import com.marlonncarvalhosa.mamaeeuquero.fragments.BuscarFragment;
import com.marlonncarvalhosa.mamaeeuquero.fragments.CarrinhoFragment;
import com.marlonncarvalhosa.mamaeeuquero.fragments.InicioFragment;
import com.marlonncarvalhosa.mamaeeuquero.fragments.LeiloarFragment;
import com.marlonncarvalhosa.mamaeeuquero.fragments.PerfilFragment;
import com.marlonncarvalhosa.mamaeeuquero.utils.FragmentoUtils;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_inicio:
                    FragmentoUtils.replace(MainActivity.this, new InicioFragment());
                    return true;
                case R.id.navigation_buscar:
                    FragmentoUtils.replace(MainActivity.this, new BuscarFragment());
                    return true;
                case R.id.navigation_leiloar:
                    FragmentoUtils.replace(MainActivity.this, new LeiloarFragment());
                    return true;
                case R.id.navigation_carrinho:
                    FragmentoUtils.replace(MainActivity.this, new CarrinhoFragment());
                    return true;
                case R.id.navigation_perfil:
                    FragmentoUtils.replace(MainActivity.this, new PerfilFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentoUtils.replace(MainActivity.this, new InicioFragment());

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        /*RecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if (dy > 0 ||dy<0 && navigation.isShown())
                {
                    navigation.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                {
                    navigation.setVisibility(View.VISIBLE);
                }

                super.onScrollStateChanged(recyclerView, newState);
            }*/ //Oculta o Bottom navigation

    }



}
