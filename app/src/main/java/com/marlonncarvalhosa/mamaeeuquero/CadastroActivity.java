package com.marlonncarvalhosa.mamaeeuquero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void finalizar(View  view){

        Toast.makeText(CadastroActivity.this, "Sera produzido em breve!", Toast.LENGTH_LONG).show();

    }
}
