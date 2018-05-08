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
    EditText edit_produto ,edit_descricao;
    Button leiloar;
    Spinner categoria;
    LayoutInflater inflater1;


    public LeiloarFragment() {
        idcampo();
        metodbutton();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_leiloar, container, false);

    }

    private void idcampo() {
        final View view1 = inflater1.inflate(R.layout.fragment_leiloar,null);
        edit_produto = view1.findViewById(R.id.edit_nomeProduto);
        edit_descricao= view1.findViewById(R.id.editText_descricao);
        categoria=view1.findViewById(R.id.spinnerclasse);
        leiloar=view1.findViewById(R.id.button_leiloar);

    }
    private void metodbutton() {
        leiloar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

}
