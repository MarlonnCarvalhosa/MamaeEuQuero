package com.marlonncarvalhosa.mamaeeuquero.Views;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marlonncarvalhosa.mamaeeuquero.R;
import com.marlonncarvalhosa.mamaeeuquero.adapter.ImagensAdapter;
import com.marlonncarvalhosa.mamaeeuquero.model.Imagem;
import com.marlonncarvalhosa.mamaeeuquero.model.Produto;

import java.util.ArrayList;

public class ImagensActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ImagensAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagens);

        initView();
        Produto produto = (Produto) getIntent().getSerializableExtra("PRODUTO");
        ArrayList<Imagem> imagems = new ArrayList<>();
        imagems.add(produto.getImagem1());
        imagems.add(produto.getImagem2());
        imagems.add(produto.getImagem3());


        adapter = new ImagensAdapter(this, imagems);
        viewPager.setAdapter(adapter);

    }

    private void initView() {
        viewPager = findViewById(R.id.viewPager);

    }

    public static Intent newIntent(FragmentActivity context, Produto produto) {
        Intent intent = new Intent(context, ImagensActivity.class);
        intent.putExtra("PRODUTO", produto);
        return intent;
    }
}
