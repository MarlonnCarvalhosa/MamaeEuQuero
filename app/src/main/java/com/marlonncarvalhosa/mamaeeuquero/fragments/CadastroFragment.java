package com.marlonncarvalhosa.mamaeeuquero.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.marlonncarvalhosa.mamaeeuquero.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroFragment extends Fragment {

    private EditText datanNascimento;
    private EditText telefone;

    public CadastroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        View view= inflater.inflate(R.layout.fragment_cadastro, container, false);

        datanNascimento = (EditText) view.findViewById(R.id.editText_cadastro_data_nascimento);
        telefone = (EditText) view.findViewById(R.id.editText_cadastro_numero_celular);

        SimpleMaskFormatter data = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher data1 = new MaskTextWatcher(datanNascimento, data);
        datanNascimento.addTextChangedListener(data1);

        return view;
    }

}
