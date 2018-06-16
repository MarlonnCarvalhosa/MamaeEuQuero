package com.marlonncarvalhosa.mamaeeuquero.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.marlonncarvalhosa.mamaeeuquero.BancoDeDados;
import com.marlonncarvalhosa.mamaeeuquero.DAO.DataBaseDAO;
import com.marlonncarvalhosa.mamaeeuquero.R;
import com.marlonncarvalhosa.mamaeeuquero.model.Mensagem;
import com.marlonncarvalhosa.mamaeeuquero.model.Usuario;
import com.marlonncarvalhosa.mamaeeuquero.utils.ConfiguraçõesFirebase;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    private ListView listViewChat;
    private FirebaseListAdapter<Mensagem> adapter;
    private DatabaseReference reference;
    private Spinner spinnerEscola;
    private EditText editTextMensagem;
    private ImageView imageViewEnviar;
    private Usuario user;
    private Query queryPerfil;
    public Usuario usuario = new Usuario();
    private FirebaseAuth usuarioFirebase = FirebaseAuth.getInstance();

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_chat, container, false);
        getUsuario();
        initView(view);


        return view;
    }

    private void initView(View view) {

        listViewChat = view.findViewById(R.id.list_view_chat);
        spinnerEscola = view.findViewById(R.id.spinner_escola);
        editTextMensagem = view.findViewById(R.id.edit_mensagem);
        imageViewEnviar = view.findViewById(R.id.image_enviar);

        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        spinnerEscola.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                reference = firebaseDatabase.getReference().child("chat").child(((String) spinnerEscola.getSelectedItem()));


                FirebaseListOptions<Mensagem> options = new FirebaseListOptions.Builder<Mensagem>()
                        .setQuery(reference, Mensagem.class)
                        .setLayout(R.layout.adapter_chat)
                        .build();

                adapter = new FirebaseListAdapter<Mensagem>(options) {

                    @Override
                    protected void populateView(View v, Mensagem model, int position) {
                        TextView textViewNome = v.findViewById(R.id.nomeChat);
                        TextView textView = v.findViewById(R.id.text_view_mensagem);
                        textView.setText(model.getTexto());
                        textViewNome.setText(model.getNome());

                    }
                };
                listViewChat.setAdapter(adapter);
                adapter.startListening();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        imageViewEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usuario!= null){
                      String texto = editTextMensagem.getText().toString();

                if(!texto.isEmpty()){
                    Mensagem mensagem = new Mensagem();
                    mensagem.setId(BancoDeDados.getInstance().getId(getActivity()));
                    mensagem.setTexto(texto);
                    mensagem.setId(ConfiguraçõesFirebase.getFirebase().push().getKey());
                    mensagem.setuIdUsuario(usuario.getId());
                    mensagem.setNome(usuario.getNomeUsuario());
                    reference.push().setValue(mensagem);
                    editTextMensagem.setText("");
                }
                }else {

                    Toast.makeText(getActivity(), "Login requerido!", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }


    private void getUsuario(){
        queryPerfil = DataBaseDAO.getQuerryUsuario(usuarioFirebase.getUid());
        queryPerfil.keepSynced(true);
        queryPerfil.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                    Usuario usuario = dataSnapshot.getValue(Usuario.class);
                    persist(usuario);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void persist(Usuario usuario) {
        this.usuario=usuario;

    }
}
