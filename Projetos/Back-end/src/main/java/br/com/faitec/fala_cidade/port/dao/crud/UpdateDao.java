package br.com.faitec.fala_cidade.port.dao.crud;

public interface UpdateDao <T>{

    void updateInformation(final int id, final T entity);

}
