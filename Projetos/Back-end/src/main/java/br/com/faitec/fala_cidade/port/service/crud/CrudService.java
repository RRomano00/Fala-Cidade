package br.com.faitec.fala_cidade.port.service.crud;

public interface CrudService <T> extends CreateService<T>, DeleteService, UpdateService<T>, ReadService<T>{
}
