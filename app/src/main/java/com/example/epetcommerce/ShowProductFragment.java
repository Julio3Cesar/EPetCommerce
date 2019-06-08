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
import android.widget.Toast;

import com.example.epetcommerce.database.ProductData;
import com.example.epetcommerce.models.Product;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;


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

        final ProductData productData = ProductData.getInstance();
        final Product product = productData.getActiveProduct();

        txtProductName.setText(product.getNomeProduto());
        txtProductDescription.setText(product.getDescProduto());
        txtProductAmount.setText("R$ "+ Float.toString(product.getPrecProduto()));

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(NavigationDrawerActivity.getContext()));
        String imgUrl = "https://oficinacordova.azurewebsites.net/android/rest/produto/image/";
        imageLoader.displayImage(imgUrl + product.getIdProduto(), imgShowProduct);

        Button btnAddToCart = view.findViewById(R.id.btnAddToCart);
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Adicionar ao singleton de produtos

                ArrayList<Product> products = new ArrayList<>();
                products.add(product);
                productData.setProducts(products);

                Toast toast = Toast.makeText(getContext(), "Produto adicionado ao carrinho", Toast.LENGTH_LONG);
                toast.show();

                ProductListFragment productListFragment = new ProductListFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, productListFragment).commit();

            }
        });

        Button btnBuy = view.findViewById(R.id.btnBuy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Adicionar ao singleton de produtos

                ArrayList<Product> products = new ArrayList<>();
                products.add(product);
                productData.setProducts(products);

                CartFragment cartFragment = new CartFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, cartFragment).commit();
            }
        });

        return view;
    }
}
