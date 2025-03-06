/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */

package com.mycompany.systembank;

import java.util.*;
import javax.swing.*;
import com.mycompany.interfaces.*;
import com.mycompany.persistencia.PersistenciaUsuarios;
public class BankSystem {
     
//inicializa
 public static List<ContaBancaria> contasBancarias = Login.persistenciaContas.carregarDados();
 public static List<Usuario> usuarios = Login.persistenciaUsuarios.carregarDados();
 
 public static Cliente getCliente(int numeroConta) {
     //percorre lista para encontrar cliente
    for (Usuario usuario : usuarios) {
        if(!(usuario instanceof Cliente)) {
        } else {
            Cliente cliente = (Cliente) usuario;
            if (cliente.getConta().getConta() == numeroConta) {
                return cliente;
            }
        }
    }
    return null; 
}
 
  public static Cliente getClienteCpf(String cpf) {
     //percorre lista para encontrar cliente
    for (Usuario usuario : usuarios) {
        if(!(usuario instanceof Cliente)) {
        } else {
            Cliente cliente = (Cliente) usuario;
            if(cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
    }
    return null; 
  }

      public static void limparExtratos() {
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Cliente) {
                Cliente cliente = (Cliente) usuario;
                cliente.getConta().getExtrato().clear(); // Limpa a lista de transações
                cliente.getConta().setSaldo(0);//zera o saldo
            }
        }
        PersistenciaUsuarios persistenciaUsuarios = new PersistenciaUsuarios(); // Cria uma instância
        persistenciaUsuarios.salvarDados(usuarios); // Chama o método de instância
        System.out.println("Extratos de todas as contas limpos.");
    }
       
    public static void main(String[] args) {

       System.out.println("Iniciando o Sistema Bancario...");
       limparExtratos();
       //chama construtor de tela do login
       
       SwingUtilities.invokeLater(()->new Login());
       
    }
    
}

