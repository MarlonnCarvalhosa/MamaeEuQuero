package com.marlonncarvalhosa.mamaeeuquero.utils;

import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.marlonncarvalhosa.mamaeeuquero.DAO.DataBaseDAO;
import com.marlonncarvalhosa.mamaeeuquero.R;
import com.marlonncarvalhosa.mamaeeuquero.Views.MainActivity;
import com.marlonncarvalhosa.mamaeeuquero.Views.SplashScreenActivity;
import com.marlonncarvalhosa.mamaeeuquero.fragments.DescricaoFragment;
import com.marlonncarvalhosa.mamaeeuquero.fragments.InicioFragment;
import com.marlonncarvalhosa.mamaeeuquero.fragments.PerfilFragment;
import com.marlonncarvalhosa.mamaeeuquero.model.Produto;
import com.marlonncarvalhosa.mamaeeuquero.model.Usuario;

import java.util.Locale;

public class LanceDialog extends AppCompatDialogFragment {

    private EditText valor;
    private Produto produto;
    FirebaseAuth auth;
    String lancedocomprador;
    private Query queryPerfil;
    private String idusuario;
    private FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.lance_layout, null);
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        builder.setView(view)
                .setTitle("De seu Lance!")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Dar Lance", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (TextUtils.isEmpty(valor.getText())){
                            Toast.makeText(getActivity(), "voce nao inseriu nenhum valor ", Toast.LENGTH_SHORT).show();
                        return;
                        }


                        float novolance = Float.valueOf(valor.getText().toString().replace("R$", "")
                                .replace(",", "."));

                        float lanceatual = Float.valueOf(produto.getPreco()).floatValue();;

                        if (novolance>lanceatual){

                            getUsuario(user.getUid());

                        }
                        else {

                            Toast.makeText(getActivity(), "valor inserido e menor que o lance de R$:"+lanceatual, Toast.LENGTH_SHORT).show();}


                    }
                });

        valor = view.findViewById(R.id.edit_lance);

        Locale mLocale = new Locale("pt", "BR");
        valor.addTextChangedListener(new MoneyTextWatcher(valor, mLocale));

        return builder.create();
    }

    private void getUsuario(String uId){
        queryPerfil = DataBaseDAO.getQuerryUsuario(uId);
        queryPerfil.keepSynced(true);
        queryPerfil.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                    Usuario usuario = dataSnapshot.getValue(Usuario.class);
                    produto.recebeLance(valor.getText().toString().replace("R$", "").replace(",", "."),lancedocomprador,usuario.getNomeUsuario() ,usuario.getId() );
                    ConfiguraçõesFirebase.getProdutos().getRef().child(produto.getId()).setValue(produto);

                    Toast.makeText(getActivity(), "Lance efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                    FragmentoUtils.replace(getActivity(), new InicioFragment());


                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}