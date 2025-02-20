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
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,2));
        
        btnCriarUsuario = new JButton("Criar Usuário");
        btnRemoverUsuario = new JButton("Remover Usuário");
        btnListarUsuarios = new JButton("Listar Usuários");
        btnSair = new JButton("Sair");
        
        btnCriarUsuario.addActionListener(e -> new MenuCriarUsuario());
        btnRemoverUsuario.addActionListener(e-> new MenuCriarUsuario());
        btnListarUsuarios.addActionListener(e -> new MenuListarUsuarios());
        btnSair.addActionListener(e -> System.exit(0));
        
        add(btnCriarUsuario);
        add(btnListarUsuarios);
        add(btnSair);
        
        
        setVisible(true);
    }
    
}

class MenuCriarUsuario extends JFrame {
    public MenuCriarUsuario() {
        setTitle("Criar Usuário");
        setSize(300, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8, 2, 5, 5));
        
        JTextField nomeField = new JTextField();
        JTextField cpfField = new JTextField();
        JTextField dataNascimentoField = new JTextField();
        JTextField telefoneField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField loginField = new JTextField();
        JPasswordField senhaField = new JPasswordField();
        JButton criarBtn = new JButton("Criar");
        
        panel.add(new JLabel("Nome Completo:")); panel.add(nomeField);
        panel.add(new JLabel("CPF:")); panel.add(cpfField);
        panel.add(new JLabel("Data de Nascimento:")); panel.add(dataNascimentoField);
        panel.add(new JLabel("Telefone:")); panel.add(telefoneField);
        panel.add(new JLabel("Email:")); panel.add(emailField);
        panel.add(new JLabel("Login:")); panel.add(loginField);
        panel.add(new JLabel("Senha:")); panel.add(senhaField);
        panel.add(criarBtn);
        
        add(panel);
        setVisible(true);
        
        criarBtn.addActionListener(e -> {
            Usuario novoUsuario = new Usuario(
                nomeField.getText(),
                cpfField.getText(),
                dataNascimentoField.getText(),
                telefoneField.getText(),
                emailField.getText(),
                loginField.getText(),
                Integer.parseInt(new String(senhaField.getPassword()))
            );
            BankSystem.usuarios.add(novoUsuario);
            //atualiza os dados após execução
            Menu.persistencia.salvarDados(usuarios);
            JOptionPane.showMessageDialog(this, "Usuário criado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });
    }
}
    

class MenuListarUsuarios extends JFrame {
    
    private JList<Usuario> jlUsuarios;
    
    public MenuListarUsuarios() {
        setTitle("Lista de Usuários");
        setSize(400, 300);
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

