
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
import static com.mycompany.persistencia.Persistencia.DIRECTORY;
import java.lang.reflect.Type;

public class PersistenciaContas implements Persistencia<ContaBancaria>{
    
    private static final String PATH = DIRECTORY+ File.separator +"contas.json";

   @Override
   public void salvarDados(List<ContaBancaria> contas) {
        Gson gson = new Gson();
        String json = gson.toJson(contas);

        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();

        PersistenciaArquivo.salvaArquivo(PATH, json);
   }
   
   @Override
       
    public List<ContaBancaria> carregarDados() {
        Gson gson = new Gson();

        String json = PersistenciaArquivo.leArquivo(PATH);

        List<ContaBancaria> contas = new ArrayList<>();
        if(json!=null && !json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Usuario>>() {
            }.getType();
        contas = gson.fromJson(json, tipoLista);

            if (contas == null)
                contas = new ArrayList<>();
        }

        return contas;
    }
}





