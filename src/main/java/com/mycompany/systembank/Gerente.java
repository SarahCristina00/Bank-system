/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */

package com.mycompany.systembank;

public class Gerente extends Usuario {

    // Construtor 
    public Gerente(String nome, String cpf, String dataNascimento, String telefone, String email, int senha) {
        super(nome, cpf, dataNascimento, telefone, email, senha);
         super.setTipoUsuario("gerente");
    }
   
    //GERENTE APOS FAZER O LOGIN NO SISTEMA 

    public void apoiarMovimentacao(Cliente cliente, Double valor,  String tipoMovimentacao) {
       // Condicional para verificar se a movimentação é um saque acima de 1 milhão ou uma transferência
        if (tipoMovimentacao.equals("saque") && valor > 1000000) {
            System.out.println("Gerente auxiliando no saque de R$" + valor + " para o cliente: " + cliente.getNome());
            // O cliente confirma a operação (AQUI PEDE A SENHA DO CLIENTE )
            System.out.println("Saque autorizado. A operação foi concluída com sucesso.");
        } else if (tipoMovimentacao.equals("transferencia")) {
            System.out.println("Gerente auxiliando na transferência de R$" + valor + " para o cliente: " + cliente.getNome());
            // AQUI PEDE A SENHA DO CLIENTE
            System.out.println("Transferência realizada com sucesso.");
        } else {
            System.out.println("Apoio de movimentação não permitido para este tipo de operação.");
        }
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
