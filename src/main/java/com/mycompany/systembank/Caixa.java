/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */

package com.mycompany.systembank;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import com.mycompany.interfaces.Login;



public class Caixa extends Usuario {
    
  
    public Caixa(String nome, String cpf, String dataNascimento, String telefone, 
                 String email, int senha) {
        super(nome, cpf, dataNascimento, telefone, email, senha);
         super.setTipoUsuario("caixa");
    }


    public void processarSaque(Cliente cliente, Double valor) {
    if (cliente.getConta().getSaldo() >= valor) {  
        
        
        String senhaInserida = solicitarSenha();
        if (senhaInserida != null && Integer.parseInt(senhaInserida) == cliente.getSenha()) {
         
            cliente.getConta().setSaldo(cliente.getConta().getSaldo() - valor); 
            JOptionPane.showMessageDialog(null, 
                "Saque de R$" + valor + " realizado com sucesso para o cliente: " + cliente.getNome(), 
                "Operação Realizada", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, 
                "Senha incorreta! A operação foi cancelada.", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, 
            "Saldo insuficiente para saque do cliente: " + cliente.getNome(), 
            "Erro", 
            JOptionPane.ERROR_MESSAGE);
    }
}


 public void processarDeposito(Cliente cliente, Double valor) {
    if (cliente == null) {
        System.out.println("Erro: Cliente não encontrado.");
        return;
    }

    System.out.println("Valor do depósito: " + valor); 

    if (valor > 0) {
        double saldoAnterior = cliente.getConta().getSaldo(); 
        cliente.getConta().setSaldo(saldoAnterior + valor); 
        double saldoAtual = cliente.getConta().getSaldo(); 
        System.out.println("Saldo anterior: " + saldoAnterior);
        System.out.println("Saldo atual: " + saldoAtual);


        JOptionPane.showMessageDialog(null, 
            "Depósito de R$" + valor + " realizado com sucesso para o cliente: " + cliente.getNome(), 
            "Operação Realizada", 
            JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(null, 
            "Valor de depósito inválido para o cliente: " + cliente.getNome(), 
            "Erro", 
            JOptionPane.ERROR_MESSAGE);
    }
}

    public void processarTransferencia(Cliente cliente, ContaBancaria destino, Double valor) {
    if (cliente.getConta().getSaldo() >= valor) {  
      
        String senhaInserida = solicitarSenha();
        if (senhaInserida != null && Integer.parseInt(senhaInserida) == cliente.getSenha()) {
            
            cliente.getConta().setSaldo(cliente.getConta().getSaldo() - valor); 
             

            Cliente clienteDestino = BankSystem.getCliente(destino.getConta());
        
            JOptionPane.showMessageDialog(null, 
                "Transferência de R$" + valor + 
                " realizada com sucesso de " + cliente.getNome() + 
                " para " + clienteDestino.getNome(),
                "Operação Realizada", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, 
                "Senha incorreta! A operação foi cancelada.", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, 
            "Saldo insuficiente para transferência do cliente: " + cliente.getNome(), 
            "Erro", 
            JOptionPane.ERROR_MESSAGE);
    }
}
    
    private String solicitarSenha() {
        JPasswordField campoSenha = new JPasswordField();
        int option = JOptionPane.showConfirmDialog(null, campoSenha, "Digite sua Senha Pessoal", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (option == JOptionPane.OK_OPTION) {
            return new String(campoSenha.getPassword());
        }
        return null;
    }

}
