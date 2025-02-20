/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */

package com.mycompany.systembank;

import java.util.*;
import javax.swing.*;
import com.mycompany.interfaces.*;
import com.mycompany.persistencia.*;

public class BankSystem {
     
//carrega lista de usuários
 public static List<Usuario> usuarios;
   
    public static void main(String[] args) {
       System.out.println("Iniciando o Sistema Bancario...");
       
       //instancia arquivo para carregar os dados
        PersistenciaUsuarios persistencia = new PersistenciaUsuarios();
        
        //carrega os dados dos usuarios
        usuarios = persistencia.carregarDados();
                   
       //chama construtor de tela
       SwingUtilities.invokeLater(()->new Menu(usuarios));
       
        //atualiza os dados após execução
       persistencia.salvarDados(usuarios);
    }
    
}

