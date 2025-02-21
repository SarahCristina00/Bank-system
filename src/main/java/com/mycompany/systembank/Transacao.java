/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */

package com.mycompany.systembank;

import java.util.Calendar;

public class Transacao {
    static int id=0;
    private String tipo;
    private double valor;
    private double saldoFinal;
    private Calendar data;
    private ContaBancaria origem;
    private ContaBancaria destino;
     
    
    public Transacao(String tipo, double valor, ContaBancaria contaOrigem,ContaBancaria contaDestino){
        id++;
        this.tipo = tipo;
        this.valor = valor;
        this.saldoFinal = contaOrigem.getSaldo() + valor;
        this.data = Calendar.getInstance();
        origem =contaOrigem;
        destino = contaDestino;
    }

    public String exibirTransacao(){
                return String.format("%s | %s | Valor: R$ %.2f | Saldo: R$ %.2f", data, tipo, valor, saldoFinal);
    }
    
}
