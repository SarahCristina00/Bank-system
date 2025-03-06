/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.persistencia;
import com.google.gson.*;
import java.io.*;
import com.google.gson.reflect.TypeToken;
import static com.mycompany.persistencia.Persistencia.DIRECTORY;
import java.lang.reflect.Type;
import com.mycompany.systembank.Gerente;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Wilian
 */
public class PersistenciaGerente implements Persistencia<Gerente> {
    
    private static final String PATH = DIRECTORY + File.separator + "gerente.json";

    @Override
    public void salvarDados(List<Gerente> gerente) {
        Gson gson = new Gson();
        String json = gson.toJson(gerente);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        // Salvando os dados no arquivo JSON
        PersistenciaArquivo.salvaArquivo(PATH, json);
    }
   
    @Override
    public List<Gerente> carregarDados() {
        Gson gson = new Gson();

        // Carregando o arquivo JSON
        String json = PersistenciaArquivo.leArquivo(PATH);

        List<Gerente> gerente = new ArrayList<>();
        if (json != null && !json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Gerente>>(){}.getType();

            // Convertendo o JSON de volta para a lista de gerente
            gerente = gson.fromJson(json, tipoLista);

            if (gerente == null) {
                gerente = new ArrayList<>();
            }
        }

        return gerente;
    }
}