/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */

package com.mycompany.interfaces;

//import com.mycompany.systembank.*;
import java.awt.*;
import javax.swing.*;

public class Login extends JFrame {
    //cria os campos que vão receber as informações
    private JTextField campoLogin  = new JTextField();
    private JPasswordField campoSenha = new JPasswordField();
    private JButton botaoEntrar = new JButton("Entrar");
    
    public Login(){
        setTitle("Faça seu Login");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        //cria painel na janela
        JPanel painel = new JPanel(new GridLayout(3,2,10,10));
        
        //adiciona os campos no painel
        painel.add(criarCampo("CPF: ",campoLogin));
        painel.add(criarCampo("Senha: ",campoSenha));
        painel.add(botaoEntrar);
        
        add(painel);
        setVisible(true);
        
    }
    
    public JPanel criarCampo(String titulo, JComponent campo){
        JPanel painelCampo = new JPanel(new GridLayout(1, 2, 5, 5));
        painelCampo.add(new JLabel(titulo));
        painelCampo.add(campo);
        return painelCampo;
    }
}
