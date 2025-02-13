/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.systembank;

/**
 *
 * @author Lara
 * @author Wilian
 */
public class Usuario {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String telefone;
    private String email;
    private String login;
    private int senha;

    // Construtor
    public Usuario(String nome, String cpf, String dataNascimento, String telefone, String email, String login, int senha) {
        this.nome = nome;
        this.cpf = validarCpf(cpf) ? cpf : "CPF inválido"; //validarCpf(cpf) que retorna true se o CPF for válido e false caso contrário.
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = validarEmail(email) ? email : "Email inválido";//validarEmail(email) que retorna true se o CPF for válido e false caso contrário.
        this.login = login;
        this.senha = senha;
    }
     
    
  }



