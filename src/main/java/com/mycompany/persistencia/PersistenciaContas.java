/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */


package com.mycompany.persistencia;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.mycompany.systembank.ContaBancaria;
import com.mycompany.systembank.Transacao;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.mycompany.persistencia.Persistencia.DIRECTORY;

public class PersistenciaContas implements Persistencia<ContaBancaria> {

    private static final String PATH = DIRECTORY + File.separator + "contas.json";

    @Override
    public void salvarDados(List<ContaBancaria> contas) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(new TypeToken<List<Transacao>>() {
                }.getType(), new TransacaoListAdapter())
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(contas);
        System.out.println("JSON gerado: " + json);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists())
            diretorio.mkdirs();

        PersistenciaArquivo.salvaArquivo(PATH, json);
    }

    @Override
    public List<ContaBancaria> carregarDados() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(new TypeToken<List<Transacao>>() {
                }.getType(), new TransacaoListAdapter())
                .create();
        String json = PersistenciaArquivo.leArquivo(PATH);

        List<ContaBancaria> contas = new ArrayList<>();
        if (json != null && !json.trim().equals("")) {
            Type tipoLista = new TypeToken<List<ContaBancaria>>() {
            }.getType();
            contas = gson.fromJson(json, tipoLista);

            if (contas == null)
                contas = new ArrayList<>();
        }

        return contas;
    }

    // Adaptador Gson para a lista de Transacao
    private static class TransacaoListAdapter implements JsonSerializer<List<Transacao>>, JsonDeserializer<List<Transacao>> {

        @Override
        public JsonElement serialize(List<Transacao> src, Type typeOfSrc, JsonSerializationContext context) {
            JsonArray array = new JsonArray();
            for (Transacao transacao : src) {
                array.add(context.serialize(transacao));
            }
            return array;
        }

        @Override
        public List<Transacao> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            List<Transacao> transacoes = new ArrayList<>();
            JsonArray array = json.getAsJsonArray();
            for (JsonElement element : array) {
                transacoes.add(context.deserialize(element, Transacao.class));
            }
            return transacoes;
        }
    }
}