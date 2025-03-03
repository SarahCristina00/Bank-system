package com.mycompany.interfaces;

import com.mycompany.systembank.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static com.mycompany.systembank.BankSystem.usuarios;
import java.util.List;

import com.mycompany.persistencia.PersistenciaGerente;

public class AcessoGerente extends JFrame {
    private JButton botaoCadastrarRendaVariavel = new JButton("Cadastro Renda Variável");
    private JButton botaoCadastrarRendaFixa = new JButton("Cadastro Renda Fixa");
    private JButton botaoAvaliarCredito = new JButton("Avaliar Crédito");
    private JButton botaoApoiarMovimentacao = new JButton("Apoiar Movimentação");
    
      private List<Gerente> listaGerentes;

    public AcessoGerente() {
        setTitle("Área do Gerente");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Carregar os dados dos gerentes ao iniciar
        PersistenciaGerente persistenciaGerente = new PersistenciaGerente();
        listaGerentes = persistenciaGerente.carregarDados();

        JPanel painelMenu = new JPanel(new GridLayout(4, 1, 20, 20));
        painelMenu.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Adicionando ações aos botões
        botaoCadastrarRendaVariavel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processarOperacao("Cadastro Renda Variável");
            }
        });

        botaoCadastrarRendaFixa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processarOperacao("Cadastro Renda Fixa");
            }
        });

        botaoAvaliarCredito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processarOperacao("Avaliar Crédito");
            }
        });

        botaoApoiarMovimentacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processarOperacao("Apoiar Movimentação");
            }
        });

        // Adicionando botões ao painel
        painelMenu.add(botaoCadastrarRendaVariavel);
        painelMenu.add(botaoCadastrarRendaFixa);
        painelMenu.add(botaoAvaliarCredito);
        painelMenu.add(botaoApoiarMovimentacao);

        add(painelMenu);
        setVisible(true);
    }

    // Método genérico para processar todas as operações
    private void processarOperacao(String tipoOperacao) {
        String dados = "";
        switch (tipoOperacao) {
            case "Cadastro Renda Variável":
                String descricaoRendaVariavel = JOptionPane.showInputDialog("Descrição da renda variável:");
                double riscoRendaVariavel = Double.parseDouble(JOptionPane.showInputDialog("Nível de risco (1-10):"));
                double rentabilidadeRendaVariavel = Double.parseDouble(JOptionPane.showInputDialog("Rentabilidade esperada (%):"));
                dados = "Descrição: " + descricaoRendaVariavel + " | Risco: " + riscoRendaVariavel + " | Rentabilidade: " + rentabilidadeRendaVariavel + "%";
                JOptionPane.showMessageDialog(this, tipoOperacao + " registrada com sucesso.");
                
                break;

            case "Cadastro Renda Fixa":
                String descricaoRendaFixa = JOptionPane.showInputDialog("Descrição da renda fixa:");
                double taxaRendaFixa = Double.parseDouble(JOptionPane.showInputDialog("Taxa (%):"));
                double rentabilidadeRendaFixa = Double.parseDouble(JOptionPane.showInputDialog("Rentabilidade esperada (%):"));
                int prazoMinimoRendaFixa = Integer.parseInt(JOptionPane.showInputDialog("Prazo mínimo (meses):"));
                int prazoMaximoRendaFixa = Integer.parseInt(JOptionPane.showInputDialog("Prazo máximo (meses):"));
                dados = "Descrição: " + descricaoRendaFixa + " | Taxa: " + taxaRendaFixa + "% | Rentabilidade: " + rentabilidadeRendaFixa + "% | Prazo: " + prazoMinimoRendaFixa + " a " + prazoMaximoRendaFixa + " meses";
               JOptionPane.showMessageDialog(this, tipoOperacao + " registrada com sucesso.");
                break;

            case "Avaliar Crédito":
            String cpfClienteCredito = JOptionPane.showInputDialog("Informe o CPF do cliente:");

            // Encontre o usuário pelo CPF
            Usuario usuarioCredito = encontrarUsuarioPorCPF(cpfClienteCredito);

            if (usuarioCredito != null) {
                double valorCredito = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do crédito solicitado:"));
                dados = "CPF: " + cpfClienteCredito + " | Crédito: " + valorCredito;

                // Exibindo a mensagem de crédito aprovado com o nome do cliente
                JOptionPane.showMessageDialog(this, "Crédito de R$" + valorCredito + " aprovado com sucesso para o cliente: " + usuarioCredito.getNome() + " (CPF: " + cpfClienteCredito + ").", "Avaliação de Crédito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            break;


           case "Apoiar Movimentação":
            String cpfClienteApoio = JOptionPane.showInputDialog("Informe o CPF do cliente:");

            // Encontre o usuário pelo CPF
            Usuario usuarioApoio = encontrarUsuarioPorCPF(cpfClienteApoio);

            if (usuarioApoio != null) {
                String tipoApoio = JOptionPane.showInputDialog("Digite o tipo de movimentação (saque/transferencia):");
                double valorApoio = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da movimentação:"));
                dados = "CPF: " + cpfClienteApoio + " | Tipo: " + tipoApoio + " | Valor: " + valorApoio;

                // Exibindo a mensagem de sucesso com o nome do cliente
                JOptionPane.showMessageDialog(this, tipoApoio.substring(0, 1).toUpperCase() + tipoApoio.substring(1) + " de R$" + valorApoio + " realizada com sucesso para o cliente: " + usuarioApoio.getNome() + " (CPF: " + cpfClienteApoio + ").", tipoApoio.substring(0, 1).toUpperCase() + tipoApoio.substring(1), JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            break;

        }

        // Salva os dados da operação na persistência utilizando PersistenciaGerente
        PersistenciaGerente persistenciaGerente = new PersistenciaGerente();
        persistenciaGerente.salvarDados(listaGerentes);  // Chama a classe PersistenciaGerente
        
    }
    
    private Usuario encontrarUsuarioPorCPF(String cpf) {
    for (Usuario usuario : usuarios) { // onde usuarios é uma lista ou array com todos os usuários
        if (usuario.getCpf().equals(cpf)) {
            return usuario; // Retorna o usuário com o CPF correspondente
        }
    }
    return null; // Se não encontrar, retorna null
  }


    public static void main(String[] args) {
        new AcessoGerente();
    }
  }