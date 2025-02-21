/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */

package com.mycompany.systembank;

public class Cliente extends Usuario{
    private Endereco endereco;
    private ContaBancaria conta;
            
            
     // Construtor que chama o super para inicializar os atributos 
    public Cliente(String nome, String cpf, String dataNascimento, String telefone, String email, String login, int senha, Endereco endereco, ContaBancaria conta) {
        
        super(nome, cpf, dataNascimento, telefone, email, login, senha);
        this.endereco = endereco;
        this.conta = conta;
         super.setTipoUsuario("cliente");
    }
    
    public void consultarSaldo(){
        System.out.println("=======Consulta de Saldo=======/n");
        System.out.println("Saldo atual da conta " +conta.getConta() +": R$"+ conta.getSaldo());
    }
    
    public void consultarExtrato(){
        conta.imprimeExtrato();
    }
    
    public void investirRendaFixa(String opcao,Double valor){
            if(conta.getSaldo()<valor){
                System.out.println("Saldo insuficiente para investimento");
                return;
            }
            conta.registraTransacao(opcao, valor, conta, conta);
    }
    
     public void investirRendaVariavel(String opcao, Double valor){
            if(conta.getSaldo()<valor){
                System.out.println("Saldo insuficiente para investimento");
                return;
            }
            conta.registraTransacao(opcao, valor, conta, conta);
    }

}

