package com.example.epetcommerce.database;

import com.example.epetcommerce.models.Product;

import java.util.List;

public class ProductData {
    private static ProductData productData;

    private List<Product> products;
    private Product activeProduct;

    private ProductData(){

    }
    public static ProductData getInstance(){
        if(productData==null){
            productData=new ProductData();
        }
        return productData;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product getActiveProduct() {
        return activeProduct;
    }

    public void setActiveProduct(Product activeProduct) {
        this.activeProduct = activeProduct;
    }
}