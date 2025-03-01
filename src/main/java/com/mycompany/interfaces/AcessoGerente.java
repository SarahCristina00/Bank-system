package com.mycompany.interfaces;

import com.mycompany.systembank.*;
import javax.swing.*;
import java.awt.*;
import static com.mycompany.systembank.BankSystem.usuarios;


public class AcessoGerente extends JFrame {
     private static Gerente gerente;
     private JButton botaoApoioMovimentacao = new JButton("Apoiar Movimentação"),
                   botaoCadastrarRendaFixa = new JButton("Cadastrar Renda Fixa"),
                   botaoCadastrarRendaVariavel = new JButton("Cadastrar Renda Variável"),
                   botaoAvaliarCredito = new JButton("Avaliar Crédito");

    public AcessoGerente() {
        setTitle("Sistema Bancário - Área do Gerente");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painelMenu = new JPanel(new GridLayout(4, 1, 20, 20));
        painelMenu.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        botaoApoioMovimentacao.addActionListener(e -> new ApoiarMovimentacao());
        botaoCadastrarRendaFixa.addActionListener(e -> new CadastrarRendaFixa());
        botaoCadastrarRendaVariavel.addActionListener(e -> new CadastrarRendaVariavel());
        botaoAvaliarCredito.addActionListener(e -> new AvaliarCredito());

        painelMenu.add(botaoApoioMovimentacao);
        painelMenu.add(botaoCadastrarRendaFixa);
        painelMenu.add(botaoCadastrarRendaVariavel);
        painelMenu.add(botaoAvaliarCredito);

        add(painelMenu);
        setVisible(true);
    }

    
   class ApoiarMovimentacao extends JFrame {
    public ApoiarMovimentacao() {
        String cpfCliente = JOptionPane.showInputDialog("Informe o CPF do cliente:");
        Usuario usuario = encontrarUsuarioPorCPF(cpfCliente);

        
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String tipoMovimentacao = JOptionPane.showInputDialog("Digite o tipo de movimentação (saque/transferencia):");
        double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da movimentação:"));
        
        // Verifica a senha do cliente
        int senhaCliente = Integer.parseInt(JOptionPane.showInputDialog("Digite a senha do cliente para confirmar:"));
        if (senhaCliente == usuario.getSenha()) {
            gerente.apoiarMovimentacao(usuario, valor, tipoMovimentacao);
            JOptionPane.showMessageDialog(this, "Operação realizada com sucesso.");
        } else {
            JOptionPane.showMessageDialog(this, "Senha incorreta! Operação cancelada.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
      }
    }
    
   class CadastrarRendaFixa extends JFrame {
    public CadastrarRendaFixa() {
        String descricao = JOptionPane.showInputDialog("Descrição da renda fixa:");
        double taxa = Double.parseDouble(JOptionPane.showInputDialog("Taxa (%):"));
        double rentabilidade = Double.parseDouble(JOptionPane.showInputDialog("Rentabilidade esperada (%):"));
        int prazoMinimo = Integer.parseInt(JOptionPane.showInputDialog("Prazo mínimo (meses):"));
        int prazoMaximo = Integer.parseInt(JOptionPane.showInputDialog("Prazo máximo (meses):"));
        
        gerente.cadastrarOpcaoRendaFixa(descricao, taxa, rentabilidade, prazoMinimo, prazoMaximo);
        JOptionPane.showMessageDialog(this, "Renda fixa cadastrada com sucesso.");
    }
   }
    
    class CadastrarRendaVariavel extends JFrame {
    public CadastrarRendaVariavel() {
        String descricao = JOptionPane.showInputDialog("Descrição da renda variável:");
        double risco = Double.parseDouble(JOptionPane.showInputDialog("Nível de risco (1-10):"));
        double rentabilidade = Double.parseDouble(JOptionPane.showInputDialog("Rentabilidade esperada (%):"));
        
        gerente.cadastrarOpcaoRendaVariavel(descricao, risco, rentabilidade);
        JOptionPane.showMessageDialog(this, "Renda variável cadastrada com sucesso.");
     }
    }
    
    class AvaliarCredito extends JFrame {
    public AvaliarCredito() {
        String cpfCliente = JOptionPane.showInputDialog("Informe o CPF do cliente:");
       Usuario usuario = encontrarUsuarioPorCPF(cpfCliente);

        
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do crédito solicitado:"));
        gerente.avaliarCredito(usuario, valor);
        JOptionPane.showMessageDialog(this, "Crédito avaliado com sucesso.");
     }
    }
    
    private Usuario encontrarUsuarioPorCPF(String cpf) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                return usuario; // Retorna qualquer usuário com aquele CPF
            }
        }
        return null;
    }
}