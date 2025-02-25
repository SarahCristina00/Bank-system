/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */

package com.mycompany.systembank;

import java.util.*;
import javax.swing.*;
import com.mycompany.interfaces.*;
public class BankSystem {
     
//inicializa
 public static List<Usuario> usuarios = Login.persistenciaUsuarios.carregarDados();
 public static List<ContaBancaria> contasBancarias = Login.persistenciaContas.carregarDados();
 
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
   
    public static void main(String[] args) {

       System.out.println("Iniciando o Sistema Bancario...");
       
       //chama construtor de tela do login
       
       SwingUtilities.invokeLater(()->new Login());
       
    }
    
}

