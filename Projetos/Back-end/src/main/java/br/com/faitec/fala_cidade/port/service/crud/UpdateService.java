package br.com.faitec.fala_cidade.port.service.crud;

public interface UpdateService <T>{

    void update(final int id, final T entity);

}
