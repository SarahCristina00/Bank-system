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
 public static List<Usuario> usuarios;
   
    public static void main(String[] args) {
       System.out.println("Iniciando o Sistema Bancario...");
                          
       //chama construtor de tela
       SwingUtilities.invokeLater(()->new Menu());
       
    }
    
}

