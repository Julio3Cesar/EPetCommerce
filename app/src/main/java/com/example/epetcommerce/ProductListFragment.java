package com.example.epetcommerce;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.epetcommerce.clients.IProductClient;
import com.example.epetcommerce.database.ProductData;
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
    SearchView searchProduct;
    private ProgressBar progressBarProductList;

    public ProductListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        progressBarProductList = view.findViewById(R.id.progressBarProductList);
        cardContainer = (RecyclerView) view.findViewById(R.id.cardContainer);
        cardContainer.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(NavigationDrawerActivity.getContext());
        cardContainer.setLayoutManager(layoutManager);
        adapter = new ProductAdapter(new ArrayList<ProductCard>());
        cardContainer.setAdapter(adapter);
        CarregarLista("");


        //progressBarProductList.setVisibility(View.GONE);
        searchProduct = view.findViewById(R.id.searchProduct);
        searchProduct.setQueryHint("O que procura?");
        searchProduct.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String newText) {
                //Log.e("onQueryTextChange", "called");
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                CarregarLista(query);

                // Do your task here

                return false;
            }

        });

        return view;
    }


    private void CarregarLista(String search) {

        progressBarProductList.setVisibility(View.VISIBLE);

        Retrofit instanciaRetrofit = new Retrofit.Builder()
                .baseUrl("https://oficinacordova.azurewebsites.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IProductClient api =
                instanciaRetrofit.create(IProductClient.class);


        Call<List<Product>> chamada;

        if(search.equals("")){
            chamada = api.GetProduto();
        }
        else{
            chamada = api.GetSearchProduto(search);
        }

            Callback<List<Product>> Callback = new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                    List<Product> Products = response.body();
                    final ArrayList<ProductCard> productCards = new ArrayList<ProductCard>();

                    if (response.isSuccessful() && Products != null) {

                        for (Product Product : Products) {
                            //CriarCard(Product, imgUrl);
                            productCards.add(new ProductCard(Product));
                        }

                        adapter = new ProductAdapter(productCards);
                        adapter.setOnItemClickListener(new ProductAdapter.IOnItemClickListener() {
                            @Override
                            public void onItemClick(ProductCard ProductCard) {
                                ProductData productData = ProductData.getInstance();
                                productData.setActiveProduct(ProductCard.GetProduto());

                                ShowProductFragment fragProduct = new ShowProductFragment();
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragProduct).commit();

                            }
                        });
                        cardContainer.setAdapter(adapter);
                        progressBarProductList.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {

                    String teste = t.getMessage();

                    progressBarProductList.setVisibility(View.GONE);
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
