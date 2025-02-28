package com.mycompany.interfaces;

import com.mycompany.systembank.*;
import javax.swing.*;
import java.awt.*;
import static com.mycompany.systembank.BankSystem.usuarios;


public class AcessoGerente extends JFrame {
     private static Gerente gerente;
    
    public AcessoGerente() {
       
        
        setTitle("Acesso Gerente");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel painel = new JPanel(new GridLayout(4, 1, 10, 10));
        
        JButton btnApoiarMovimentacao = new JButton("Apoiar Movimentação");
        JButton btnCadastrarRendaFixa = new JButton("Cadastrar Renda Fixa");
        JButton btnCadastrarRendaVariavel = new JButton("Cadastrar Renda Variável");
        JButton btnAvaliarCredito = new JButton("Avaliar Crédito");
        
        btnApoiarMovimentacao.addActionListener(e -> apoiarMovimentacao());
        btnCadastrarRendaFixa.addActionListener(e -> cadastrarRendaFixa());
        btnCadastrarRendaVariavel.addActionListener(e -> cadastrarRendaVariavel());
        btnAvaliarCredito.addActionListener(e -> avaliarCredito());
        
        painel.add(btnApoiarMovimentacao);
        painel.add(btnCadastrarRendaFixa);
        painel.add(btnCadastrarRendaVariavel);
        painel.add(btnAvaliarCredito);
        
        add(painel);
        setVisible(true);
    }
    
    private void apoiarMovimentacao() {
        String cpfCliente = JOptionPane.showInputDialog("Informe o CPF do cliente:");
        Cliente cliente = encontrarClientePorCPF(cpfCliente);
        
        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String tipoMovimentacao = JOptionPane.showInputDialog("Digite o tipo de movimentação (saque/transferencia):");
        double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da movimentação:"));
        
        // Verifica a senha do cliente
        int senhaCliente = Integer.parseInt(JOptionPane.showInputDialog("Digite a senha do cliente para confirmar:"));
        if (senhaCliente == cliente.getSenha()) {
            gerente.apoiarMovimentacao(cliente, valor, tipoMovimentacao);
            JOptionPane.showMessageDialog(this, "Operação realizada com sucesso.");
        } else {
            JOptionPane.showMessageDialog(this, "Senha incorreta! Operação cancelada.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cadastrarRendaFixa() {
        String descricao = JOptionPane.showInputDialog("Descrição da renda fixa:");
        double taxa = Double.parseDouble(JOptionPane.showInputDialog("Taxa (%):"));
        double rentabilidade = Double.parseDouble(JOptionPane.showInputDialog("Rentabilidade esperada (%):"));
        int prazoMinimo = Integer.parseInt(JOptionPane.showInputDialog("Prazo mínimo (meses):"));
        int prazoMaximo = Integer.parseInt(JOptionPane.showInputDialog("Prazo máximo (meses):"));
        
        gerente.cadastrarOpcaoRendaFixa(descricao, taxa, rentabilidade, prazoMinimo, prazoMaximo);
        JOptionPane.showMessageDialog(this, "Renda fixa cadastrada com sucesso.");
    }
    
    private void cadastrarRendaVariavel() {
        String descricao = JOptionPane.showInputDialog("Descrição da renda variável:");
        double risco = Double.parseDouble(JOptionPane.showInputDialog("Nível de risco (1-10):"));
        double rentabilidade = Double.parseDouble(JOptionPane.showInputDialog("Rentabilidade esperada (%):"));
        
        gerente.cadastrarOpcaoRendaVariavel(descricao, risco, rentabilidade);
        JOptionPane.showMessageDialog(this, "Renda variável cadastrada com sucesso.");
    }
    
    private void avaliarCredito() {
        String cpfCliente = JOptionPane.showInputDialog("Informe o CPF do cliente:");
        Cliente cliente = encontrarClientePorCPF(cpfCliente);
        
        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do crédito solicitado:"));
        gerente.avaliarCredito(cliente, valor);
        JOptionPane.showMessageDialog(this, "Crédito avaliado com sucesso.");
    }
    
    private Cliente encontrarClientePorCPF(String cpf) {
        for (Usuario usuario:usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                return (Cliente) usuario;
            }
        }
        return null;
    }
}
