/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */

package com.mycompany.systembank;

import java.util.List;

public class ContaBancaria {
    private int agencia;
    private int conta;
    private double saldo;
    private List<Transacao> extrato = null;


    public ContaBancaria (int agencia, int conta, int saldo){
        this.agencia = agencia;
        this.conta = conta;
        this.saldo = saldo;
    }
    
    public int  getAgencia() { return agencia; } 
    public void setAgencia(int agencia) { this.agencia = agencia; }
    public int  getConta() { return conta; } 
    public void setConta(int conta) { this.conta = conta; }
    public double getSaldo() { return saldo; } 
    public void setSaldo(int saldo) { this.saldo = saldo; }
     
    public void registraTransacao(String tipo, double valor, ContaBancaria origem, ContaBancaria destino){
        Transacao transacao = new Transacao(tipo, valor, origem, destino);
        extrato.add(transacao);
    }
        
    public void imprimeExtrato(){
        System.out.println("=======Extrato de Transações=======");
        for(int i=0; i<extrato.size(); i++){
            Transacao transacao = extrato.get(i);
            System.out.println(transacao);
        }
    } 
}
