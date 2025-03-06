package com.mycompany.interfaces;

import com.mycompany.systembank.*;
import javax.swing.*;
import java.awt.*;
import java.util.Map;


public class AcessoGerente extends JFrame {
    private JButton botaoCadastrarRendaVariavel = new JButton("Cadastro Renda Variável"),
            botaoCadastrarRendaFixa = new JButton("Cadastro Renda Fixa"),
            botaoAvaliarCredito = new JButton("Avaliar Crédito"), 
            botaoApoiarMovimentacao = new JButton("Apoiar Movimentação"),
            botaoSair = new JButton("Sair");
            
    
   // private List<Gerente> listaGerentes;

    public AcessoGerente(Gerente gerente) {
        setTitle("Área do Gerente");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        //PersistenciaGerente persistenciaGerente = new PersistenciaGerente();
        //listaGerentes = persistenciaGerente.carregarDados();
        
        JPanel painelMenu = new JPanel(new GridLayout(4, 1, 20, 20));
        painelMenu.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        botaoCadastrarRendaVariavel.addActionListener(e -> new CadastroRendaVariavel(gerente));
        botaoCadastrarRendaFixa.addActionListener(e -> new CadastroRendaFixa(gerente));
        botaoAvaliarCredito.addActionListener(e -> new AvaliarCredito(gerente));
        botaoApoiarMovimentacao.addActionListener(e -> new ApoiarMovimentacao(gerente));
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
    public CadastroRendaVariavel(Gerente gerente) {
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
        painel.add(new JLabel("Rentabilidade (%): "));
        painel.add(campoRentabilidade);

        botaoConfirmar.addActionListener(e -> {
            String descricao = campoDescricao.getText();
            double risco = Double.parseDouble(campoRisco.getText());
            double rentabilidade = Double.parseDouble(campoRentabilidade.getText());
            gerente.cadastrarOpcaoRendaVariavel(descricao, risco, rentabilidade);
        });

        add(painel, BorderLayout.CENTER);
        add(botaoConfirmar, BorderLayout.SOUTH);
        setVisible(true);
    }
}

class CadastroRendaFixa extends JFrame {
    public CadastroRendaFixa(Gerente gerente) {
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
        painel.add(new JLabel("Taxa (%): "));
        painel.add(campoTaxa);
        painel.add(new JLabel("Rentabilidade (%): "));
        painel.add(campoRentabilidade);
        painel.add(new JLabel("Prazo Mínimo (em meses): "));
        painel.add(campoPrazoMinimo);
        painel.add(new JLabel("Prazo Máximo(em meses): "));
        painel.add(campoPrazoMaximo);

        botaoConfirmar.addActionListener(e -> {
            String descricao = campoDescricao.getText();
            double taxa = Double.parseDouble(campoTaxa.getText());
            double rentabilidade = Double.parseDouble(campoRentabilidade.getText());
            int prazoMinimo = Integer.parseInt(campoPrazoMinimo.getText());
            int prazoMaximo = Integer.parseInt(campoPrazoMaximo.getText());
            gerente.cadastrarOpcaoRendaFixa(descricao, taxa, rentabilidade, prazoMinimo, prazoMaximo);
        });

        add(painel, BorderLayout.CENTER);
        add(botaoConfirmar, BorderLayout.SOUTH);
        setVisible(true);
    }
}

class AvaliarCredito extends JFrame {

    private JList<String> listaSolicitacoes;
    private DefaultListModel<String> modeloLista;

    public AvaliarCredito(Gerente gerente) {
        setTitle("Avaliar Crédito");
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new BorderLayout());

        modeloLista = new DefaultListModel<>();
        listaSolicitacoes = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaSolicitacoes);

        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton botaoAprovar = new JButton("Aprovar");
        JButton botaoNegar = new JButton("Negar");

        botaoAprovar.addActionListener(e -> {
            int indiceSelecionado = listaSolicitacoes.getSelectedIndex();
            if (indiceSelecionado != -1) {
                gerente.analisarSolicitacoes(indiceSelecionado, "Aprovado");
            }
            exibirSolicitacoes();
        });
        botaoNegar.addActionListener(e -> {
            int indiceSelecionado = listaSolicitacoes.getSelectedIndex();
            if (indiceSelecionado != -1) {
                gerente.analisarSolicitacoes(indiceSelecionado, "Negado");
            }
            exibirSolicitacoes();
        });

        painelBotoes.add(botaoAprovar);
        painelBotoes.add(botaoNegar);
        
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        add(painelPrincipal);
        setVisible(true);

        exibirSolicitacoes(); // Exibe as solicitações ao abrir a janela
    }

    private void exibirSolicitacoes() {
        modeloLista.clear();
        for (Map<String, Object> solicitacao : BankSystem.solicitacoes) {
            if (solicitacao.get("tipo").equals("credito")) {
                String cpfCliente = (String) solicitacao.get("cpfCliente");
                Cliente cliente = BankSystem.getUsuario(cpfCliente, Cliente.class);
                if (cliente != null) {
                    modeloLista.addElement("Cliente: " + cliente.getNome() + ", Valor: R$" + solicitacao.get("valor")+", Status: " +solicitacao.get("status"));
                }
            }
        }
    }
}


class ApoiarMovimentacao extends JFrame {
        
    public ApoiarMovimentacao(Gerente gerente) {
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
            Usuario cliente = BankSystem.getUsuario(cpf,Cliente.class);

            gerente.apoiarMovimentacao(cliente, valor, tipo);
        });

        add(painel, BorderLayout.CENTER);
        add(botaoConfirmar, BorderLayout.SOUTH);
        setVisible(true);
    }
    
    
  }
  
    
