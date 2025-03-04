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
    private ContaBancaria origem;
    private ContaBancaria destino;

    public Transacao(String tipo, double valor, ContaBancaria contaOrigem, ContaBancaria contaDestino) {
        id++;
        this.tipo = tipo;
        this.valor = valor;
        this.saldoFinal = contaOrigem.getSaldo() + valor;
        this.data = Calendar.getInstance();
        this.origem = contaOrigem;
        this.destino = contaDestino;
    }

    // Método para formatar a data como string
    public String getDataFormatada() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formato.format(data.getTime());
    }

    // Getters para os atributos
    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public double getSaldoFinal() {
        return saldoFinal;
    }
}
