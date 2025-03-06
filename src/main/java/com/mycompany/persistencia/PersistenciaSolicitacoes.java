package com.mycompany.persistencia;

import com.google.gson.*;
import java.io.*;
import com.google.gson.reflect.TypeToken;
import static com.mycompany.persistencia.Persistencia.DIRECTORY;
import com.mycompany.systembank.Cliente;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersistenciaSolicitacoes implements Persistencia<Map<String, Object>> {

    private static final String PATH = DIRECTORY + File.separator + "solicitacoes.json";

    @Override
    public void salvarDados(List<Map<String, Object>> solicitacoes) {
        Gson gson = new Gson();
        String json = gson.toJson(solicitacoes);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        PersistenciaArquivo.salvaArquivo(PATH, json);
    }

    @Override
    public List<Map<String, Object>>  carregarDados() {
        Gson gson = new Gson();
        String json = PersistenciaArquivo.leArquivo(PATH);
        List<Map<String, Object>> solicitacoes = new ArrayList<>();

        if (json != null && !json.trim().equals("")) {
            try {
                JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();

                for (JsonElement element : jsonArray) {
                    JsonObject jsonObject = element.getAsJsonObject();
                    Map<String, Object> solicitacao = gson.fromJson(jsonObject, new TypeToken<Map<String, Object>>() {}.getType());
                    solicitacoes.add(solicitacao);
                }
            } catch (JsonSyntaxException e) {
                System.err.println("Erro ao analisar JSON: " + e.getMessage());
            }
        }

        return solicitacoes;
    }

    
    public class ClienteSerializer implements JsonSerializer<Cliente> {
    @Override
    public JsonElement serialize(Cliente cliente, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(cliente);
    }
}

    public class ClienteDeserializer implements JsonDeserializer<Cliente> {
        @Override
        public Cliente deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return context.deserialize(json, Cliente.class);
        }
    }
}

