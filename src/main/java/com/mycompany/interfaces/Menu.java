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

public class Menu extends JFrame {
    private JButton btnCriarUsuario, btnRemoverUsuario, btnListarUsuarios, btnSair;
    //instancia arquivo para carregar os dados
     public  static PersistenciaUsuarios persistencia = new PersistenciaUsuarios();
    
    public Menu() {
                
        //carrega os dados dos usuarios
        usuarios = persistencia.carregarDados();
        setTitle("Sistema Bancário - Menu Principal");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        setLayout(new GridLayout(4,0,30,30));
        setBorder(createEmptyBorder(20, 20, 20, 20));
        
        btnCriarUsuario = new JButton("Criar Usuário");
        btnRemoverUsuario = new JButton("Remover Usuário");
        btnListarUsuarios = new JButton("Listar Usuários");
        btnSair = new JButton("Sair");
        
        btnCriarUsuario.addActionListener(e -> new MenuCriarUsuario());
        btnRemoverUsuario.addActionListener(e-> new MenuRemoverUsuario());
        btnListarUsuarios.addActionListener(e -> new MenuListarUsuarios());
        btnSair.addActionListener(e -> System.exit(0));
        
        add(btnCriarUsuario);
        add(btnRemoverUsuario);
        add(btnListarUsuarios);
        add(btnSair);
        
        setVisible(true);
    }
}
    

class MenuCriarUsuario extends JFrame {
    public MenuCriarUsuario() {
        setTitle("Criar Usuário");
        setSize(500, 500);
        setLocationRelativeTo(null);

        JPanel painelInformacoes = new JPanel(new GridLayout(8, 2, 5, 5));
        painelInformacoes.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JTextField campoNome = new JTextField();
        JTextField campoCPF = new JTextField();
        JTextField campoDataNascimento = new JTextField();
        JTextField campoTelefone = new JTextField();
        JTextField campoEmail = new JTextField();
        JTextField campoLogin = new JTextField();
        JPasswordField campoSenha = new JPasswordField();
        JButton criarBtn = new JButton("Criar");
        
        painelInformacoes.add(new JLabel("Nome Completo:")); painelInformacoes.add(campoNome);
        painelInformacoes.add(new JLabel("CPF:")); painelInformacoes.add(campoCPF);
        painelInformacoes.add(new JLabel("Data de Nascimento:")); painelInformacoes.add(campoDataNascimento);
        painelInformacoes.add(new JLabel("Telefone:")); painelInformacoes.add(campoTelefone);
        painelInformacoes.add(new JLabel("Email:")); painelInformacoes.add(campoEmail);
        painelInformacoes.add(new JLabel("Login:")); painelInformacoes.add(campoLogin);
        painelInformacoes.add(new JLabel("Senha:")); painelInformacoes.add(campoSenha);
        painelInformacoes.add(criarBtn);
        
        add(painelInformacoes);
        setVisible(true);
        
        criarBtn.addActionListener(e -> {
            Usuario novoUsuario = new Usuario(
                campoNome.getText(),
                campoCPF.getText(),
                campoDataNascimento.getText(),
                campoTelefone.getText(),
                campoEmail.getText(),
                campoLogin.getText(),
                Integer.parseInt(new String(campoSenha.getPassword()))
            );
            BankSystem.usuarios.add(novoUsuario);
            //atualiza os dados após execução
            Menu.persistencia.salvarDados(usuarios);
            JOptionPane.showMessageDialog(this, "Usuário criado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });
    }
}
   
class MenuRemoverUsuario extends JFrame{
    public MenuRemoverUsuario(){
        setTitle("Sistema Bancário - Remoção de Usuário");
        setSize(500, 500);
        setLocationRelativeTo(null);
         
        JPanel panel = new JPanel(new GridLayout(2, 2, 20, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JTextField campoNome = new JTextField();
        panel.add(new JLabel("Nome do Usuário a ser removido:")); panel.add(campoNome);
       
        JButton removerBtn = new JButton("Remover Usuário");
         panel.add(removerBtn);
        
        removerBtn.addActionListener(e -> {
                String nome = campoNome.getText();
                
                for(Usuario usuario : usuarios){
                    if(usuario.getNome().equals(nome)){
                        usuarios.remove(usuario);
                        Menu.persistencia.salvarDados(usuarios);
                        JOptionPane.showMessageDialog(this, "Usuário removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                         dispose();
                         return;
                    }
                }
                    JOptionPane.showMessageDialog(this, "Usuário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
          );
            
        
        add(panel);
        setVisible(true);
    }
}

class MenuListarUsuarios extends JFrame {
        private JList<Usuario> jlUsuarios;
        public MenuListarUsuarios() {
        setTitle("Lista de Usuários");
        setSize(500, 500);
        setLocationRelativeTo(null);
        
        JPanel lista = new JPanel();
        lista.setBorder(BorderFactory.createTitledBorder("Lista de Usuários"));
        lista.setLayout(new BorderLayout());
        
        DefaultListModel<Usuario> model = new DefaultListModel<>();
        
        for(Usuario usuario:usuarios){
            model.addElement(usuario);
        }
            jlUsuarios = new JList<>(model);
            
            lista.add(new JScrollPane(jlUsuarios), BorderLayout.CENTER);
            
            add(lista);
            
            setVisible(true);
       
    }
}

