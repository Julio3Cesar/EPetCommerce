package com.example.epetcommerce.models;

public class Product {

    private float descontoPromocao;
    private String descProduto = null;
    private float precProduto;
    private int idProduto;
    private String nomeProduto;
    private float idCategoria;
    private float qtdMinEstoque;
    private boolean ativoProduto;


    // Getter Methods

    public float getDescontoPromocao() {
        return descontoPromocao;
    }

    public String getDescProduto() {
        return descProduto;
    }

    public float getPrecProduto() {
        return precProduto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public float getIdCategoria() {
        return idCategoria;
    }

    public float getQtdMinEstoque() {
        return qtdMinEstoque;
    }

    public boolean getAtivoProduto() {
        return ativoProduto;
    }

    // Setter Methods

    public void setDescontoPromocao(float descontoPromocao) {
        this.descontoPromocao = descontoPromocao;
    }

    public void setDescProduto(String descProduto) {
        this.descProduto = descProduto;
    }

    public void setPrecProduto(float precProduto) {
        this.precProduto = precProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setIdCategoria(float idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setQtdMinEstoque(float qtdMinEstoque) {
        this.qtdMinEstoque = qtdMinEstoque;
    }

    public void setAtivoProduto(boolean ativoProduto) {
        this.ativoProduto = ativoProduto;
    }
}
