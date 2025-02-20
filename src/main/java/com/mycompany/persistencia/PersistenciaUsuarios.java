
 /**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */
package com.mycompany.persistencia;


import com.google.gson.*;
import com.mycompany.systembank.*;
import java.io.*;
import java.util.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class PersistenciaUsuarios {
    private static String DIRECTORY = "data";
    private static final String PATH = DIRECTORY+ File.separator +"usuarios.json";

   public void salvarDados(List<Usuario> usuarios) {
        Gson gson = new Gson();
        String json = gson.toJson(usuarios);

        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();

        PersistenciaArquivo.salvaArquivo(PATH, json);
   }
       
    public List<Usuario> carregarDados() {
        Gson gson = new Gson();

        String json = PersistenciaArquivo.leArquivo(PATH);

        List<Usuario> usuarios = new ArrayList<>();
        if(!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Usuario>>() {
            }.getType();
        usuarios = gson.fromJson(json, tipoLista);

            if (usuarios == null)
                usuarios = new ArrayList<>();
        }

        return usuarios;
    }
}





