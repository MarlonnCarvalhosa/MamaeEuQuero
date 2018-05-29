package com.marlonncarvalhosa.mamaeeuquero.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.marlonncarvalhosa.mamaeeuquero.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriasFragment extends Fragment {

    private CardView acessorios;
    private CardView brinquedos;
    private CardView calcados;
    private CardView enxoval;
    private CardView escolar;
    private CardView moveis;
    private CardView roupas;



    public CategoriasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_categorias, container, false);

        /*clickAcessorios(view);
        clickBrinquedos(view);
        clickCalcados(view);
        clickEnxoval(view);
        clickEscolar(view);
        clickMoveis(view);
        clickRoupas(view);*/

        idCamppo(view);

        return view;

    }

    private void idCamppo(View view) {

        acessorios = (CardView) view.findViewById(R.id.acessorios);
        brinquedos = (CardView) view.findViewById(R.id.brinquedos);
        calcados = (CardView) view.findViewById(R.id.calcados);
        enxoval = (CardView) view.findViewById(R.id.enxoval);
        escolar = (CardView) view.findViewById(R.id.escolar);
        moveis = (CardView) view.findViewById(R.id.moveis);
        roupas = (CardView) view.findViewById(R.id.roupas);

    }

    /*private void clickAcessorios(View view) {

        acessorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void clickBrinquedos(View view) {

        brinquedos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void clickCalcados(View view) {

        calcados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void clickEnxoval(View view) {

        enxoval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void clickEscolar(View view) {

        escolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void clickMoveis(View view) {

        moveis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void clickRoupas(View view) {

        roupas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }*/

}
