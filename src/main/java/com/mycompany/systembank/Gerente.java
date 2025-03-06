package com.mycompany.systembank;

import static com.mycompany.interfaces.Login.persistenciaSolicitacoes;
import com.mycompany.persistencia.PersistenciaInvestimentos;
import java.util.*;
import java.util.Map;
import javax.swing.JOptionPane;

public class Gerente extends Usuario {
    

    // Construtor 
    public Gerente(String nome, String cpf, String dataNascimento, String telefone, String email, int senha) {
        super(nome, cpf, dataNascimento, telefone, email, senha);
        super.setTipoUsuario("gerente");
    }
   
    // GERENTE APÓS FAZER O LOGIN NO SISTEMA 
    public void apoiarMovimentacao(Usuario usuario, Double valor, String tipoMovimentacao) {
        if (tipoMovimentacao.equals("saque") && valor > 1000000) {
            JOptionPane.showMessageDialog(null, "Gerente auxiliando no saque de R$" + valor + " para o cliente: " + usuario.getNome(), "Apoio de Movimentação", JOptionPane.INFORMATION_MESSAGE);
            
            // Solicita a senha do cliente para autorizar
            int senhaCliente = Integer.parseInt(JOptionPane.showInputDialog("Digite a senha do cliente para confirmar:"));
            if (senhaCliente == usuario.getSenha()) {
                JOptionPane.showMessageDialog(null, "Saque autorizado. A operação foi concluída com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Senha incorreta! Operação cancelada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else if (tipoMovimentacao.equals("transferencia")) {
            JOptionPane.showMessageDialog(null, "Gerente auxiliando na transferência de R$" + valor + " para o cliente: " + usuario.getNome(), "Apoio de Movimentação", JOptionPane.INFORMATION_MESSAGE);
            
            // Solicita a senha do cliente para autorizar
            int senhaCliente = Integer.parseInt(JOptionPane.showInputDialog("Digite a senha do cliente para confirmar:"));
            if (senhaCliente == usuario.getSenha()) {
                JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Senha incorreta! Operação cancelada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Movimentação não permitida.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cadastrarOpcaoRendaFixa(String descricao, Double taxa, Double rentabilidade, int prazoMinimo, int prazoMaximo) {
        JOptionPane.showMessageDialog(null, "Opção de renda fixa cadastrada: " + descricao +
                " | Taxa: " + taxa + "%" +
                " | Rentabilidade esperada: " + rentabilidade + "%" +
                " | Prazo mínimo: " + prazoMinimo + " meses" +
                " | Prazo máximo: " + prazoMaximo + " meses", "Cadastro de Renda Fixa", JOptionPane.INFORMATION_MESSAGE);
    }

    public void cadastrarOpcaoRendaVariavel(String descricao, Double risco, Double rentabilidade) {
        JOptionPane.showMessageDialog(null, "Opção de renda variável cadastrada: " + descricao +
                " | Risco: " + risco +
                " | Rentabilidade esperada: " + rentabilidade + "%", "Cadastro de Renda Variável", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void cadastrarOpcaoInvestimento(Map<String, Object> opcao) {
        BankSystem.opcoesInvestimento.add(opcao);
        PersistenciaInvestimentos.salvarDados(BankSystem.opcoesInvestimento);
    }

    public static List<Map<String, Object>> getOpcoesInvestimento() {
        return BankSystem.opcoesInvestimento;
    }

    public void analisarSolicitacoes(int indice, String status) {
           Map<String, Object> solicitacao = BankSystem.solicitacoes.get(indice);
            if (solicitacao.get("tipo").equals("credito") && solicitacao.get("status").equals("pendente")) {
                solicitacao.put("status", status);
                System.out.println("Solicitação atualizada: " + solicitacao);
            }
            persistenciaSolicitacoes.salvarDados(BankSystem.solicitacoes);
        }
    }

