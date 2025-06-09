package br.com.faitec.fala_cidade.port.dao.crud;

import java.util.List;

public interface ReadDao <T>{

    T readById(final int id);

    List<T> readall();

}
