/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */

package com.mycompany.systembank;

public class Usuario {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String telefone;
    private String email;
    private String login;
    private int senha;
    private String tipoUsuario="usuario";

    // Construtor
    public Usuario(String nome, String cpf, String dataNascimento, String telefone, String email, String login, int senha) {
        this.nome = nome;
        this.cpf = validarCpf(cpf) ? cpf : "CPF inválido"; //validarCpf(cpf) que retorna true se o CPF for válido e false caso contrário.
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = validarEmail(email) ? email : "Email inválido";//validarEmail(email) que retorna true se o CPF for válido e false caso contrário.
        this.login = login;
        this.senha = senha;
    }
    
    public String getTipoUsuario(){return tipoUsuario;}
    
    public String getNome() { return nome; } // retorna o nome do usuario
    public void setNome(String nome) { this.nome = nome; }// recebe o nome do usuario

    public String getCpf() { return cpf; }// retorna o cpf do usuario
    public void setCpf(String cpf) { if (validarCpf(cpf)) this.cpf = cpf; }// recebe cpf do usuario

    public String getDataNascimento() { return dataNascimento; }// retorna a data  de nascimento
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }// recebe a data de nascimento

    public String getTelefone() { return telefone; } // retorna telefone do usuario
    public void setTelefone(String telefone) { this.telefone = telefone; }//recebe o telefone do usuario

    public String getEmail() { return email; } // retorna email do usuario
    public void setEmail(String email) { if (validarEmail(email)) this.email = email; }// recebe email do usuario

    public String getLogin() { return login; } // retorna login do usuario
    public void setLogin(String login) { this.login = login; } // recebe login do usuario

    public int getSenha() { return senha;} // retorna senha do usuario
    public void setSenha(int senha) { this.senha = senha; }// recebe  a senha do usuario

    // Validação de CPF (formato 000.000.000-00)
    private boolean validarCpf(String cpf) {
        return cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }

    // Validação de email
    private boolean validarEmail(String email) {
        return email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }
    
    @Override
    public String toString() {
        return nome + " - " + login; 
    }

  }



