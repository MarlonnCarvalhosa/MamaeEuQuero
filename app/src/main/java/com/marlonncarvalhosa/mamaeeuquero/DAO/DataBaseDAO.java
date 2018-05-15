package com.marlonncarvalhosa.mamaeeuquero.DAO;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.marlonncarvalhosa.mamaeeuquero.model.Produto;
import com.marlonncarvalhosa.mamaeeuquero.utils.ConfiguraçõesFirebase;
import com.marlonncarvalhosa.mamaeeuquero.utils.ConstantsUtils;

public class DataBaseDAO {

    public   void instancia_produto(Produto produto){
        produto.setId(ConfiguraçõesFirebase.getFirebase().push().getKey());
        Log.v("teste save",produto.getId()+produto.getNome());
        DatabaseReference reference = ConfiguraçõesFirebase.getFirebase();
        reference.child(ConstantsUtils.BANCO_PRODUTO).child(String.valueOf(produto.getId())).setValue(produto);






    }
}
