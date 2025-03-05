package com.mycompany.interfaces;

import com.mycompany.systembank.*;
import javax.swing.*;
import java.awt.*;
import static com.mycompany.systembank.BankSystem.usuarios;
import java.util.List;

import com.mycompany.persistencia.PersistenciaGerente;

public class AcessoGerente extends JFrame {
    private JButton botaoCadastrarRendaVariavel = new JButton("Cadastro Renda Variável"),
            botaoCadastrarRendaFixa = new JButton("Cadastro Renda Fixa"),
            botaoAvaliarCredito = new JButton("Avaliar Crédito"), 
            botaoApoiarMovimentacao = new JButton("Apoiar Movimentação"),
            botaoSair = new JButton("Sair");
            
    
   // private List<Gerente> listaGerentes;

    public AcessoGerente() {
        setTitle("Área do Gerente");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        //PersistenciaGerente persistenciaGerente = new PersistenciaGerente();
        //listaGerentes = persistenciaGerente.carregarDados();
        
        JPanel painelMenu = new JPanel(new GridLayout(4, 1, 20, 20));
        painelMenu.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        botaoCadastrarRendaVariavel.addActionListener(e -> new CadastroRendaVariavel());
        botaoCadastrarRendaFixa.addActionListener(e -> new CadastroRendaFixa());
        botaoAvaliarCredito.addActionListener(e -> new AvaliarCredito());
        botaoApoiarMovimentacao.addActionListener(e -> new ApoiarMovimentacao());
        botaoSair.addActionListener(e -> new Login());
        
        painelMenu.add(botaoCadastrarRendaVariavel);
        painelMenu.add(botaoCadastrarRendaFixa);
        painelMenu.add(botaoAvaliarCredito);
        painelMenu.add(botaoApoiarMovimentacao);
        painelMenu.add(botaoSair);
        
        add(painelMenu);
        setVisible(true);
    }
}

class CadastroRendaVariavel extends JFrame {
    public CadastroRendaVariavel() {
        setTitle("Cadastro Renda Variável");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 2, 20, 20));
        JTextField campoDescricao = new JTextField();
        JTextField campoRisco = new JTextField();
        JTextField campoRentabilidade = new JTextField();
        JButton botaoConfirmar = new JButton("Confirmar Cadastro");

        painel.add(new JLabel("Descrição: "));
        painel.add(campoDescricao);
        painel.add(new JLabel("Risco: "));
        painel.add(campoRisco);
        painel.add(new JLabel("Rentabilidade: "));
        painel.add(campoRentabilidade);

        botaoConfirmar.addActionListener(e -> {
            String descricao = campoDescricao.getText();
            double risco = Double.parseDouble(campoRisco.getText());
            double rentabilidade = Double.parseDouble(campoRentabilidade.getText());
            Gerente gerente = new Gerente("Gerente", "", "", "", "", 0);
            gerente.cadastrarOpcaoRendaVariavel(descricao, risco, rentabilidade);
        });

        add(painel, BorderLayout.CENTER);
        add(botaoConfirmar, BorderLayout.SOUTH);
        setVisible(true);
    }
}

class CadastroRendaFixa extends JFrame {
    public CadastroRendaFixa() {
        setTitle("Cadastro Renda Fixa");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(5, 2, 20, 20));
        JTextField campoDescricao = new JTextField();
        JTextField campoTaxa = new JTextField();
        JTextField campoRentabilidade = new JTextField();
        JTextField campoPrazoMinimo = new JTextField();
        JTextField campoPrazoMaximo = new JTextField();
        JButton botaoConfirmar = new JButton("Confirmar Cadastro");

        painel.add(new JLabel("Descrição: "));
        painel.add(campoDescricao);
        painel.add(new JLabel("Taxa: "));
        painel.add(campoTaxa);
        painel.add(new JLabel("Rentabilidade: "));
        painel.add(campoRentabilidade);
        painel.add(new JLabel("Prazo Mínimo: "));
        painel.add(campoPrazoMinimo);
        painel.add(new JLabel("Prazo Máximo: "));
        painel.add(campoPrazoMaximo);

        botaoConfirmar.addActionListener(e -> {
            String descricao = campoDescricao.getText();
            double taxa = Double.parseDouble(campoTaxa.getText());
            double rentabilidade = Double.parseDouble(campoRentabilidade.getText());
            int prazoMinimo = Integer.parseInt(campoPrazoMinimo.getText());
            int prazoMaximo = Integer.parseInt(campoPrazoMaximo.getText());
            Gerente gerente = new Gerente("Gerente", "", "", "", "", 0);
            gerente.cadastrarOpcaoRendaFixa(descricao, taxa, rentabilidade, prazoMinimo, prazoMaximo);
        });

        add(painel, BorderLayout.CENTER);
        add(botaoConfirmar, BorderLayout.SOUTH);
        setVisible(true);
    }
}

class AvaliarCredito extends JFrame {
    
    public static Usuario encontrarUsuarioPorCPF(String cpf) { 
        for (Usuario usuario : usuarios) { 
            if (usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }
        return null;
     }
    
    public AvaliarCredito() {
        setTitle("Avaliar Crédito");
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(2, 2, 20, 20));
        JTextField campoCPF = new JTextField();
        JTextField campoValor = new JTextField();
        JButton botaoConfirmar = new JButton("Confirmar Avaliação");

        painel.add(new JLabel("CPF do Cliente: "));
        painel.add(campoCPF);
        painel.add(new JLabel("Valor do Crédito: "));
        painel.add(campoValor);

        botaoConfirmar.addActionListener(e -> {
            String cpf = campoCPF.getText();
            double valor = Double.parseDouble(campoValor.getText());
            Gerente gerente = new Gerente("Gerente", "", "", "", "", 0);
            Usuario cliente = encontrarUsuarioPorCPF(cpf);

            gerente.avaliarCredito(cliente, valor);
        });

        add(painel, BorderLayout.CENTER);
        add(botaoConfirmar, BorderLayout.SOUTH);
        setVisible(true);
    }
}


class ApoiarMovimentacao extends JFrame {
    
    public static Usuario encontrarUsuarioPorCPF(String cpf) { 
        for (Usuario usuario : usuarios) { 
            if (usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }
        return null;
     }
    
    public ApoiarMovimentacao() {
        setTitle("Apoiar Movimentação");
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 2, 20, 20));
        JTextField campoCPF = new JTextField();
        JTextField campoValor = new JTextField();
        JTextField campoTipo = new JTextField();
        JButton botaoConfirmar = new JButton("Confirmar Apoio");

        painel.add(new JLabel("CPF do Cliente: "));
        painel.add(campoCPF);
        painel.add(new JLabel("Valor: "));
        painel.add(campoValor);
        painel.add(new JLabel("Tipo (saque/transferência): "));
        painel.add(campoTipo);

        botaoConfirmar.addActionListener(e -> {
            String cpf = campoCPF.getText();
            double valor = Double.parseDouble(campoValor.getText());
            String tipo = campoTipo.getText();
            Gerente gerente = new Gerente("Gerente", "", "", "", "", 0);
            Usuario cliente = encontrarUsuarioPorCPF(cpf);

            gerente.apoiarMovimentacao(cliente, valor, tipo);
        });

        add(painel, BorderLayout.CENTER);
        add(botaoConfirmar, BorderLayout.SOUTH);
        setVisible(true);
    }
    
    
  }
  
    
