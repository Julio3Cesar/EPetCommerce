package com.example.epetcommerce.clients;

import com.example.epetcommerce.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IProductClient {

    @GET("/android/rest/produto")
    Call<List<Product>> GetProduto();

    @GET("/android/rest/produto/{product}")
    Call<List<Product>> GetSearchProduto(@Path("product") String productName);
}
