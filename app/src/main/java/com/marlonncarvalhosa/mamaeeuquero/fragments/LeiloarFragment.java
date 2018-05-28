package com.marlonncarvalhosa.mamaeeuquero.fragments;



import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.marlonncarvalhosa.mamaeeuquero.DAO.DataBaseDAO;
import com.marlonncarvalhosa.mamaeeuquero.R;
import com.marlonncarvalhosa.mamaeeuquero.Views.MainActivity;
import com.marlonncarvalhosa.mamaeeuquero.model.Produto;
import com.marlonncarvalhosa.mamaeeuquero.utils.FragmentoUtils;
import com.marlonncarvalhosa.mamaeeuquero.utils.Lance_Dialog;
import com.squareup.picasso.Picasso;
import android.support.v7.app.AlertDialog;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Calendar;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeiloarFragment extends Fragment {

    private EditText edit_produto, edit_cidade, edit_preco, edit_descricao;
    private Button leiloar;
    private Spinner categoria;
    private Button addImgButton;
    private ImageView miniImagem;
    private String data;
    String horario;
    int hora , minuto, dia;
    private FirebaseAuth auth;

    private Uri filePath;

    private Uri mImageUri;

    private ImageView imageView;

    //teste
    FirebaseStorage storage;
    StorageReference storageReference;

    private final int PICK_IMAGE_REQUEST = 71;

    public LeiloarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_leiloar, container, false);
        auth = FirebaseAuth.getInstance();
        verificaAuth();
        final Calendar c = Calendar.getInstance();
      hora = 0 + c.get(Calendar.HOUR_OF_DAY);
      minuto = 0 + c.get(Calendar.MINUTE);
        dia = 0 +c.get(Calendar.DAY_OF_MONTH);
        horario = (hora + ":" + minuto);
        idCampo(view);
        btnAdicionarImg(view);
        return view;

    }

    private void idCampo(View view) {

        miniImagem = view.findViewById(R.id.mini_imagem);
        addImgButton = view.findViewById(R.id.btnAddImg);
        edit_produto = view.findViewById(R.id.edit_nomeProduto);
        edit_preco = view.findViewById(R.id.edit_preco);
        edit_cidade = view.findViewById(R.id.edit_nomecidade);
        categoria = view.findViewById(R.id.spinnerclasse);
        leiloar = view.findViewById(R.id.button_leiloar);
        edit_descricao = view.findViewById(R.id.edit_DescricaoProduto);
        long date = System.currentTimeMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        data = formatter.format(date);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        leiloar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                uploadImage();

            }
        });

    }

    private void uploadImage() {

        if (mImageUri != null) {
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Leiloando...");
            progressDialog.show();
            final String pathImage = "images/" + UUID.randomUUID().toString();
            final StorageReference ref = storageReference.child(pathImage);
            ref.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                    taskSnapshot.getDownloadUrl();
                    progressDialog.dismiss();


                    AlertDialog.Builder alert = new android.support.v7.app.AlertDialog.Builder(getContext());

                    alert
                            .setTitle("Produto Leiloado ;)")
                            .setIcon(R.drawable.ic_action_check_verde)
                            .setMessage("Seu produto foi anunciado com sucesso!")
                            .setCancelable(false)
                            .setPositiveButton("Finalizar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    FragmentoUtils.replace(getActivity(), new InicioFragment());
                                }
                            });

                    AlertDialog alertDialog = alert.create();
                    alertDialog.show();

                    Uri imageUrl = taskSnapshot.getDownloadUrl();

                    Produto produto = new Produto();
                    produto.setPreco(edit_preco.getText().toString());
                    produto.setNome(edit_produto.getText().toString());
                    produto.setCat(categoria.getSelectedItem().toString());
                    produto.setLocal(edit_cidade.getText().toString());
                    produto.setDescrição(edit_descricao.getText().toString());
                    produto.setImageUrl(imageUrl.toString());
                    produto.setPathImagem(pathImage);
                    produto.setDataInicial(data);
                    produto.setHorarioInicial(horario);
                    produto.setHora(hora);
                    produto.setMinuto(minuto);
                    produto.setDia(dia);

                    new DataBaseDAO().instancia_produto(produto);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();

                }

            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                            .getTotalByteCount());
                    progressDialog.setMessage("Carregando Imagem " + (int) progress + "%");
                }
            });
        }
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

    public void verificaAuth() {
        if (auth.getCurrentUser() != null) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {

            }

        }
        if (auth.getCurrentUser() == null) {
            FragmentoUtils.replace(getActivity(), new LoginFragment());
        }

    }

}