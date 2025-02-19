/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.systembank;

/**
 *
 * @author Lara
 */
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Extrato {
    private final List<movimentacoes> movimentacoes;
    private class Movimentacao{
        private String tipo;
        private double valor;
        private double saldoFinal;
        private LocalDate data;
    }
    
    public Movimentacao(String tipo, double valor, double SaldoFinal){
        this.tipo = tipo;
        this.valor = valor;
        this.saldoFinal = saldoFinal;
        this.data = LocalDate.now();
    }
    @Override
    public String ToString(){
        DateTimeFormatter formatoData =  DateTimeFormatter.ofPattern("MM/yyyy");
        String mesAno = data.format(formatoData);
            return String.format("%s | %s | Valor: R$ %.2f | Saldo: R$ %.2f", mesAno, tipo, valor, saldoFinal);
    }
    
    public Extrato(){
    this.movimentacoes = new ArrayList<>();
    }
    public void registraMovimentacao(String tipo, double valor, double saldo){
        movimentacoes movimentacao = new Movimentacoes(tipo, valor, saldo);
        movimentacoes.add(movimentacao);
    }
    public void imprimeExtrato(){
        System.out.println("----------Extrato de Movimentações----------");
        for(int i=0; i<movimentacoes.size(); i++){
            Movimentacoes movimentacao = movimentacoes.get(i);
            System.out.println(movimentacao);
        }
        
}
