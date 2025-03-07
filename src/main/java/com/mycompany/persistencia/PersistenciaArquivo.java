/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */
package com.mycompany.persistencia;

import java.io.*;

public class PersistenciaArquivo {

    public static String leArquivo(String filePath) {
        StringBuilder conteudo = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }

        return conteudo.toString();
    }

    public static void salvaArquivo(String filePath, String conteudo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            writer.write(conteudo);
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
}