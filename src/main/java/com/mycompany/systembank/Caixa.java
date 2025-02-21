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
        if (cliente.getConta().getSaldo() >= valor) {  // Acessando saldo pela conta para verificar se o cliente tem saldo suficiente para o saque
            cliente.getConta().setSaldo(cliente.getConta().getSaldo() - valor);// atualização do saldo do cliente apos o saque
            System.out.println("Saque de R$" + valor + 
                               " realizado com sucesso para o cliente: " + cliente.getNome());
        } else {
            System.out.println("Saldo insuficiente para saque do cliente: " + cliente.getNome());
        }
    }

    public void processarDeposito(Cliente cliente, Double valor) {
       if (valor > 0) {  // se o valor do deposito for maior que 0 
            cliente.getConta().setSaldo(cliente.getConta().getSaldo() + valor);// obtem o saldo atual do cliente e adiciona o valor do deposito 
            System.out.println("Depósito de R$" + valor + 
                               " realizado com sucesso para o cliente: " + cliente.getNome());
        } else {
            System.out.println("Valor de depósito inválido para o cliente: " + cliente.getNome());
        }
    }

    public void processarTransferencia(Cliente cliente, ContaBancaria destino, Double valor) {
       if (cliente.getConta().getSaldo() >= valor) {  //Verifica saldo atual do cliente se é maior que o valor da transferencia
            cliente.getConta().setSaldo(cliente.getConta().getSaldo() - valor);// retira o valor de trasferencia da  conta do cliente 
          //  destino.receberTransferencia(valor);// PRA ONDE VAMOS TRANSFERIR O DINHEIRO? PARA UMA OUTRA CONTA QUE CADASTRAMOS? PARA UMA CONTA QUE CRIAREMOS SÓ PRA RECEBER A TRABSFERENCIA?
            System.out.println("Transferência de R$" + valor + 
                               " realizada com sucesso do cliente " + cliente.getNome() + 
                               " para a conta de destino.");
        } else {
            System.out.println("Saldo insuficiente para transferência do cliente: " + cliente.getNome());
        }
    }
}
