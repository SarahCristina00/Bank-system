/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */

package com.mycompany.systembank;

public class Cliente  extends Usuario{
    private Endereco endereco;
    private ContaBancaria conta;
            
            
     // Construtor que chama o super para inicializar os atributos 
    public Cliente(String nome, String cpf, String dataNascimento, String telefone, String email, String login, int senha, Endereco endereco, ContaBancaria conta) {
        
        super(nome, cpf, dataNascimento, telefone, email, login, senha);
        this.endereco = endereco;
        this.conta = conta;
    }
    
    public void consultarSaldo(){
        conta.getSaldo();
    }
}

