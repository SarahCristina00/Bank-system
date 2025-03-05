/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */

package com.mycompany.systembank;

import java.util.*;

public class ContaBancaria {
    private static int agencia = 001;
    private static int proximaConta = 1000;
    private int conta;
    private double saldo;
    private List<Transacao> extrato;

    public ContaBancaria() {
        this.conta = proximaConta++; // Atribui o próximo número de conta e incrementa
        this.saldo = 0;
        this.extrato = new ArrayList<>();
    }

    // Método para retornar a lista de transações
    public List<Transacao> getExtrato() {
        return extrato;
    }
    
    public int  getAgencia() { return agencia; } 
    public int  getConta() { return conta; } 
    public double getSaldo() { return saldo; } 
    public void setSaldo(double saldo) { this.saldo = saldo; }
     
    public void registraTransacao(String tipo, double valor, ContaBancaria origem, ContaBancaria destino){
        Transacao transacao = new Transacao(tipo, valor, origem, destino);
        extrato.add(transacao);
    }
    
    public boolean transfereSaldo(double valor, ContaBancaria destino){
       //verifica saldo
       if (saldo < valor || saldo<0) {
           return false;
       }
        //retira valor da conta
        this.saldo -= valor;
        //adiciona valor na conta origem
        destino.saldo += valor;
        //registra a transferencia nos extratos das duas contas
        this.registraTransacao("Transferência enviada", valor, this, destino);
        destino.registraTransacao("Transferência recebida", valor, this, destino);
        return true;
    }
        
    public void imprimeExtrato(){
        System.out.println("=======Extrato de Transações=======");
        for(int i=0; i<extrato.size(); i++){
            Transacao transacao = extrato.get(i);
            System.out.println(transacao);
        }
    } 
}