/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.systembank;

/**
 *
 * @author Wilian
 */
public class Caixa extends Usuario {

    // Construtor 
    public Caixa(String nome, String cpf, String dataNascimento, String telefone, 
                 String email, String login, int senha) {
        super(nome, cpf, dataNascimento, telefone, email, login, senha);
    }

    public void processarSaque(Cliente cliente, Double valor) {
        System.out.println("Saque de R$" + valor + 
                           " processado para o cliente: " + cliente.getNome());
    }

    public void processarDeposito(Cliente cliente, Double valor) {
        System.out.println("Depósito de R$" + valor + 
                           " processado para o cliente: " + cliente.getNome());
    }

    public void processarTransferencia(Cliente cliente, ContaBancaria destino, Double valor) {
        System.out.println("Transferência de R$" + valor + 
                           " do cliente " + cliente.getNome());
    }
}
