package com.mycompany.persistencia;

import java.util.List;

public interface Persistencia<T> {
    String DIRECTORY = "data";
    public void salvarDados(List<T> perfis);
    public List<T> carregarDados();

}