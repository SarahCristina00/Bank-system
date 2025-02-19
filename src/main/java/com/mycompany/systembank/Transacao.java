/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */

package com.mycompany.systembank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Transacao {
    static int id=0;
    private String tipo;
    private double valor;
    private double saldoFinal;
    private LocalDateTime data;
    private ContaBancaria origem;
    private ContaBancaria destino;
    
    private final List<Transacao> extrato = null;
 
    
    public Transacao(String tipo, double valor, ContaBancaria contaOrigem,ContaBancaria contaDestino){
        id++;
        this.tipo = tipo;
        this.valor = valor;
        this.saldoFinal = contaOrigem.getSaldo() + valor;
        this.data = LocalDateTime.now();
        origem =contaOrigem;
        destino = contaDestino;
    }

    public String exibirTransacao(){
        DateTimeFormatter formatoData =  DateTimeFormatter.ofPattern("MM/yyyy");
        String mesAno = data.format(formatoData);
            return String.format("%s | %s | Valor: R$ %.2f | Saldo: R$ %.2f", mesAno, tipo, valor, saldoFinal);
    }
    
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
