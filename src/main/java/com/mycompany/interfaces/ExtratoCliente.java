/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */


package com.mycompany.interfaces;

import com.mycompany.systembank.Cliente;
import com.mycompany.systembank.Transacao;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ExtratoCliente extends JFrame {
    private Cliente cliente; 

  
    public ExtratoCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void exibirExtrato() {
        try {
            JFrame frameExtrato = new JFrame("Extrato Bancário");
            frameExtrato.setSize(600, 400);
            frameExtrato.setLocationRelativeTo(null);
            frameExtrato.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

         
            DefaultTableModel modeloTabela = new DefaultTableModel();
            modeloTabela.addColumn("Data");
            modeloTabela.addColumn("Tipo");
            modeloTabela.addColumn("Valor (R$)");
            modeloTabela.addColumn("Saldo Final (R$)");

           
            JTable tabelaExtrato = new JTable(modeloTabela);
            JScrollPane scrollPane = new JScrollPane(tabelaExtrato);

           
            List<Transacao> transacoes = cliente.getConta().getExtrato();
            System.out.println("Número de transações: " + transacoes.size()); 

            for (Transacao transacao : transacoes) {
                Object[] linha = {
                    transacao.getDataFormatada(), 
                    transacao.getTipo(),
                    transacao.getValor(),
                    transacao.getSaldoFinal()
                };
                modeloTabela.addRow(linha);
            }

       
            frameExtrato.add(scrollPane, BorderLayout.CENTER);
            frameExtrato.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace(); 
            JOptionPane.showMessageDialog(this, "Erro ao exibir extrato: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}