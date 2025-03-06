/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */
package com.mycompany.interfaces;

import java.text.NumberFormat;
import java.util.Locale;
import static com.mycompany.interfaces.Login.criarCampo;
import com.mycompany.systembank.*;
import java.awt.*;
import javax.swing.*;

public class AcessoCaixa extends JFrame {

    private JButton botaoSaque = new JButton("Realizar Saque"),
            botaoDeposito = new JButton("Realizar Depósito"),
            botaoTransferencia = new JButton("Processar Transferência"),
            botaoSair = new JButton("Sair");
           

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
         botaoSair.addActionListener(e -> new Login());
       

        painelMenu.add(botaoSaque);
        painelMenu.add(botaoDeposito);
        painelMenu.add(botaoTransferencia);
        painelMenu.add(botaoSair);
        
        

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
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        JFormattedTextField campoValor = new JFormattedTextField(formatoMoeda);
        campoValor.setValue(0);
        JButton botaoConfirmar = new JButton("Confirmar Saque");

        painel.add(criarCampo("Conta do Cliente: ", campoConta));
        painel.add(criarCampo("Valor: ", campoValor));
        
        botaoConfirmar.addActionListener(e -> {
            int conta = Integer.parseInt(campoConta.getText());
            Number valorNumero = (Number) campoValor.getValue();
            double valor =valorNumero.doubleValue();
            Cliente cliente = BankSystem.getCliente(conta);
            Caixa caixa = new Caixa("Caixa", "", "", "", "", 0);
            caixa.processarSaque(cliente, valor);
            // salva os dados após a transação
                    Login.persistenciaContas.salvarDados(BankSystem.contasBancarias);
               
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
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        JFormattedTextField campoValor = new JFormattedTextField(formatoMoeda);
        campoValor.setValue(0);
        JButton botaoConfirmar = new JButton("Confirmar Depósito");

        painel.add(criarCampo("Conta do Cliente: ", campoConta));
        painel.add(criarCampo("Valor: ", campoValor));
        
        botaoConfirmar.addActionListener(e -> {
            try{
                int conta = Integer.parseInt(campoConta.getText());
                //converte valor informado na tela
                Number valorNumero = (Number) campoValor.getValue();
                double valor =valorNumero.doubleValue();
                Cliente cliente = BankSystem.getCliente(conta);
                if (cliente!= null){
                    Caixa caixa = new Caixa("Caixa", "", "", "", "", 0);
                    caixa.processarDeposito(cliente, valor);
                    // salva os dados após a transação
                        Login.persistenciaContas.salvarDados(BankSystem.contasBancarias);
                }else{
                  JOptionPane.showMessageDialog(this, "Conta do cliente não existe.");  
                }
               
            }catch(Exception ex){
                ex.printStackTrace();
            }
            
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
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        JFormattedTextField campoValor = new JFormattedTextField(formatoMoeda);
        campoValor.setValue(0);
        JButton botaoConfirmar = new JButton("Confirmar Transferência");

        painel.add(criarCampo("Conta de Origem: ", campoOrigem));
        painel.add(criarCampo("Conta de Destino: ", campoDestino));
        painel.add(criarCampo("Valor: ", campoValor));

        botaoConfirmar.addActionListener(e -> {
            int contaOrigem = Integer.parseInt(campoOrigem.getText());
            int contaDestino = Integer.parseInt(campoDestino.getText());
            Number valorNumero = (Number) campoValor.getValue();
            double valor =valorNumero.doubleValue();
            Cliente clienteOrigem = BankSystem.getCliente(contaOrigem);
            Cliente clienteDestino = BankSystem.getCliente(contaDestino);
            // se as contas de origem e destino existem
            if (clienteOrigem != null && clienteDestino != null) {
                // realiza a transferência e registra a transação
                if (clienteOrigem.getConta().transfereSaldo(valor, clienteDestino.getConta())) {
                    JOptionPane.showMessageDialog(this, "Transferência realizada com sucesso!");
                    // salva os dados após a transação
                    Login.persistenciaContas.salvarDados(BankSystem.contasBancarias);
                } else {
                    JOptionPane.showMessageDialog(this, "Saldo insuficiente para a transferência.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Uma das contas de destino ou origem não existe.");
            }
        });

        add(painel, BorderLayout.CENTER);
        add(botaoConfirmar, BorderLayout.SOUTH);
        setVisible(true);
    }
}
