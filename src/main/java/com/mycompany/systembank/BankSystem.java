/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */

package com.mycompany.systembank;

import java.util.*;
import javax.swing.*;
import com.mycompany.interfaces.*;
import static com.mycompany.interfaces.Login.*;


public class BankSystem {
     
//inicializa
 public static List<Usuario> usuarios = Login.persistenciaUsuarios.carregarDados();
 public static List<Map<String, Object>> solicitacoes = Login.persistenciaSolicitacoes.carregarDados();
 public static List<Map<String, Object>> opcoesInvestimento = Login.persistenciaInvestimentos.carregarDados();
public static List<JFrame> janelasAbertas = new ArrayList<>();



 
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
 
  public static <T extends Usuario> T getUsuario(String cpf, Class<T> tipoUsuario) {
     //percorre lista para encontrar cliente
    for (Usuario usuario : usuarios) {
        if (usuario.getCpf().equals(cpf) && tipoUsuario.isInstance(usuario)) {
            return tipoUsuario.cast(usuario);
        }
    }
    return null; 
  }

      /*public static void limparExtratos() {
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
    }*/
       
    public static void main(String[] args) {

       System.out.println("Iniciando o Sistema Bancario...");
       persistenciaUsuarios.carregarDados();
       persistenciaSolicitacoes.carregarDados();
       persistenciaInvestimentos.carregarDados();
       //chama construtor de tela do login
       
       SwingUtilities.invokeLater(()->new Login());
       
    }
    
}

