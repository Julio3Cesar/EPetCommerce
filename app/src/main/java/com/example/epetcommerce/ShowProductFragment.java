package com.example.epetcommerce;


import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowProductFragment extends Fragment {

    private TextView txtProductName;
    private TextView txtProductDescription;
    private TextView txtProductAmount;
    private ImageView imgShowProduct;
    private Button btnAddToCart;
    private Button btnBuy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_show_product, container, false);

        TextView txtProductName = view.findViewById(R.id.txtProductName);
        TextView txtProductDescription = view.findViewById(R.id.txtProductDescription);
        TextView txtProductAmount = view.findViewById(R.id.txtProductAmount);
        ImageView imgShowProduct = view.findViewById(R.id.imgShowProduct);

        Button btnAddToCart = view.findViewById(R.id.btnAddToCart);
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Adicionar ao singleton de produtos

                ProductListFragment productListFragment = new ProductListFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, productListFragment);
            }
        });

        Button btnBuy = view.findViewById(R.id.btnBuy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Adicionar ao singleton de produtos

                CartFragment cartFragment = new CartFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, cartFragment);
            }
        });

        return view;
    }
}
