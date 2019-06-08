package com.example.epetcommerce.view_code;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.epetcommerce.NavigationDrawerActivity;
import com.example.epetcommerce.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProdutoViewHolder>{

    private IOnItemClickListener _listener;

    public interface IOnItemClickListener{
        void onItemClick(ProductCard ProductCard);
    }

    public void setOnItemClickListener(IOnItemClickListener listener)
    {
        _listener = listener;
    }

    private static ArrayList<ProductCard> _ProductCards;

    public static class ProdutoViewHolder extends RecyclerView.ViewHolder
    {

        public ImageView imgCard;
        public TextView txtPreco;
        public TextView txtNome;


        public ProdutoViewHolder(View itemView, final IOnItemClickListener innerListener) {
            super(itemView);

            imgCard = itemView.findViewById(R.id.imgProductCard);
            txtNome = itemView.findViewById(R.id.txtNomeProductCard);
            txtPreco = itemView.findViewById(R.id.txtPrecoProductCard);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(innerListener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            innerListener.onItemClick(_ProductCards.get(position));
                        }
                    }
                }
            });
        }
    }


    public ProductAdapter(ArrayList<ProductCard> ProductCards)
    {
        _ProductCards = ProductCards;
    }

    @Override
    public ProdutoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        ProdutoViewHolder produtoVH = new ProdutoViewHolder(v, _listener);
        return produtoVH;
    }

    @Override
    public void onBindViewHolder(ProdutoViewHolder holder, int position) {
        ProductCard produto = _ProductCards.get(position);

        String imgUrl = "https://oficinacordova.azurewebsites.net/android/rest/produto/image/";

        holder.txtPreco.setText("R$ " + produto.GetProduto().getPrecProduto() + "0");
        holder.txtNome.setText(produto.GetProduto().getNomeProduto());

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(NavigationDrawerActivity.getContext()));

        imageLoader.displayImage(imgUrl + produto.GetProduto().getIdProduto(), holder.imgCard);

    }

    @Override
    public int getItemCount() {
        return _ProductCards.size();
    }

}
