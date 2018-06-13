package com.marlonncarvalhosa.mamaeeuquero.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.marlonncarvalhosa.mamaeeuquero.BancoDeDados;
import com.marlonncarvalhosa.mamaeeuquero.R;
import com.marlonncarvalhosa.mamaeeuquero.model.Mensagem;
import com.marlonncarvalhosa.mamaeeuquero.model.Usuario;

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

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listViewChat = getView().findViewById(R.id.list_view_chat);
        spinnerEscola = getView().findViewById(R.id.spinner_escola);
        editTextMensagem = getView().findViewById(R.id.edit_mensagem);
        imageViewEnviar = getView().findViewById(R.id.image_enviar);

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

                        TextView textView = v.findViewById(R.id.text_view_mensagem);
                        textView.setText(model.getTexto());

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

                String texto = editTextMensagem.getText().toString();

                if(!texto.isEmpty()){
                    Mensagem mensagem = new Mensagem();
                    Usuario usuario = new Usuario();
                    mensagem.setId(BancoDeDados.getInstance().getId(getActivity()));
                    mensagem.setTexto(texto);
                    reference.push().setValue(mensagem);
                    editTextMensagem.setText("");
                }


            }
        });

        /*

        */


    }
}
