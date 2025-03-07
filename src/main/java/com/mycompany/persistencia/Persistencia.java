/**
@author Lara da Silva Dias (202376010)
@author Sarah Cristina (202376034)
@author Wilian Santos (202276040)
 */
package com.mycompany.persistencia;

import java.util.List;

public interface Persistencia<T> {
    String DIRECTORY = "data";
    public void salvarDados(List<T> perfis);
    public List<T> carregarDados();

}