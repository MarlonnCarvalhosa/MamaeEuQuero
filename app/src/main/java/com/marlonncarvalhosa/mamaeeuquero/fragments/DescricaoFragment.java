package com.marlonncarvalhosa.mamaeeuquero.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.marlonncarvalhosa.mamaeeuquero.Views.FullScreenImage;
import com.marlonncarvalhosa.mamaeeuquero.R;
import com.marlonncarvalhosa.mamaeeuquero.Views.ImagensActivity;
import com.marlonncarvalhosa.mamaeeuquero.model.Produto;
import com.marlonncarvalhosa.mamaeeuquero.utils.ConstantsUtils;
import com.marlonncarvalhosa.mamaeeuquero.utils.FragmentoUtils;
import com.marlonncarvalhosa.mamaeeuquero.utils.Lance_Dialog;
import com.marlonncarvalhosa.mamaeeuquero.utils.MoneyTextWatcher;

import java.util.Locale;


public class DescricaoFragment extends Fragment{
    public static final String URL_IMAGEM = "package com.marlonncarvalhosa.mamaeeuquero.fragments;";
    private Bundle bundle;
    private Produto produto;
    private ImageView imageView;
    private TextView nomeProduto,lanceProduto,tempoProduto,detalhesProduto;
    private Button btnLance;
    private FirebaseAuth auth;


    public DescricaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_descricao, container, false);
        auth = FirebaseAuth.getInstance();
        idCampo(view);
        initView(view);
        imgFull(view);


        getActivity().setTitle("Detalhes do Produto");

    return view;
    }

    public void imgFull(View view) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().startActivity(new Intent(ImagensActivity.newIntent(getActivity(), produto)));

            }
        });
    }

    private void idCampo(View view) {

        nomeProduto = view.findViewById(R.id.TextProduto);
        lanceProduto = view.findViewById(R.id.TextValor);
        tempoProduto = view.findViewById(R.id.TextTempo);
        detalhesProduto = view.findViewById(R.id.textDetalhes);
        imageView = view.findViewById(R.id.imageProduto);
        btnLance = view.findViewById(R.id.btn_lance);
    }

    private void abrirDialogo() {

        Lance_Dialog lance_dialog = new Lance_Dialog();
        lance_dialog.setProduto(produto);
        lance_dialog.show(getFragmentManager(), " Lance");

    }


    private void initView(View view) {
        bundle = getArguments();
        if(bundle!= null){
            produto = (Produto) bundle.getSerializable(ConstantsUtils.PRODUTO);
            nomeProduto.setText(""+produto.getNome());
            lanceProduto.setText(produto.getPreco());
            tempoProduto.setText(produto.getDataInicial());
            detalhesProduto.setText(produto.getDescrição());
            btnLance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    verificaAuth();
                }
            });

            String url = produto.getImagem1().getUrl();

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

    public void verificaAuth() {
        if (auth.getCurrentUser() != null) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                abrirDialogo();
            }

        }
        if (auth.getCurrentUser() == null){
            FragmentoUtils.replace(getActivity(), new LoginFragment());
        }

    }

    //Evento de clique de voltar para fragmento anterior <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Override
    public void onResume() {
        super.onResume();

        if(getView() == null){
            return;
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){

                    FragmentoUtils.replace(getActivity(), new InicioFragment());
                    return true;
                }
                return false;
            }
        });
    }
}





