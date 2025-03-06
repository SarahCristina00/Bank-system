
 /**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */
package com.mycompany.persistencia;

import com.google.gson.*;
import com.mycompany.interfaces.Login;
import com.mycompany.systembank.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersistenciaUsuarios implements Persistencia<Usuario> {

    private static final String PATH = Persistencia.DIRECTORY + File.separator + "usuarios.json";
    private static Map<Integer, ContaBancaria> mapaContas = new HashMap<>();

    @Override
    public void salvarDados(List<Usuario> usuarios) {
        Gson gson = new Gson();
        String json = gson.toJson(usuarios);

        File diretorio = new File(Persistencia.DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        PersistenciaArquivo.salvaArquivo(PATH, json);
        // Salva as contas após salvar os usuários
        Login.persistenciaContas.salvarDados(new ArrayList<>(mapaContas.values()));
    }

    @Override
    public List<Usuario> carregarDados() {
        Gson gson = new Gson();
        String json = PersistenciaArquivo.leArquivo(PATH);
        List<Usuario> usuarios = new ArrayList<>();

        if (json != null && !json.trim().equals("")) {
            // Carrega as contas e cria o mapa de contas
            List<ContaBancaria> contas = Login.persistenciaContas.carregarDados();
            for (ContaBancaria conta : contas) {
                mapaContas.put(conta.getConta(), conta);
            }

            JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();

            for (JsonElement element : jsonArray) {
                JsonObject jsonObject = element.getAsJsonObject();
                String tipoUsuario = jsonObject.get("tipoUsuario").getAsString();

                if ("usuario".equalsIgnoreCase(tipoUsuario)) {
                    usuarios.add(gson.fromJson(element, Usuario.class));
                } else if ("cliente".equalsIgnoreCase(tipoUsuario)) {
                    Cliente cliente = gson.fromJson(element, Cliente.class);
                    // Atualiza a conta do cliente com a conta do mapa de contas
                    ContaBancaria conta = mapaContas.get(cliente.getConta().getConta());
                    if (conta != null) {
                        cliente.setConta(conta);
                    }
                    usuarios.add(cliente);
                } else if ("gerente".equalsIgnoreCase(tipoUsuario)) {
                    usuarios.add(gson.fromJson(element, Gerente.class));
                } else if ("caixa".equalsIgnoreCase(tipoUsuario)) {
                    usuarios.add(gson.fromJson(element, Caixa.class));
                }
            }
        }
        return usuarios;
    }

    public static void atualizarConta(ContaBancaria conta) {
        mapaContas.put(conta.getConta(), conta);
    }
}