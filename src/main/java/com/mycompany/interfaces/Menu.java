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
        
        JPanel painelMenu = new JPanel();
        painelMenu.setLayout(new GridLayout(4,1,20,20));
        painelMenu.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        btnCriarUsuario = new JButton("Criar Usuário");
        btnRemoverUsuario = new JButton("Remover Usuário");
        btnListarUsuarios = new JButton("Listar Usuários");
        btnSair = new JButton("Sair");
        
        btnCriarUsuario.addActionListener(e -> new MenuCriarUsuario());
        btnRemoverUsuario.addActionListener(e-> new MenuRemoverUsuario());
        btnListarUsuarios.addActionListener(e -> new MenuListarUsuarios());
        btnSair.addActionListener(e -> System.exit(0));
        
        painelMenu.add(btnCriarUsuario);
        painelMenu.add(btnRemoverUsuario);
        painelMenu.add(btnListarUsuarios);
        painelMenu.add(btnSair);
        
        add(painelMenu);
        setVisible(true);
    }
}
    

class MenuCriarUsuario extends JFrame {
    public MenuCriarUsuario() {
        setTitle("Sistema Bancário - Criar Usuário");
        setSize(500, 500);
        setLocationRelativeTo(null);
        
        //adiciona na janela um painel que vai conter os campos para inserir as informações
        JPanel painelInformacoes = new JPanel(new GridLayout(8, 2, 5, 5));
        painelInformacoes.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        //cria caixa de seleção de tipo de usuário
        JRadioButton botaoGerente = new JRadioButton("Gerente");
        JRadioButton botaoCaixa = new JRadioButton("Caixa");
        JRadioButton botaoCliente = new JRadioButton("Cliente");
        ButtonGroup tipoUsuario = new ButtonGroup();
        
        tipoUsuario.add(botaoGerente);
        tipoUsuario.add(botaoCaixa);
        tipoUsuario.add(botaoCliente);
        
        //cria os campos que vão receber as informações
        JTextField campoNome = new JTextField();
        JTextField campoCPF = new JTextField();
        JTextField campoDataNascimento = new JTextField();
        JTextField campoTelefone = new JTextField();
        JTextField campoEmail = new JTextField();
        JTextField campoLogin = new JTextField();
        JPasswordField campoSenha = new JPasswordField();
        JButton criarBtn = new JButton("Criar");
        
        //adiciona no painel os campos juntamente com sua descrição 
        painelInformacoes.add(new JLabel("Tipo Usuário:")); painelInformacoes.add(tipoUsuario);
        painelInformacoes.add(new JLabel("Nome Completo:")); painelInformacoes.add(campoNome);
        painelInformacoes.add(new JLabel("CPF:")); painelInformacoes.add(campoCPF);
        painelInformacoes.add(new JLabel("Data de Nascimento:")); painelInformacoes.add(campoDataNascimento);
        painelInformacoes.add(new JLabel("Telefone:")); painelInformacoes.add(campoTelefone);
        painelInformacoes.add(new JLabel("Email:")); painelInformacoes.add(campoEmail);
        painelInformacoes.add(new JLabel("Login:")); painelInformacoes.add(campoLogin);
        painelInformacoes.add(new JLabel("Senha:")); painelInformacoes.add(campoSenha);
        painelInformacoes.add(criarBtn);
        
        //adiciona a janela o painel com as informações
        add(painelInformacoes);
        setVisible(true);
        
        //evento que será acionado ao clicar no botão criar chamando o construtor do usuário
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
            
            //adiciona novo usuario a lista de usuarios
            usuarios.add(novoUsuario);
            
            //atualiza os dados após execução
            Menu.persistencia.salvarDados(usuarios);
            JOptionPane.showMessageDialog(this, "Usuário criado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });
    }
}
   
class MenuRemoverUsuario extends JFrame{
    public MenuRemoverUsuario(){
        setTitle("Sistema Bancário - Remover Usuário");
        setSize(500, 500);
        setLocationRelativeTo(null);
         
        JPanel painelRemocao = new JPanel(new GridLayout(8, 8, 20, 20));
        painelRemocao.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JTextField campoNome = new JTextField();
        painelRemocao.add(new JLabel("Nome do Usuário a ser removido:")); painelRemocao.add(campoNome);
       
        JButton removerBtn = new JButton("Remover Usuário");
         painelRemocao.add(removerBtn);
        
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
            
        
        add(painelRemocao);
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

