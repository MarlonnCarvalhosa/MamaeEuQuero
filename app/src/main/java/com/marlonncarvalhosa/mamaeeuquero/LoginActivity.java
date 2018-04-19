package com.marlonncarvalhosa.mamaeeuquero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //raphael guedes esteve aqui
    }


    public void entrar(View view){

        Toast.makeText(LoginActivity.this, "Sera produzido em breve!", Toast.LENGTH_SHORT).show();

    }

    public void esqueceuSenha(View view){

        Toast.makeText(LoginActivity.this, "Sera produzido em breve!", Toast.LENGTH_SHORT).show();

    }

    public void irParaALoja(View view){

        Intent intentIrParaALoja = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intentIrParaALoja);

    }

    public void cadastrar(View view){

        Intent intentCadastrar = new Intent(getApplicationContext(), CadastroActivity.class);
        startActivity(intentCadastrar);

    }


}
