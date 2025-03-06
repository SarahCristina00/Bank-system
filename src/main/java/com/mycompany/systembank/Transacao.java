/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */

package com.mycompany.systembank;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Transacao {
    static int id = 0;
    private String tipo;
    private double valor;
    private double saldoFinal;
    private Calendar data;
    private int origem;
    private int destino;
    
    public Transacao(){}
    public Transacao(String tipo, double valor, double saldoInicial, ContaBancaria contaOrigem, ContaBancaria contaDestino) {
        id++;
        this.tipo = tipo;
        this.valor = valor;
        this.data = Calendar.getInstance();
        this.origem = (contaOrigem != null) ? contaOrigem.getConta() : 0;
        this.destino = (contaDestino != null) ? contaDestino.getConta() : 0;
        if(tipo.equals("Transferência enviada") || tipo.equals("Saque")){
            this.saldoFinal = saldoInicial - valor;
        }else if (tipo.equals("Transferência recebida") || tipo.equals("Deposito")) {
            this.saldoFinal = saldoInicial + valor;
        }
    }

    // Método para formatar a data como string
    public String getDataFormatada() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formato.format(data.getTime());
    }

    // Getters para os atributos
    public String getTipo() {return tipo;}

    public double getValor() {return valor;}

    public double getSaldoFinal() {return saldoFinal;}
    
    @Override
    public String toString() {
        return "Transacao{" +
                "tipo='" + tipo + '\'' +
                ", valor=" + valor +
                ", saldoFinal=" + saldoFinal +
                ", dataFormatada='" + getDataFormatada() + '\'' +
                ", origem=" + origem +
                ", destino=" + destino +
                '}';
    }
}

