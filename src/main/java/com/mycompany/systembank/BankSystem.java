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

    public static List<Usuario> usuarios = Login.persistenciaUsuarios.carregarDados();
    public static List<ContaBancaria> contasBancarias = Login.persistenciaContas.carregarDados();

    
    public static Cliente getCliente(int numeroConta) {
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Cliente) {
                Cliente cliente = (Cliente) usuario;
                ContaBancaria conta = cliente.getConta();
                if (conta != null && conta.getConta() == numeroConta) {
                    return cliente;
                }
            }
        }
        return null; 
    }

  
    public static Cliente getClienteCpf(String cpf) {
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Cliente) {
                Cliente cliente = (Cliente) usuario;
                if (cliente.getCpf().equals(cpf)) {
                    return cliente;
                }
            }
        }
        return null; 
    }

    public static void main(String[] args) {
        System.out.println("Iniciando o Sistema Bancário...");

        
        if (usuarios == null || contasBancarias == null) {
            System.err.println("Erro ao carregar dados de usuários ou contas.");
            return;
        }

   
       
        SwingUtilities.invokeLater(() -> new Login());
    }
}
