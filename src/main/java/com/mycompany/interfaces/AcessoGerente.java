/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */
package com.mycompany.interfaces;

import com.mycompany.systembank.*;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
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
        BankSystem.janelasAbertas.add(this);
        setVisible(true);
    }
}

class CadastroRendaVariavel extends JFrame {
    private JList<String> listaOpcoes = new JList<>();
    private DefaultListModel<String> modeloLista = new DefaultListModel<>();
    public CadastroRendaVariavel(Gerente gerente) {
        setTitle("Cadastro Renda Variável");
        setSize(600, 400); // Aumentando o tamanho para acomodar a lista
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new BorderLayout());

        // Painel para os campos de cadastro
        JPanel painelCampos = new JPanel(new GridLayout(3, 2, 20, 20));
        JTextField campoDescricao = new JTextField();
        JTextField campoRisco = new JTextField();
        JTextField campoRentabilidade = new JTextField();
        JButton botaoConfirmar = new JButton("Confirmar Cadastro");

        painelCampos.add(new JLabel("Descrição: "));
        painelCampos.add(campoDescricao);
        painelCampos.add(new JLabel("Risco: "));
        painelCampos.add(campoRisco);
        painelCampos.add(new JLabel("Rentabilidade (%): "));
        painelCampos.add(campoRentabilidade);

        botaoConfirmar.addActionListener(e -> {
            Map<String, Object> opcao = new HashMap<>();
            opcao.put("tipo", "renda variável");
            opcao.put("descricao", campoDescricao.getText());
            opcao.put("risco", Double.valueOf(campoRisco.getText()));
            opcao.put("rentabilidade", Double.valueOf(campoRentabilidade.getText()));
            gerente.cadastrarOpcaoInvestimento(opcao);
            atualizarListaOpcoes(listaOpcoes); 
        });

        // Painel para a lista de opções cadastradas
        JPanel painelLista = new JPanel(new BorderLayout());
        atualizarListaOpcoes(listaOpcoes); // Inicializa a lista
        JScrollPane scrollPane = new JScrollPane(listaOpcoes);
        painelLista.add(new JLabel("Opções Cadastradas:"), BorderLayout.NORTH);
        painelLista.add(scrollPane, BorderLayout.CENTER);

        // Adiciona os painéis ao painel principal
        painelPrincipal.add(painelCampos, BorderLayout.NORTH);
        painelPrincipal.add(painelLista, BorderLayout.CENTER);
        painelPrincipal.add(botaoConfirmar, BorderLayout.SOUTH);

        add(painelPrincipal);
        BankSystem.janelasAbertas.add(this);
        setVisible(true);
    }

    private void atualizarListaOpcoes(JList<String> lista) {
        modeloLista.clear();
        for (Map<String, Object> opcao : Gerente.getOpcoesInvestimento()) {
            if (opcao.get("tipo").equals("renda variável")) {
                String descricao = (String) opcao.get("descricao");
                double risco = (double) opcao.get("risco");
                double rentabilidade = (double) opcao.get("rentabilidade");
                modeloLista.addElement(descricao + " (Risco: " + risco + ", Rentabilidade: " + rentabilidade + "%)");
            }
        }
        lista.setModel(modeloLista);
    }
}

class CadastroRendaFixa extends JFrame {
    private JList<String> listaOpcoes = new JList<>();
    private DefaultListModel<String> modeloLista = new DefaultListModel<>();
    public CadastroRendaFixa(Gerente gerente) {
        setTitle("Cadastro Renda Fixa");
        setSize(600, 400); // Aumentando o tamanho para acomodar a lista
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new BorderLayout());

        // Painel para os campos de cadastro
        JPanel painelCampos = new JPanel(new GridLayout(5, 2, 20, 20));
        JTextField campoDescricao = new JTextField();
        JTextField campoTaxa = new JTextField();
        JTextField campoRentabilidade = new JTextField();
        JTextField campoPrazoMinimo = new JTextField();
        JTextField campoPrazoMaximo = new JTextField();
        JButton botaoConfirmar = new JButton("Confirmar Cadastro");

        painelCampos.add(new JLabel("Descrição: "));
        painelCampos.add(campoDescricao);
        painelCampos.add(new JLabel("Taxa (%): "));
        painelCampos.add(campoTaxa);
        painelCampos.add(new JLabel("Rentabilidade (%): "));
        painelCampos.add(campoRentabilidade);
        painelCampos.add(new JLabel("Prazo Mínimo (em meses): "));
        painelCampos.add(campoPrazoMinimo);
        painelCampos.add(new JLabel("Prazo Máximo(em meses): "));
        painelCampos.add(campoPrazoMaximo);

        botaoConfirmar.addActionListener(e -> {
            Map<String, Object> opcao = new HashMap<>();
            opcao.put("tipo", "renda fixa");
            opcao.put("descricao", campoDescricao.getText());
            opcao.put("taxa", Double.valueOf(campoTaxa.getText()));
            opcao.put("rentabilidade", Double.valueOf(campoRentabilidade.getText()));
            opcao.put("prazoMinimo", Integer.valueOf(campoPrazoMinimo.getText()));
            opcao.put("prazoMaximo", Integer.valueOf(campoPrazoMaximo.getText()));
            gerente.cadastrarOpcaoInvestimento(opcao);
            atualizarListaOpcoes(listaOpcoes); 
        });

        // Painel para a lista de opções cadastradas
        JPanel painelLista = new JPanel(new BorderLayout());
        atualizarListaOpcoes(listaOpcoes); 
        JScrollPane scrollPane = new JScrollPane(listaOpcoes);
        painelLista.add(new JLabel("Opções Cadastradas:"), BorderLayout.NORTH);
        painelLista.add(scrollPane, BorderLayout.CENTER);

        // Adiciona os painéis ao painel principal
        painelPrincipal.add(painelCampos, BorderLayout.NORTH);
        painelPrincipal.add(painelLista, BorderLayout.CENTER);
        painelPrincipal.add(botaoConfirmar, BorderLayout.SOUTH);

        add(painelPrincipal);
        BankSystem.janelasAbertas.add(this);
        setVisible(true);
    }

    private void atualizarListaOpcoes(JList<String> lista) {
        modeloLista.clear();
        for(Map<String, Object> opcao : Gerente.getOpcoesInvestimento()) {
            if (opcao.get("tipo").equals("renda fixa")) {
                String descricao = (String) opcao.get("descricao");
                double taxa = (double) opcao.get("taxa");
                double rentabilidade = (double) opcao.get("rentabilidade");
                int prazoMinimo = 0; // Valor padrão em caso de erro
                if(!(opcao.get("prazoMinimo") instanceof Double)) {
                    if (opcao.get("prazoMinimo") instanceof Integer) {
                        prazoMinimo = (int) opcao.get("prazoMinimo");
                    } 
                }else{
                    prazoMinimo = ((Double) opcao.get("prazoMinimo")).intValue();
                }

                int prazoMaximo = 0; // Valor padrão em caso de erro
                if(opcao.get("prazoMaximo") instanceof Double aDouble) {
                    prazoMaximo = aDouble.intValue(); 
                }else if(opcao.get("prazoMaximo")instanceof Integer) {
                    prazoMaximo =(int)opcao.get("prazoMaximo"); 
                }
                modeloLista.addElement(descricao + " (Taxa: " + taxa + "%, Rentabilidade: " + rentabilidade + "%, Prazo: " + prazoMinimo + "-" + prazoMaximo + " meses)");
            }
        }
        lista.setModel(modeloLista);
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
        BankSystem.janelasAbertas.add(this);
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
        BankSystem.janelasAbertas.add(this);
        setVisible(true);
    }
    
    
  }
  
    
