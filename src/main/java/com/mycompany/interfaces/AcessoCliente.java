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
        botaoConsultaExtrato = new JButton("Consultar Extrato"),
        botaoConsultaInvestimento = new JButton("Consultar Investimentos"),
        botaoConsultaCredito = new JButton("Consultar Empréstimo/Financiamento");
            
    public AcessoCliente() {
        setTitle("Sistema Bancário - Área do Cliente");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel painelMenu = new JPanel(new GridLayout(5,1,20,20));
        painelMenu.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        
        botaoTransferencia.addActionListener(e -> new Transferencia());
        botaoConsultaSaldo.addActionListener(e-> new Menu());
        botaoConsultaExtrato.addActionListener(e-> new Menu());
        botaoConsultaInvestimento.addActionListener(e-> new Menu());
        botaoConsultaCredito.addActionListener(e-> new Menu());
        
        
        painelMenu.add(botaoTransferencia);
        painelMenu.add(botaoConsultaSaldo);
        painelMenu.add(botaoConsultaExtrato);
        painelMenu.add(botaoConsultaInvestimento);
        painelMenu.add(botaoConsultaCredito);
        
        add(painelMenu);
        setVisible(true);
    }
    
}

class Transferencia extends JFrame {
    public Transferencia() {
        setTitle("Área do Cliente - Realizar Transferência");
        setSize(500, 500);
        setLocationRelativeTo(null);

        // cria painel
        JPanel painelInformacoes = new JPanel(new GridLayout(3, 2, 5, 5));

        // campos
        JTextField campoOrigem = new JTextField();
        JTextField campoDestino = new JTextField();
        JTextField campoValor = new JTextField();
        JButton botaoRealizarTransferencia = new JButton("Realizar Transferência");

        //adicao dos campos ao painel
        painelInformacoes.add(criarCampo("Informe a Conta de Origem: ", campoOrigem));
        painelInformacoes.add(criarCampo("Informe a Conta de Destino: ", campoDestino));
        painelInformacoes.add(criarCampo("Informe o valor a ser transferido: ", campoValor));


        // Adiciona os componentes na tela
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelPrincipal.add(painelInformacoes, BorderLayout.CENTER);

        add(painelPrincipal);
        add(botaoRealizarTransferencia, BorderLayout.SOUTH);
        
        setVisible(true);
    }
}
