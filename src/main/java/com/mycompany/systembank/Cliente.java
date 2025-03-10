/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */

package com.mycompany.systembank;

import static com.mycompany.interfaces.Login.persistenciaSolicitacoes;
import static com.mycompany.interfaces.Login.persistenciaUsuarios;
import static com.mycompany.systembank.Caixa.solicitarSenha;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class Cliente extends Usuario{
    private Endereco endereco;
    private ContaBancaria conta;
            
            
     // Construtor que chama o super para inicializar os atributos 
    public Cliente(String nome, String cpf, String dataNascimento, String telefone, String email, int senha, Endereco endereco, ContaBancaria conta) {
        
        super(nome, cpf, dataNascimento, telefone, email, senha);
        this.endereco = endereco;
        this.conta = conta;
         super.setTipoUsuario("cliente");
    }
    
   public ContaBancaria getConta() {return conta;}
   public void setConta(ContaBancaria conta) {this.conta = conta;}
    
     public Endereco getEndereco() {
        return endereco;
    }
     
     public boolean transferir(double valor, Cliente destinatario){
         return this.conta.transfereSaldo(valor, destinatario.getConta());
     }
    
    public void consultarSaldo(){
        System.out.println("=======Consulta de Saldo=======/n");
        System.out.println("Saldo atual da conta " +conta.getConta() +": R$"+ conta.getSaldo());
    }
    
    public void consultarExtrato(){
        conta.imprimeExtrato();
    }
    
    public void solicitarCredito(double valor) {
        Map<String, Object> solicitacao = new HashMap<>();
        solicitacao.put("tipo", "credito");
        solicitacao.put("nomeCliente", this.getNome());
        solicitacao.put("cpfCliente", this.getCpf());
        solicitacao.put("contaCliente", this.getConta().getConta());
        solicitacao.put("valor", valor);
        solicitacao.put("status", "pendente");
        BankSystem.solicitacoes.add(solicitacao);
        persistenciaSolicitacoes.salvarDados(BankSystem.solicitacoes);        
        JOptionPane.showMessageDialog(null, "Solicitação de crédito de R$" + valor + " enviada para análise.");
    }

    
    public void investir(Map<String, Object> opcao, Double valor) {
        if(getConta().getSaldo() >= valor) {  
       
        // Solicitar senha para validar a operação
        String senhaInserida = solicitarSenha();
            if(senhaInserida != null && Integer.parseInt(senhaInserida) == getSenha()) {
                // Senha correta, proceder com o saque
                getConta().registraTransacao("Investimento "+opcao.get("descricao"), valor, getConta(), null);
                JOptionPane.showMessageDialog(null, 
                    "Investimento de R$" + valor + " realizado com sucesso para o cliente: " + getNome(), 
                    "Operação Realizada", 
                    JOptionPane.INFORMATION_MESSAGE);
                persistenciaUsuarios.salvarDados(BankSystem.usuarios);
                persistenciaUsuarios.carregarDados();
            }else{
                JOptionPane.showMessageDialog(null, 
                    "Senha incorreta! A operação foi cancelada.", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }else{
        JOptionPane.showMessageDialog(null, 
            "Saldo insuficiente para investimento do cliente: " + getNome(), 
            "Erro", 
            JOptionPane.ERROR_MESSAGE);
        }
    }
     
}

