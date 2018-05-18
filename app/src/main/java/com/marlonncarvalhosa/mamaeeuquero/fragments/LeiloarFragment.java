package com.marlonncarvalhosa.mamaeeuquero.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.marlonncarvalhosa.mamaeeuquero.DAO.DataBaseDAO;
import com.marlonncarvalhosa.mamaeeuquero.R;
import com.marlonncarvalhosa.mamaeeuquero.model.Produto;
import com.squareup.picasso.Picasso;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeiloarFragment extends Fragment {

    private EditText edit_produto, edit_cidade, edit_preco ,edit_descricao;
    private Button leiloar;
    private Spinner categoria;
    private Button addImgButton;
    private ImageView miniImagem;
    private LayoutInflater inflater1;

    private Uri mImageUri;

    private static final int PICK_IMAGE_REQUEST = 1;

    public LeiloarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_leiloar, container, false);
        idcampo(view);
        btnAdicionarImg(view);
        return  view;

    }

    private void idcampo(View view) {

        miniImagem = view.findViewById(R.id.mini_imagem);
        addImgButton = view.findViewById(R.id.btnAddImg);
        edit_produto = view.findViewById(R.id.edit_nomeProduto);
        edit_preco = view.findViewById(R.id.edit_preco);
        edit_cidade = view.findViewById(R.id.edit_nomecidade);
        categoria = view.findViewById(R.id.spinnerclasse);
        leiloar = view.findViewById(R.id.button_leiloar);
        edit_descricao  =view.findViewById(R.id.edit_DescricaoProduto);

        leiloar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(),"Produto Leiloado",Toast.LENGTH_LONG).show();
                Produto produto = new Produto();
                produto.setPreco(edit_preco.getText().toString());
                produto.setNome(edit_produto.getText().toString());
                produto.setCat(categoria.getSelectedItem().toString());
                produto.setLocal(edit_cidade.getText().toString());
                produto.setDescrição(edit_descricao.getText().toString());

                new DataBaseDAO().instancia_produto(produto);

            }
        });

    }

    public void btnAdicionarImg(View view) {
        
        addImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                abrirImagem();
                
            }
        });
        
    }

    private void abrirImagem() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.get().load(mImageUri).into(miniImagem);
            miniImagem.setImageURI(mImageUri);
        }
    }

}