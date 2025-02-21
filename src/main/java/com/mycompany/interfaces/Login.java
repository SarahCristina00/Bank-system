/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */

package com.mycompany.interfaces;

import com.mycompany.persistencia.PersistenciaUsuarios;
import com.mycompany.systembank.*;
import static com.mycompany.systembank.BankSystem.usuarios;
import java.awt.*;
import javax.swing.*;

public class Login extends JFrame {
    //cria os campos que vão receber as informações
    private JTextField campoLogin  = new JTextField();
    private JPasswordField campoSenha = new JPasswordField();
    private JButton botaoEntrar = new JButton("Entrar");
     public static PersistenciaUsuarios persistencia = new PersistenciaUsuarios();
    
    @SuppressWarnings("null")
    public Login(){
        setTitle("Sistema Bancário - Login");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        //cria painel na janela
        JPanel painel = new JPanel(new GridLayout(5,3,50,50));
         painel.setBorder(BorderFactory.createEmptyBorder(100, 20, 20, 20));
        
        //adiciona os campos no painel
        painel.add(criarCampo("CPF: ",campoLogin));
        painel.add(criarCampo("Senha: ",campoSenha));
        painel.add(botaoEntrar);
        
        //evento que será acionado ao clicar em entrar
         botaoEntrar.addActionListener(e->{
           String cpf = campoLogin.getText();
           int senha = Integer.parseInt(new String(campoSenha.getPassword()));
           
           //verificacao dos dados informados
           boolean loginValido = false;
           String tipoUsuario = null;
           for(Usuario usuario:usuarios){
               if(usuario.getCpf().equals(cpf) && usuario.getSenha() ==senha){
                   loginValido = true;
                   tipoUsuario = usuario.getTipoUsuario();
                   break;
               }
           }
           
           //se o login for valido
           if(loginValido){
              JOptionPane.showMessageDialog(this, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
              setVisible(false);
              
              //abre a tela de acordo com o usuario
              switch(tipoUsuario){
                  case "usuario":
                      SwingUtilities.invokeLater(()->new Menu());
                      break;
                  case "gerente":
                      SwingUtilities.invokeLater(()->new Menu());
                      break;
                case "caixa":
                      SwingUtilities.invokeLater(()->new Menu());
                      break;
                case "cliente":
                      SwingUtilities.invokeLater(()->new Menu());
                      break;
                  default:
                      JOptionPane.showMessageDialog(this, "Tipo de usuário inválido.", "Erro", JOptionPane.INFORMATION_MESSAGE);
                      break;
              }
           }else{
               JOptionPane.showMessageDialog(this, "CPF ou senha inválidos, informe novamente.", "Erro", JOptionPane.INFORMATION_MESSAGE);
           }           
        });
        
        add(painel);
        setVisible(true);
        
    }
    
    public static JPanel criarCampo(String titulo, JComponent campo){
        JPanel painelCampo = new JPanel(new GridLayout(1, 2, 5, 5));
        painelCampo.add(new JLabel(titulo));
        painelCampo.add(campo);
        return painelCampo;
    }
}
