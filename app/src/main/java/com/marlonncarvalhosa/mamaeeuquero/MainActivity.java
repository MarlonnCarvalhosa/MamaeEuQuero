package com.marlonncarvalhosa.mamaeeuquero;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.marlonncarvalhosa.mamaeeuquero.fragments.InicioFragment;
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
                    return true;
                case R.id.navigation_leiloar:
                    return true;
                case R.id.navigation_carrinho:
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

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentoUtils.replace(MainActivity.this, new InicioFragment());

    }

}
