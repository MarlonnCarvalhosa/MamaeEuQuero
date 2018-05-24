package com.marlonncarvalhosa.mamaeeuquero.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.marlonncarvalhosa.mamaeeuquero.R;
import com.marlonncarvalhosa.mamaeeuquero.model.Produto;
import com.marlonncarvalhosa.mamaeeuquero.utils.ConstantsUtils;

public class DescricaoFragment extends Fragment{
private Bundle bundle;
private  Produto
 produto;

    public DescricaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_descricao, container, false);

        initView(view);

    return view;
    }

    private void initView(View view) {
        bundle = getArguments();
        if(bundle!= null){
            produto = (Produto) bundle.getSerializable(ConstantsUtils.PRODUTO);


        }
        Toast.makeText(getActivity(), ""+ produto.getNome() + produto.getCat(), Toast.LENGTH_SHORT).show();

    }

    public DescricaoFragment newIntance(Produto produto) {
        DescricaoFragment  descricaoFragment = new DescricaoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstantsUtils.PRODUTO, produto);
        descricaoFragment.setArguments(bundle);
        return descricaoFragment;
    }
}
