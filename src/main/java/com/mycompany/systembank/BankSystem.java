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
    //cria lista de usuários
    public static List<Usuario> usuarios = new ArrayList<>();
    
     //cria usuario padrão
    private static void criarUsuarioPadrao(){
       Usuario adm = new Usuario("Administrador","000.000.000-00","01/01/1999","(00) 00000-0000","adm@email.com","adm",1234);
       usuarios.add(adm);
    }
    
    public static void main(String[] args) {
       System.out.println("Iniciando o Sistema Bancario...");
       criarUsuarioPadrao();
       //chama construtor de tela
       SwingUtilities.invokeLater(()->new Menu(usuarios));
    }
    
}

