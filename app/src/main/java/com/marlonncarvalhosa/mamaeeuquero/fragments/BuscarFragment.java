package com.marlonncarvalhosa.mamaeeuquero.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.marlonncarvalhosa.mamaeeuquero.R;
import com.marlonncarvalhosa.mamaeeuquero.adapter.ProdutoAdapter;
import com.marlonncarvalhosa.mamaeeuquero.model.Produto;
import com.marlonncarvalhosa.mamaeeuquero.utils.ConfiguraçõesFirebase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuscarFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 71;
    private RecyclerView recyclerView;
    private List<Produto> produtos;
    private Query databaseProdutos;
    private ProdutoAdapter adapter;
    private ImageView imagemProduto;
    private Uri mImageUri;
    public BuscarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buscar, container, false);
        idCampo(view);
        preencherLista();
        return view;

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.get().load(mImageUri).into(imagemProduto);
            imagemProduto.setImageURI(mImageUri);
        }
    }

    private void preencherLista() {
        produtos = new ArrayList<>();
        databaseProdutos = ConfiguraçõesFirebase.getProdutos();
        databaseProdutos.keepSynced(true);
        databaseProdutos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                try{

                    produtos.clear();
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Produto produto = snapshot.getValue(Produto.class);
                        produtos.add(produto);


                    }
                    adapter.atualiza(produtos);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        adapter = new ProdutoAdapter(getActivity(),produtos);
        recyclerView.setAdapter(adapter);

    }

    public void idCampo(View view) {

        imagemProduto = view.findViewById(R.id.imagemProduto);
        recyclerView = view.findViewById(R.id.recyclerproduto);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

}
