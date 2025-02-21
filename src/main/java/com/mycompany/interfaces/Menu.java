/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */

package com.mycompany.interfaces;

import static com.mycompany.interfaces.Login.criarCampo;
import com.mycompany.persistencia.PersistenciaUsuarios;
import com.mycompany.systembank.*;
import static com.mycompany.systembank.BankSystem.usuarios;
import java.awt.*;
import javax.swing.*;

public class Menu extends JFrame {
    private JButton btnCriarUsuario, btnRemoverUsuario, btnListarUsuarios, btnSair;
    //instancia arquivo para carregar os dados
     public static PersistenciaUsuarios persistencia = new PersistenciaUsuarios();
    
    public Menu() {
                
        //carrega os dados dos usuarios
        usuarios = persistencia.carregarDados();
        setTitle("Sistema Bancário - Menu Principal");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel painelMenu = new JPanel(new GridLayout(4,1,20,20));
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
        
        //cria caixa de seleção de tipo de usuário
        JPanel painelBotoes = new JPanel(new GridLayout(1,3,5,5));
        JRadioButton botaoGerente = new JRadioButton("Gerente");
        JRadioButton botaoCaixa = new JRadioButton("Caixa");
        JRadioButton botaoCliente = new JRadioButton("Cliente");
        ButtonGroup tipoUsuario = new ButtonGroup();
        
        tipoUsuario.add(botaoGerente);
        tipoUsuario.add(botaoCaixa);
        tipoUsuario.add(botaoCliente);
        painelBotoes.add(botaoGerente);
        painelBotoes.add(botaoCaixa);
        painelBotoes.add(botaoCliente);
        
        
        //cria os campos que vão receber as informações
        JTextField campoNome = new JTextField();
        JTextField campoCPF = new JTextField();
        JTextField campoDataNascimento = new JTextField();
        JTextField campoTelefone = new JTextField();
        JTextField campoEmail = new JTextField();
        JPasswordField campoSenha = new JPasswordField();
        JButton criarBtn = new JButton("Criar");
        
        //adiciona no painel os campos juntamente com sua descrição 
        painelInformacoes.add(criarCampo("Nome Completo: ",campoNome));
        painelInformacoes.add(criarCampo("CPF: ",campoCPF));
        painelInformacoes.add(criarCampo("Data de Nacimento: ",campoDataNascimento));
        painelInformacoes.add(criarCampo("Telefone: ",campoTelefone));
        painelInformacoes.add(criarCampo("Email: ",campoEmail));
        painelInformacoes.add(criarCampo("Senha: ",campoSenha));

       //cria cmpos do cliente 
       JPanel painelCliente = new JPanel(new GridLayout(8,2,5,5));
       
        JTextField campoRua = new JTextField();
        JTextField campoNumero = new JTextField();
        JTextField campoBairro = new JTextField();
        JTextField campoCidade = new JTextField();
        JTextField campoEstado = new JTextField();
        JTextField campoComplemento = new JTextField();
        JTextField campoCEP = new JTextField();
        JTextField campoConta = new JTextField();
        
        painelCliente.add(criarCampo("Rua: ",campoRua));
        painelCliente.add(criarCampo("Número: ",campoNumero));
        painelCliente.add(criarCampo("Bairro: ",campoBairro));
        painelCliente.add(criarCampo("Cidade: ",campoCidade));
        painelCliente.add(criarCampo("Complemento: ",campoComplemento));
        painelCliente.add(criarCampo("CEP: ",campoCEP));
        painelCliente.add(criarCampo("Número da Conta: ",campoConta));
        painelCliente.setVisible(false);
       
        //adiciona a janela o painel principal
        JPanel painelPrincipal = new JPanel(new GridLayout(3,1,0,0));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelPrincipal.add(painelBotoes, BorderLayout.NORTH);
        painelPrincipal.add(painelInformacoes, BorderLayout.CENTER);
        painelPrincipal.add(painelCliente, BorderLayout.SOUTH);
        
        add(painelPrincipal);
        add(criarBtn, BorderLayout.SOUTH);
        
        botaoCliente.addActionListener(e->{
           painelCliente.setVisible(true);
           setSize(700,700);
        });
        
       botaoCaixa.addActionListener(e->{
           painelCliente.setVisible(false);
           setSize(500,500);
        });
        
       botaoGerente.addActionListener(e->{
           painelCliente.setVisible(false);
           setSize(500,500);
        });
        
        //evento que será acionado ao clicar no botão criar chamando o construtor do usuário
        criarBtn.addActionListener(e -> {
            //if verifica qual tipo de usuário será criado
            if(botaoGerente.isSelected()){
                 Gerente novoUsuario = new Gerente(
                        campoNome.getText(),
                        campoCPF.getText(),
                        campoDataNascimento.getText(),
                        campoTelefone.getText(),
                        campoEmail.getText(),
                        Integer.parseInt(new String(campoSenha.getPassword()))
                    );
                //adiciona novo usuario a lista de usuarios
                usuarios.add(novoUsuario);
                
                //atualiza os dados após execução
                Menu.persistencia.salvarDados(usuarios);
                    JOptionPane.showMessageDialog(this, "Usuário criado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
            } else if(botaoCaixa.isSelected()){
                 Caixa novoUsuario = new Caixa(
                        campoNome.getText(),
                        campoCPF.getText(),
                        campoDataNascimento.getText(),
                        campoTelefone.getText(),
                        campoEmail.getText(),
                        Integer.parseInt(new String(campoSenha.getPassword()))
                    );
                //adiciona novo usuario a lista de usuarios
                usuarios.add(novoUsuario);        
                //atualiza os dados após execução
                 Menu.persistencia.salvarDados(usuarios);
                  JOptionPane.showMessageDialog(this, "Usuário criado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                  setVisible(false);
            } else if(botaoCliente.isSelected()){
               Endereco endereco = new Endereco(
                   campoRua.getText(),
                   Integer.parseInt(campoNumero.getText()),
                   campoBairro.getText(),
                   campoCidade.getText(),
                   campoEstado.getText(),
                   campoComplemento.getText(),
                   campoCEP.getText()
               );
               
               ContaBancaria conta = new ContaBancaria(
                    Integer.parseInt(campoConta.getText())
               );
               
               Cliente novoUsuario = new Cliente(
                        campoNome.getText(),
                        campoCPF.getText(),
                        campoDataNascimento.getText(),
                        campoTelefone.getText(),
                        campoEmail.getText(),
                       Integer.parseInt(new String(campoSenha.getPassword())),
                        endereco,
                         conta
                    );
                //adiciona novo usuario a lista de usuarios
                usuarios.add(novoUsuario);    
                //atualiza os dados após execução
                Menu.persistencia.salvarDados(usuarios);
                JOptionPane.showMessageDialog(this, "Usuário criado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
            }
        });
        
        setVisible(true);
    }
}
   
class MenuRemoverUsuario extends JFrame{
    public MenuRemoverUsuario(){
        setTitle("Sistema Bancário - Remover Usuário");
        setSize(500, 500);
        setLocationRelativeTo(null);
         
        //cria painel com titulo
        JPanel painelRemocao = new JPanel(new GridLayout(5,3,20,20));
        painelRemocao.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        painelRemocao.add(new JLabel("Selecione o usuário que deseja remover"));
       
        //cria uma caixa de seleção com lista de usuários para remção
        JComboBox<Usuario> comboBoxUsuarios = new JComboBox<>(usuarios.toArray(new Usuario[0]));
        painelRemocao.add(comboBoxUsuarios);
        
        //botão para confirmar a ação
        JButton removerBtn = new JButton("Remover Usuário");
         painelRemocao.add(removerBtn);
        
        removerBtn.addActionListener(e -> {
               Usuario usuarioSelecionado = (Usuario) comboBoxUsuarios.getSelectedItem();
                
               //verifica se houve seleção
                    if(usuarioSelecionado!=null){
                        usuarios.remove(usuarioSelecionado);
                        Menu.persistencia.salvarDados(usuarios);
                        JOptionPane.showMessageDialog(this, "Usuário removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                         dispose();
                    }else{
                         JOptionPane.showMessageDialog(this, "Nenhum usuário foi selecionado para remoção.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
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

