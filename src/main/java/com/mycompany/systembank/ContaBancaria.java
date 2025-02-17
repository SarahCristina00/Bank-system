/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.systembank;

/**
 *
 * @author Lara
 */
public class ContaBancaria {
    private int agencia;
    private int conta;
    private double saldo;
    
    private Extrato extrato;


public ContaBancaria (int agencia, int conta, int saldo, Extrato extrato){
            this.agencia = agencia;
            this.conta = conta;
            this.saldo = saldo;
            this.extrato = extrato;
    }
    
    public int  getAgencia() { return agencia; } 
    public void setAgencia(int agencia) { this.agencia = agencia; }
    public int  getConta() { return conta; } 
    public void setConta(int conta) { this.conta = conta; }
    public double  getSaldo() { return saldo; } 
    public void setSaldo(int saldo) { this.saldo = saldo; }
}
