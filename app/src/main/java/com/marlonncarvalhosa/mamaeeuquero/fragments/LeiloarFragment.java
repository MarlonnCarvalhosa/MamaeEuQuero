package com.marlonncarvalhosa.mamaeeuquero.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.marlonncarvalhosa.mamaeeuquero.DAO.DataBaseDAO;
import com.marlonncarvalhosa.mamaeeuquero.R;
import com.marlonncarvalhosa.mamaeeuquero.model.Produto;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeiloarFragment extends Fragment {

    private EditText edit_produto ,edit_cidade;
    private Button leiloar;
    private Spinner categoria;
    private LayoutInflater inflater1;
    DatabaseReference databaseProduto;


    public LeiloarFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_leiloar, container, false);
        idcampo(view);
        return  view;

    }

    private void idcampo(View view) {

        edit_produto = view.findViewById(R.id.edit_nomeProduto);
        edit_cidade = view.findViewById(R.id.edit_nomeCidade);
        categoria = view.findViewById(R.id.spinnerclasse);
        leiloar = view.findViewById(R.id.button_leiloar);

        leiloar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"botao do mal",Toast.LENGTH_LONG).show();
                Produto produto = new Produto();
                produto.setNome(edit_produto.getText().toString());
                produto.setCat(categoria.getSelectedItem().toString());
                produto.setPreco("2000");

                new DataBaseDAO().instancia_produto(produto);


            }
        });

    }





}