package com.example.epetcommerce;


import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.epetcommerce.database.ProductData;
import com.example.epetcommerce.models.Product;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {


    public CartFragment() {
        // Required empty public constructor
    }

    private Button btnToPayment;
    private LinearLayout cartContainer;
    private TextView txtTotalProducts;
    private TextView txtValueTotalProducts;
    private Button btnCleanCart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_cart, container, false);

        txtTotalProducts = view.findViewById(R.id.txtTotalProducts);
        txtValueTotalProducts = view.findViewById(R.id.txtValueTotalProducts);
        btnToPayment = view.findViewById(R.id.btnToPayment);
        btnToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentFragment paymentFragment = new PaymentFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, paymentFragment).commit();
            }
        });

        cartContainer = view.findViewById(R.id.cartContainer);
        btnCleanCart = view.findViewById(R.id.btnCleanCart);

        btnCleanCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductData.getInstance().getProducts().clear();
                ProductListFragment productListFragment = new ProductListFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, productListFragment).commit();
            }
        });

        List<Product> products = ProductData.getInstance().getProducts();

        if(products != null)
        {

            txtTotalProducts.setText(Integer.toString(products.size()) + " Item(s) no carrinho");
        }


        if(products == null)
        {
            products = new ArrayList<Product>();
            Product product = new Product();
            product.setIdProduto(0);

            product.setNomeProduto("Nenhum produto no carrinho");
            products.add(product);
        }
        float total = 0f;
        for(Product product : products)
        {
            addItem(product);
            total += product.getPrecProduto();
        }
        txtValueTotalProducts.setText("Valor Total: R$"+total);

        return view;
    }

    private void addItem(Product produto)
    {
        CardView card = (CardView)LayoutInflater.from(getActivity())
                .inflate(R.layout.product_card, cartContainer, false);

        ImageView imgProduct = card.findViewById(R.id.imgProductCard);
        TextView txtPreco = card.findViewById(R.id.txtPrecoProductCard);
        TextView txtNome = card.findViewById(R.id.txtNomeProductCard);

        txtNome.setText(produto.getNomeProduto());
        txtPreco.setText("R$ "+ produto.getPrecProduto());

        String imgUrl = "https://oficinacordova.azurewebsites.net/android/rest/produto/image/";
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(NavigationDrawerActivity.getContext()));

        imageLoader.displayImage(imgUrl + produto.getIdProduto(), imgProduct);

        cartContainer.addView(card);
    }

}
