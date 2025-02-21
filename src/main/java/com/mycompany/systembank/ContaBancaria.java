/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */

package com.mycompany.systembank;

import java.util.List;

public class ContaBancaria {
    private int agencia = 001;
    private int conta = 1000;
    private double saldo;
    private List<Transacao> extrato;


    public ContaBancaria (){
        conta = conta++;
        saldo = 0;
    }
    
    public int  getAgencia() { return agencia; } 
    public int  getConta() { return conta; } 
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
