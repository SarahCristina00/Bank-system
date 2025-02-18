package com.mycompany.systembank;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Wilian
 */
public class Gerente extends Usuario {

    // Construtor 
    public Gerente(String nome, String cpf, String dataNascimento, String telefone, String email, String login, int senha) {
        super(nome, cpf, dataNascimento, telefone, email, login, senha);
    }

    public void apoiarMovimentacao(Cliente cliente, Double valor) {
        System.out.println("Gerente apoiando movimentação de R$" + valor + 
                " para o cliente: " + cliente.getNome());
    }

    public void cadastrarOpcaoRendaFixa(String descricao, Double taxa, Double rentabilidade) {
        System.out.println("Opção de renda fixa cadastrada: " + descricao + 
                           " | Taxa: " + taxa + "%" + 
                           " | Rentabilidade esperada: " + rentabilidade + "%");
    }

    public void cadastrarOpcaoRendaVariavel(String descricao, Double risco, Double rentabilidade) {
        System.out.println("Opção de renda variável cadastrada: " + descricao + 
                           " | Risco: " + risco + 
                           " | Rentabilidade esperada: " + rentabilidade + "%");
    }

    public void avaliarCredito(Cliente cliente, Double valor) {
        System.out.println("Gerente avaliando crédito de R$" + valor + 
                           " para o cliente: " + cliente.getNome());
    }
}
