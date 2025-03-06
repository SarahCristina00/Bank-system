
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

public class PersistenciaUsuarios implements Persistencia<Usuario>{
    
    private static final String PATH = DIRECTORY+ File.separator +"usuarios.json";

   @Override
   public void salvarDados(List<Usuario> usuarios) {
        Gson gson = new Gson();
        String json = gson.toJson(usuarios);

        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();

        PersistenciaArquivo.salvaArquivo(PATH, json);
   }
   
   @Override
       
    public List<Usuario> carregarDados() {
        Gson gson = new Gson();

        String json = PersistenciaArquivo.leArquivo(PATH);

        List<Usuario> usuarios = new ArrayList<>();
            if (json != null && !json.trim().equals("")) {
                Type tipoLista = new TypeToken<List<Usuario>>() {}.getType();
                usuarios = gson.fromJson(json, tipoLista);  

                // verificação dos tipos de usu
                for (int i = 0; i < usuarios.size(); i++) {
                    Usuario usuario = usuarios.get(i);               
                    if ("cliente".equalsIgnoreCase(usuario.getTipoUsuario())) {
                    // para cliente
                    usuarios.set(i, gson.fromJson(gson.toJson(usuario), Cliente.class));
                } else if ("gerente".equalsIgnoreCase(usuario.getTipoUsuario())) {
                    //  para gerente
                    usuarios.set(i, gson.fromJson(gson.toJson(usuario), Gerente.class));
                } else if ("caixa".equalsIgnoreCase(usuario.getTipoUsuario())) {
                    //  para caixa
                    usuarios.set(i, gson.fromJson(gson.toJson(usuario), Caixa.class));
                }
                }
            }

        return usuarios;
    }
}





