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
import java.ultil.List;
public class Extrato {
    private List<movimentacoes> movimentacoes;

    public Extrato{
    this.movimentacoes = new ArrayList<>();
    }
    public void registraMovimentacao(String tipo, double valor, double saldo){
        Movimentacoes movimentacao = new Movimentacoes(tipo, valor, saldo);
        movimentacoes.add(movimentacao);
    }
}
