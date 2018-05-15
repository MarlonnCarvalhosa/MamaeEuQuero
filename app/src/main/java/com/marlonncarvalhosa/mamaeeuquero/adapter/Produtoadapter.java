package com.marlonncarvalhosa.mamaeeuquero.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marlonncarvalhosa.mamaeeuquero.R;
import com.marlonncarvalhosa.mamaeeuquero.model.Produto;

import java.util.List;

public class Produtoadapter extends RecyclerView.Adapter<Produtoadapter.ViewHolder> {
    private FragmentActivity activity;
    private List<Produto> produtos;
    private RecyclerView recyclerView;


    public Produtoadapter(FragmentActivity activity, List<Produto> produtos,RecyclerView recyclerView){
        this.activity=activity;
        this.produtos=produtos;
        this.recyclerView=recyclerView;
    }
    public void atualiza(List<Produto> produtos){
        this.produtos=produtos;
        this.notifyDataSetChanged();
        this.recyclerView.scrollToPosition(produtos.size());
    }

    @NonNull
    @Override
    public Produtoadapter.ViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Produtoadapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Produto produto = produtos.get(position);
        holder.textViewProduto.setText(produto.getNome());
        holder.textViewCidade.setText(produto.getPreco());
        holder.textViewpreco.setText(produto.getPreco());

    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewProduto,textViewpreco,textViewCidade;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewProduto=itemView.findViewById(R.id.nomeProduto);
            textViewCidade=itemView.findViewById(R.id.cidade);
            textViewpreco=itemView.findViewById(R.id.preco);
        }
    }
}
