/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.systembank;

/**
 *
 * @author Lara
 */
class Endereco {
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private int numero;
    private String complemento;
    private int cep;

     public Endereco(String rua, String bairro, String cidade, String estado, int numero, String complemento, int cep) {
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
    }

    public String getRua() { return rua; } 
    public void setRua(String rua) { this.rua = rua; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) {this.bairro = bairro;}

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEstado() { return estado; } 
    public void setEstado(String estado) { this.estado = estado; }

    public int  getNumero() { return numero; } 
    public void setNumero(int numero) { this.numero = numero; }

    public String getComplemento() { return complemento; } 
    public void setComplemento(String complemento) { this.complemento = complemento; } 

    public int getCep() { return cep;} 
    public void setCep(int cep) { this.cep = cep; }
    
}

