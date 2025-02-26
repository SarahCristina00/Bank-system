/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces;
import static com.mycompany.interfaces.Login.criarCampo;
import com.mycompany.systembank.*;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Wilian
 */
public class AcessoCaixa extends JFrame {

    private JButton botaoSaque = new JButton("Realizar Saque"),
            botaoDeposito = new JButton("Realizar Depósito"),
            botaoTransferencia = new JButton("Processar Transferência"),
            botaoVoltar = new JButton("Voltar ao Menu");

    public AcessoCaixa() {
        setTitle("Sistema Bancário - Área do Caixa");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painelMenu = new JPanel(new GridLayout(4, 1, 20, 20));
        painelMenu.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        botaoSaque.addActionListener(e -> new Saque());
        botaoDeposito.addActionListener(e -> new Deposito());
        botaoTransferencia.addActionListener(e -> new TransferenciaCaixa());
        botaoVoltar.addActionListener(e -> new Menu());

        painelMenu.add(botaoSaque);
        painelMenu.add(botaoDeposito);
        painelMenu.add(botaoTransferencia);
        painelMenu.add(botaoVoltar);

        add(painelMenu);
        setVisible(true);
    }
}

class Saque extends JFrame {
    public Saque() {
        setTitle("Área do Caixa - Realizar Saque");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(2, 2, 20, 20));
        JTextField campoConta = new JTextField();
        JTextField campoValor = new JTextField();
        JButton botaoConfirmar = new JButton("Confirmar Saque");

        painel.add(criarCampo("Conta do Cliente: ", campoConta));
        painel.add(criarCampo("Valor: ", campoValor));
        
        botaoConfirmar.addActionListener(e -> {
            int conta = Integer.parseInt(campoConta.getText());
            double valor = Double.parseDouble(campoValor.getText());
            Cliente cliente = BankSystem.getCliente(conta);
            Caixa caixa = new Caixa("Caixa", "", "", "", "", 0);
            caixa.processarSaque(cliente, valor);
        });

        add(painel, BorderLayout.CENTER);
        add(botaoConfirmar, BorderLayout.SOUTH);
        setVisible(true);
    }
}

class Deposito extends JFrame {
    public Deposito() {
        setTitle("Área do Caixa - Realizar Depósito");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(2, 2, 20, 20));
        JTextField campoConta = new JTextField();
        JTextField campoValor = new JTextField();
        JButton botaoConfirmar = new JButton("Confirmar Depósito");

        painel.add(criarCampo("Conta do Cliente: ", campoConta));
        painel.add(criarCampo("Valor: ", campoValor));
        
        botaoConfirmar.addActionListener(e -> {
            int conta = Integer.parseInt(campoConta.getText());
            double valor = Double.parseDouble(campoValor.getText());
            Cliente cliente = BankSystem.getCliente(conta);
            Caixa caixa = new Caixa("Caixa", "", "", "", "", 0);
            caixa.processarDeposito(cliente, valor);
        });

        add(painel, BorderLayout.CENTER);
        add(botaoConfirmar, BorderLayout.SOUTH);
        setVisible(true);
    }
}

class TransferenciaCaixa extends JFrame {
    public TransferenciaCaixa() {
        setTitle("Área do Caixa - Processar Transferência");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 2, 20, 20));
        JTextField campoOrigem = new JTextField();
        JTextField campoDestino = new JTextField();
        JTextField campoValor = new JTextField();
        JButton botaoConfirmar = new JButton("Confirmar Transferência");

        painel.add(criarCampo("Conta de Origem: ", campoOrigem));
        painel.add(criarCampo("Conta de Destino: ", campoDestino));
        painel.add(criarCampo("Valor: ", campoValor));

        botaoConfirmar.addActionListener(e -> {
            int contaOrigem = Integer.parseInt(campoOrigem.getText());
            int contaDestino = Integer.parseInt(campoDestino.getText());
            double valor = Double.parseDouble(campoValor.getText());
            Cliente clienteOrigem = BankSystem.getCliente(contaOrigem);
            Cliente clienteDestino = BankSystem.getCliente(contaDestino);
            Caixa caixa = new Caixa("Caixa", "", "", "", "", 0);
            caixa.processarTransferencia(clienteOrigem, clienteDestino.getConta(), valor);
        });

        add(painel, BorderLayout.CENTER);
        add(botaoConfirmar, BorderLayout.SOUTH);
        setVisible(true);
    }
}
