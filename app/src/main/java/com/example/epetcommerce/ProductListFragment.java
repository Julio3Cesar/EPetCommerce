package com.example.epetcommerce;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.epetcommerce.clients.IProductClient;
import com.example.epetcommerce.models.Product;
import com.example.epetcommerce.view_code.ProductAdapter;
import com.example.epetcommerce.view_code.ProductCard;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends Fragment {


    RecyclerView cardContainer;
    ProductAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    public ProductListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        cardContainer = (RecyclerView) view.findViewById(R.id.cardContainer);
        cardContainer.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(NavigationDrawerActivity.getContext());
        cardContainer.setLayoutManager(layoutManager);

        CarregarLista();
        return view;
    }


    private void CarregarLista() {


        Retrofit instanciaRetrofit = new Retrofit.Builder()
                .baseUrl("https://oficinacordova.azurewebsites.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IProductClient api =
                instanciaRetrofit.create(IProductClient.class);

        Call<List<Product>> chamada = api.GetProduto();

        Callback<List<Product>> Callback = new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                List<Product> Products = response.body();
                ArrayList<ProductCard> productCards = new ArrayList<ProductCard>();

                if (response.isSuccessful() && Products != null) {
                    String imgUrl = "https://oficinacordova.azurewebsites.net/android/rest/Product/image/";
                    for (Product Product : Products) {
                        //CriarCard(Product, imgUrl);
                        productCards.add(new ProductCard(imgUrl + Product.getIdProduto(),
                                Float.toString(Product.getPrecProduto()), Product.getNomeProduto(), Product));
                    }
                    adapter = new ProductAdapter(productCards);
                    adapter.setOnItemClickListener(new ProductAdapter.IOnItemClickListener() {
                        @Override
                        public void onItemClick(ProductCard ProductCard) {
                            showDialog("Clickado", ProductCard.getTxtNomeCard());
                        }
                    });
                    cardContainer.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

                String teste = t.getMessage();


            }
        };

        chamada.enqueue(Callback);

    }


    private void showDialog(String message, String title) {
        //Declara e instancia uma fábrica de construção de diálogos
        AlertDialog.Builder builder = new AlertDialog.Builder(NavigationDrawerActivity.getContext());
        //Configura o corpo da mensagem
        builder.setMessage(message);
        //Configura o título da mensagem
        builder.setTitle(title);
        //Impede que o botão seja cancelável (possa clicar
        //em voltar ou fora para fechar)
        builder.setCancelable(false);
        //Configura um botão de OK para fechamento (um
        //outro listener pode ser configurado no lugar do "null")
        builder.setPositiveButton("OK", null);
        //Cria efetivamente o diálogo
        AlertDialog dialog = builder.create();
        //Faz com que o diálogo apareça na tela
        dialog.show();
    }

}
