package com.example.epetcommerce.view_code;

import com.example.epetcommerce.models.Product;

public class ProductCard {

    private Product product;

    public ProductCard(Product produto)
    {
        product = produto;
    }


    public Product GetProduto() {return product;}
}
