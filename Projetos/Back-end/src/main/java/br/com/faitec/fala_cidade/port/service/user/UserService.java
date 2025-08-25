package br.com.faitec.fala_cidade.port.service.user;

import br.com.faitec.fala_cidade.domain.UserModel;
import br.com.faitec.fala_cidade.port.service.crud.CrudService;

public interface UserService extends CrudService<UserModel>, ReadByEmailService, UpdatePasswordService {
}
