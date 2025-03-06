/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */

package com.mycompany.systembank;

public class Cliente extends Usuario {
    private Endereco endereco;
    private ContaBancaria conta;

   
    public Cliente(String nome, String cpf, String dataNascimento, String telefone, String email, int senha, Endereco endereco, ContaBancaria conta) {
        super(nome, cpf, dataNascimento, telefone, email, senha);
        

        if (conta == null) {
            throw new IllegalArgumentException("A conta bancária não pode ser nula.");
        }
        
        this.endereco = endereco;
        this.conta = conta;
        super.setTipoUsuario("cliente");
    }

 
    public ContaBancaria getConta() {
        return conta;
    }


    public Endereco getEndereco() {
        return endereco;
    }


    public boolean transferir(double valor, Cliente destinatario) {
        if (destinatario == null || destinatario.getConta() == null) {
            System.out.println("Erro: Conta do destinatário inválida.");
            return false;
        }

        if (valor <= 0) {
            System.out.println("Erro: O valor da transferência deve ser positivo.");
            return false;
        }

        return this.conta.transfereSaldo(valor, destinatario.getConta());
    }


    public void consultarSaldo() {
        if (conta == null) {
            System.out.println("Erro: Conta bancária não foi inicializada.");
            return;
        }

        System.out.println("======= Consulta de Saldo =======");
        System.out.println("Saldo atual da conta " + conta.getConta() + ": R$" + conta.getSaldo());
    }


    public void consultarExtrato() {
        if (conta == null) {
            System.out.println("Erro: Conta bancária não foi inicializada.");
            return;
        }

        conta.imprimeExtrato();
    }


    public void investirRendaFixa(String opcao, Double valor) {
        if (conta == null) {
            System.out.println("Erro: Conta bancária não foi inicializada.");
            return;
        }

        if (valor <= 0) {
            System.out.println("Erro: O valor do investimento deve ser positivo.");
            return;
        }

        if (conta.getSaldo() < valor) {
            System.out.println("Saldo insuficiente para investimento.");
            return;
        }

        conta.registraTransacao(opcao, valor, conta, conta);
        System.out.println("Investimento em renda fixa realizado com sucesso.");
    }


    public void investirRendaVariavel(String opcao, Double valor) {
        if (conta == null) {
            System.out.println("Erro: Conta bancária não foi inicializada.");
            return;
        }

        if (valor <= 0) {
            System.out.println("Erro: O valor do investimento deve ser positivo.");
            return;
        }

        if (conta.getSaldo() < valor) {
            System.out.println("Saldo insuficiente para investimento.");
            return;
        }

        conta.registraTransacao(opcao, valor, conta, conta);
        System.out.println("Investimento em renda variável realizado com sucesso.");
    }
}