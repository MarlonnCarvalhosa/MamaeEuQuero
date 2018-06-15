package com.marlonncarvalhosa.mamaeeuquero.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.marlonncarvalhosa.mamaeeuquero.DAO.DataBaseDAO;
import com.marlonncarvalhosa.mamaeeuquero.R;
import com.marlonncarvalhosa.mamaeeuquero.model.Usuario;
import com.marlonncarvalhosa.mamaeeuquero.utils.FragmentoUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {
    private FirebaseAuth auth;
    private Button desconectar, leiloes, carrinho, chat;
    private TextView pessoa, celular;
    private Query queryPerfil;
    private String idusuario;
    private FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser() ;

    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            idusuario=user.getUid();


        }

        pessoa = (TextView) view.findViewById(R.id.pessoaPerfil);
        celular = (TextView) view.findViewById(R.id.celPerfil);
      
        metodobotoes(view);
        getUsuario(usuario.getUid());
        return view;
    }


    public void  metodobotoes (View view){
        desconectar=view.findViewById(R.id.desconectar);
        leiloes=view.findViewById(R.id.meusleiloes);
        carrinho = view.findViewById(R.id.carrinhoPerfil);
        chat = view.findViewById(R.id.perfilChat);

        desconectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                FragmentoUtils.replace(getActivity(), new LoginFragment());

            }
        });
        leiloes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("ID",idusuario);

                ProdutoCategoriaFragment produtoCategoriaFragment= new ProdutoCategoriaFragment();
                produtoCategoriaFragment.setArguments(args);
                FragmentoUtils.replace(getActivity(),produtoCategoriaFragment);

            }
        });

        carrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("ID", idusuario);

                CarrinhoFragment carrinhoFragment = new CarrinhoFragment();
                carrinhoFragment.setArguments(args);
                FragmentoUtils.replace(getActivity(), carrinhoFragment);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("ID", idusuario);

                ChatFragment chatFragment = new ChatFragment();
                chatFragment.setArguments(args);
                FragmentoUtils.replace(getActivity(), chatFragment);
            }
        });

    }

    private void getUsuario(String uId){
        queryPerfil = DataBaseDAO.getQuerryUsuario(uId);
        queryPerfil.keepSynced(true);
        queryPerfil.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                    Usuario usuario = dataSnapshot.getValue(Usuario.class);
                    exibir(usuario);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

          

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    private void exibir(Usuario usuario) {

        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            if (user.getDisplayName() != null) {
                pessoa.setText(usuario.getNomeUsuario());
            }
            if (user.getPhoneNumber() != null){
                celular.setText(usuario.getNumeroCelular());
            }
        }

    }

}
