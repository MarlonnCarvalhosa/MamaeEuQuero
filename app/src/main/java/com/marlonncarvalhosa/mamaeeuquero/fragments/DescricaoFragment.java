package com.marlonncarvalhosa.mamaeeuquero.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.marlonncarvalhosa.mamaeeuquero.R;
import com.marlonncarvalhosa.mamaeeuquero.model.Produto;
import com.marlonncarvalhosa.mamaeeuquero.utils.ConstantsUtils;

public class DescricaoFragment extends Fragment{
private Bundle bundle;
private  Produto produto;
private ImageView imageView;
private TextView nomeProduto,lanceProduto,tempoProduto,detalhesProduto;
private Button btnLance;

    public DescricaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_descricao, container, false);
        idCampo(view);

        initView(view);


    return view;
    }

    private void idCampo(View view) {
        nomeProduto=view.findViewById(R.id.TextProduto);
        lanceProduto=view.findViewById(R.id.TextValor);
        tempoProduto=view.findViewById(R.id.TextTempo);
        detalhesProduto=view.findViewById(R.id.textDetalhes);
        imageView=view.findViewById(R.id.imageProduto);
    }

    private void initView(View view) {
        bundle = getArguments();
        if(bundle!= null){
            produto = (Produto) bundle.getSerializable(ConstantsUtils.PRODUTO);
            nomeProduto.setText(""+produto.getNome());
            lanceProduto.setText(produto.getPreco());
            tempoProduto.setText(produto.getDataInicial());
            detalhesProduto.setText(produto.getDescrição());
            String url = produto.getImageUrl();

            try {
                Glide.with(getActivity())
                        .load(url)
                        .into(imageView);
            }catch (Exception e){
                e.printStackTrace();
            }


        }


    }

    public DescricaoFragment newIntance(Produto produto) {
        DescricaoFragment  descricaoFragment = new DescricaoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstantsUtils.PRODUTO, produto);
        descricaoFragment.setArguments(bundle);
        return descricaoFragment;
    }
}
