/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */

package com.mycompany.systembank;

public class Caixa extends Usuario {
    
    // Construtor 
    public Caixa(String nome, String cpf, String dataNascimento, String telefone, 
                 String email, int senha) {
        super(nome, cpf, dataNascimento, telefone, email, senha);
         super.setTipoUsuario("caixa");
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
