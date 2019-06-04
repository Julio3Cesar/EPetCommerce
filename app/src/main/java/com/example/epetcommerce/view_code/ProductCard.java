package com.example.epetcommerce.view_code;

import com.example.epetcommerce.models.Product;

public class ProductCard {

    private String imgCardUri;
    private String txtPrecoCard;
    private String txtNomeCard;

    private Product _produto;

    public ProductCard(String imgResource, String precoCard, String nomeCard, Product produto)
    {
        imgCardUri = imgResource;
        txtNomeCard = nomeCard;
        txtPrecoCard = precoCard;
        _produto = produto;
    }

    public String getImgCardUri() {
        return imgCardUri;
    }

    public String getTxtPrecoCard() {
        return txtPrecoCard;
    }

    public String getTxtNomeCard() {
        return txtNomeCard;
    }
}
