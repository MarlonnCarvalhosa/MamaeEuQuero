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

import com.marlonncarvalhosa.mamaeeuquero.CadastroActivity;
import com.marlonncarvalhosa.mamaeeuquero.MainActivity;
import com.marlonncarvalhosa.mamaeeuquero.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeiloarFragment extends Fragment {
    private EditText edit_produto ,edit_descricao;
    private Button leiloar;
    private Spinner categoria;
    private LayoutInflater inflater1;
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
        edit_descricao= view.findViewById(R.id.editText_descricao);
        categoria=view.findViewById(R.id.spinnerclasse);
        leiloar=view.findViewById(R.id.button_leiloar);

    }
    private void metodbutton() {
        leiloar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

}