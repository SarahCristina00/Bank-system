package com.mycompany.persistencia;

import java.util.List;

public interface Persistencia<T> {

   public void salvaDados(List<T> perfis);
    public List<T> CarregaDados();

}