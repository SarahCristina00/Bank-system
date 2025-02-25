/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */

package com.mycompany.interfaces;

import static com.mycompany.interfaces.Login.criarCampo;
import com.mycompany.persistencia.PersistenciaUsuarios;
import com.mycompany.systembank.*;
import static com.mycompany.systembank.BankSystem.usuarios;
import java.awt.*;
import javax.swing.*;

public class AcessoCliente extends JFrame {
    
        private JButton botaoTransferencia = new JButton("Realizar Transferência"),
        botaoConsultaSaldo = new JButton("Consultar Saldo"),
        botaoConsultaExtrato = new JButton("Consultar Extrato");
            
    public AcessoCliente() {
        setTitle("Sistema Bancário - Área do Cliente");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel painelMenu = new JPanel(new GridLayout(5,1,20,20));
        painelMenu.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        
        botaoTransferencia.addActionListener(e -> new Menu());
        botaoConsultaSaldo.addActionListener(e-> new Menu());
        botaoConsultaExtrato.addActionListener(e-> new Menu());
        
        
        painelMenu.add(botaoTransferencia);
        painelMenu.add(botaoConsultaSaldo);
        painelMenu.add(botaoConsultaExtrato);
        
        add(painelMenu);
        setVisible(true);
    }
}
