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
    
    // Construtor 
    public Caixa(String nome, String cpf, String dataNascimento, String telefone, 
                 String email, int senha) {
        super(nome, cpf, dataNascimento, telefone, email, senha);
         super.setTipoUsuario("caixa");
    }

    //CAIXA APOS FAZER LOGIN NO SISTEMA
    public void processarSaque(Cliente cliente, Double valor) {
    if (cliente.getConta().getSaldo() >= valor) {  // Acessando saldo pela conta para verificar se o cliente tem saldo suficiente para o saque
        
        // Solicitar senha para validar a operação
        String senhaInserida = solicitarSenha();
        if (senhaInserida != null && Integer.parseInt(senhaInserida) == cliente.getSenha()) {
            // Senha correta, proceder com o saque
            cliente.getConta().setSaldo(cliente.getConta().getSaldo() - valor); // Atualização do saldo do cliente após o saque
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
    if (valor > 0) {  // Se o valor do depósito for maior que 0 
        cliente.getConta().setSaldo(cliente.getConta().getSaldo() + valor); // Obtém o saldo atual do cliente e adiciona o valor do depósito 
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
       if (cliente.getConta().getSaldo() >= valor) {  //Verifica saldo atual do cliente se é maior que o valor da transferencia
            cliente.getConta().setSaldo(cliente.getConta().getSaldo() - valor);// retira o valor de trasferencia da  conta do cliente 
          //  destino.receberTransferencia(valor);// PRA ONDE VAMOS TRANSFERIR O DINHEIRO? PARA UMA OUTRA CONTA QUE CADASTRAMOS? PARA UMA CONTA QUE CRIAREMOS SÓ PRA RECEBER A TRABSFERENCIA?
          //  aqui o cliente confirma a operação informando sua senha pessoal. 
          System.out.println("Transferência de R$" + valor + 
                               " realizada com sucesso do cliente " + cliente.getNome() + 
                               " para a conta de destino.");
        } else {
            System.out.println("Saldo insuficiente para transferência do cliente: " + cliente.getNome());
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
