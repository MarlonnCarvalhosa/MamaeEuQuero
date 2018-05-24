package com.marlonncarvalhosa.mamaeeuquero.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.marlonncarvalhosa.mamaeeuquero.R;
import com.marlonncarvalhosa.mamaeeuquero.utils.FragmentoUtils;

public class LoginFragment extends Fragment
{
    private EditText senha,email;
    private LinearLayout linearLayout;
    private TextView recuperar;
    private Button login;
    private FirebaseAuth auth;
    ProgressBar progressBar;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        idCampo(view);



        auth = FirebaseAuth.getInstance();
        verificaAuth();
        progressBar.setVisibility(View.GONE);
        return view;

    }
    private void idCampo(View view) {
        email = view.findViewById(R.id.editText_email);
        senha = view.findViewById(R.id.editText_senha);
        recuperar = view.findViewById(R.id.redefinir);
        progressBar = view.findViewById(R.id.progressbar);
        login = view.findViewById(R.id.logar);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                efetuaLogin();
            }
        });
    }

    private void efetuaLogin() {
        login.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        final String inputemail = email.getText().toString();
        final String password = senha.getText().toString();

        if (TextUtils.isEmpty(inputemail)) {
            email.setError("Campo Obrigatorio");
            login.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);



            return;
        }

        if (TextUtils.isEmpty(password)) {
            senha.setError("Campo Obrigatorio");
            login.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

            return;
        }


        auth.signInWithEmailAndPassword(inputemail,password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(!task.isSuccessful()){
                            login.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            email.setText("");
                            senha.setText("");




                        }
                        else {
                            FragmentoUtils.replace(getActivity(), new PerfilFragment());
                        }


                    }
                });

    }


    public void verificaAuth() {
        if (auth.getCurrentUser() != null) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) { Toast.makeText(getActivity(),"foi",Toast.LENGTH_LONG).show();
                FragmentoUtils.replace(getActivity(), new PerfilFragment());

            }

        }
    }
}
