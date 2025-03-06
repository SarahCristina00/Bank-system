
/**
    @author Lara da Silva Dias (202376010)
    @author Sarah Cristina (202376034)
    @author Wilian Santos (202276040)
     */

    package com.mycompany.interfaces;

    import static com.mycompany.interfaces.Login.criarCampo;
import static com.mycompany.interfaces.Login.persistenciaUsuarios;
    import com.mycompany.systembank.*;
    import java.awt.*;
    import javax.swing.*;

    public class AcessoCliente extends JFrame {
        
            private JButton botaoTransferencia = new JButton("Realizar Transferência"),
            botaoConsultaSaldo = new JButton("Consultar Saldo"),
            botaoConsultaExtrato = new JButton("Consultar Extrato"),
            botaoConsultaInvestimento = new JButton("Consultar Investimentos"),
            botaoConsultaCredito = new JButton("Consultar Empréstimo/Financiamento"),
            botaoSair = new JButton("Sair");
            

        public AcessoCliente(Cliente cliente) {
            
            setTitle("Sistema Bancário - Área do Cliente");
            setSize(500, 500);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            
            JPanel painelMenu = new JPanel(new GridLayout(6,1,20,20));
            painelMenu.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));


            botaoTransferencia.addActionListener(e -> new Transferencia(cliente));
            botaoConsultaSaldo.addActionListener(e-> JOptionPane.showMessageDialog(this, "Saldo: R$ " + cliente.getConta().getSaldo()));
            botaoConsultaExtrato.addActionListener(e -> new ExtratoCliente(cliente).exibirExtrato());
            botaoConsultaInvestimento.addActionListener(e-> new Menu());
            botaoConsultaCredito.addActionListener(e-> new Menu());
            botaoSair.addActionListener(e -> new Login());


            painelMenu.add(botaoTransferencia);
            painelMenu.add(botaoConsultaSaldo);
            painelMenu.add(botaoConsultaExtrato);
            painelMenu.add(botaoConsultaInvestimento);
            painelMenu.add(botaoConsultaCredito);
            painelMenu.add(botaoSair);

            add(painelMenu);
            setVisible(true);
        }

    }
     

    class Transferencia extends JFrame {
        public Transferencia(Cliente cliente) {
            setTitle("Área do Cliente - Realizar Transferência");
            setSize(500, 500);
            setLocationRelativeTo(null);

            
            JPanel painelInformacoes = new JPanel(new GridLayout(3, 2, 20, 20));

            
            JTextField campoDestino = new JTextField();
            JTextField campoValor = new JTextField();
            JButton botaoRealizarTransferencia = new JButton("Realizar Transferência");

            //adicao dos campos ao painel
            painelInformacoes.add(criarCampo("Informe a Conta de Destino: ", campoDestino));
            painelInformacoes.add(criarCampo("Informe o valor a ser transferido: ", campoValor));

            botaoRealizarTransferencia.addActionListener(e -> {
                int contaDestino = Integer.parseInt(campoDestino.getText());
                double valor = Double.parseDouble(campoValor.getText());

                Cliente clienteDestino = BankSystem.getCliente(contaDestino);
                 if (clienteDestino != null && valor > 0) {
                        if (cliente.getConta().transfereSaldo(valor, clienteDestino.getConta())) {
                                JOptionPane.showMessageDialog(this, "Transferência realizada com sucesso!");
                                persistenciaUsuarios.salvarDados(BankSystem.usuarios);
                                persistenciaUsuarios.carregarDados();
                        } else {
                                JOptionPane.showMessageDialog(this, "Saldo insuficiente para a transferência.");
                        }
                   } else {
                            JOptionPane.showMessageDialog(this, "Conta de destino inválida.");
                   }
            });

            // Adiciona os componentes na tela
            JPanel painelPrincipal = new JPanel(new BorderLayout());
            painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            painelPrincipal.add(painelInformacoes, BorderLayout.CENTER);

            add(painelPrincipal);
            add(botaoRealizarTransferencia, BorderLayout.SOUTH);

            setVisible(true);
        }
    }
