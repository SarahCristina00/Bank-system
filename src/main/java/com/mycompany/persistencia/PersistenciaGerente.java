/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

 /**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */
package com.mycompany.persistencia;

import com.google.gson.*;
import com.mycompany.systembank.Gerente;
import java.io.*;
import java.util.*;
import com.google.gson.reflect.TypeToken;
import static com.mycompany.persistencia.Persistencia.DIRECTORY;
import java.lang.reflect.Type;

public class PersistenciaGerente implements Persistencia<Gerente> {

    private static final String PATH = DIRECTORY + File.separator + "gerente.json";

    @Override
    public void salvarDados(List<Gerente> gerentes) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(gerentes);

            File diretorio = new File(DIRECTORY);
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }

            PersistenciaArquivo.salvaArquivo(PATH, json);
        } catch (Exception e) {
            System.err.println("Erro ao salvar gerentes: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Gerente> carregarDados() {
        try {
            Gson gson = new Gson();
            String json = PersistenciaArquivo.leArquivo(PATH);

            List<Gerente> gerentes = new ArrayList<>();
            if (json != null && !json.trim().equals("")) {
                Type tipoLista = new TypeToken<List<Gerente>>() {}.getType();
                gerentes = gson.fromJson(json, tipoLista);

                if (gerentes == null) {
                    gerentes = new ArrayList<>();
                }
            }

            return gerentes;
        } catch (Exception e) {
            System.err.println("Erro ao carregar gerentes: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>(); // Retorna uma lista vazia em caso de erro
        }
    }
}