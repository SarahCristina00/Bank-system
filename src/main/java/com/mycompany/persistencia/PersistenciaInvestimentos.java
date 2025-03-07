/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */
package com.mycompany.persistencia;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersistenciaInvestimentos {

    private static final String PATH = Persistencia.DIRECTORY + File.separator + "investimentos.json";

    public static void salvarDados(List<Map<String, Object>> dados) {
        Gson gson = new Gson();
        String json = gson.toJson(dados);
        PersistenciaArquivo.salvaArquivo(PATH, json);
    }

    public static List<Map<String, Object>> carregarDados() {
        Gson gson = new Gson();
        String json = PersistenciaArquivo.leArquivo(PATH);
        Type tipoLista = new TypeToken<List<Map<String, Object>>>() {}.getType();
        return gson.fromJson(json, tipoLista) != null ? gson.fromJson(json, tipoLista) : new ArrayList<>();
    }
}