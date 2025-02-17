/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.systembank;

/**
 *
 * @author Lara
 */
public class Cliente  extends Usuario{
            private Endereco endereco;
            private ContaBancaria conta;
            
            
             // Construtor que chama o super para inicializar os atributos 
    public Cliente(String nome, String cpf, String dataNascimento, String telefone, String email, String login, int senha, Endereco endereco, ContaBancaria conta) {
        
        super(nome, cpf, dataNascimento, telefone, email, login, senha);
        this.endereco = endereco;
        this.conta = conta;
    }
    
     // Getters e Setters
    public Endereco getEndereco(){
        return endereco; 
    }
    public void setEndereco(Endereco endereco){
        this.endereco = endereco; 
    }

    public ContaBancaria getConta(){
        return conta; 
    }
    public void setConta(ContaBancaria conta){
        this.conta = conta; 
    }
}

