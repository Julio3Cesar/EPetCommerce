package com.example.epetcommerce.models;

public class Customer {

    private Integer idCliente;
    private String dtNascCliente = null;
    private String emailCliente;
    private String senhaCliente = null;
    private String cpfcliente;
    private String recebeNewsLetter = null;
    private String telComercialCliente = null;
    private String telResidencialCliente = null;
    private String celularCliente = null;
    private String nomeCompletoCliente;


    public Customer() {

    }

    public Customer(String email, String password) {
        this.emailCliente = email;
        this.senhaCliente = password;
    }

    public Customer(String name, String email, String password) {
        this.nomeCompletoCliente = name;
        this.emailCliente = email;
        this.senhaCliente = password;
    }

    // Getter Methods

    public Integer getIdCliente() {
        return idCliente;
    }

    public String getDtNascCliente() {
        return dtNascCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public String getSenhaCliente() {
        return senhaCliente;
    }

    public String getCpfcliente() {
        return cpfcliente;
    }

    public String getRecebeNewsLetter() {
        return recebeNewsLetter;
    }

    public String getTelComercialCliente() {
        return telComercialCliente;
    }

    public String getTelResidencialCliente() {
        return telResidencialCliente;
    }

    public String getCelularCliente() {
        return celularCliente;
    }

    public String getNomeCompletoCliente() {
        return nomeCompletoCliente;
    }

    // Setter Methods

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public void setDtNascCliente(String dtNascCliente) {
        this.dtNascCliente = dtNascCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public void setSenhaCliente(String senhaCliente) {
        this.senhaCliente = senhaCliente;
    }

    public void setCpfcliente(String cpfcliente) {
        this.cpfcliente = cpfcliente;
    }

    public void setRecebeNewsLetter(String recebeNewsLetter) {
        this.recebeNewsLetter = recebeNewsLetter;
    }

    public void setTelComercialCliente(String telComercialCliente) {
        this.telComercialCliente = telComercialCliente;
    }

    public void setTelResidencialCliente(String telResidencialCliente) {
        this.telResidencialCliente = telResidencialCliente;
    }

    public void setCelularCliente(String celularCliente) {
        this.celularCliente = celularCliente;
    }

    public void setNomeCompletoCliente(String nomeCompletoCliente) {
        this.nomeCompletoCliente = nomeCompletoCliente;
    }
}
