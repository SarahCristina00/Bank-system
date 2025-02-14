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
    
}

