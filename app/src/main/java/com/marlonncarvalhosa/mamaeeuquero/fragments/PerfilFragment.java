package com.marlonncarvalhosa.mamaeeuquero.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private Button desconectar;
    private Query queryPerfil;
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
      
        metodobotoes(view);
        getUsuario(usuario.getUid());
        return view;
    }


    public void  metodobotoes (View view){
        desconectar=view.findViewById(R.id.desconectar);
        desconectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                FragmentoUtils.replace(getActivity(), new LoginFragment());

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

        Toast.makeText(getActivity(), usuario.getNomeUsuario()+"\n"+ usuario.getEmail(), Toast.LENGTH_SHORT).show();

    }

}
