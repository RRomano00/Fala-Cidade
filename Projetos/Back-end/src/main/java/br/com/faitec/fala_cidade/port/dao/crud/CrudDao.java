package br.com.faitec.fala_cidade.port.dao.crud;

public interface CrudDao<T> extends CreateDao<T>, DeleteDao, ReadDao<T>, UpdateDao<T>{
}
