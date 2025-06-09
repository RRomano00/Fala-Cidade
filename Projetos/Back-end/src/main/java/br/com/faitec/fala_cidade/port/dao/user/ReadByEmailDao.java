package br.com.faitec.fala_cidade.port.dao.user;

import br.com.faitec.fala_cidade.domain.UserModel;

public interface ReadByEmailDao {

    UserModel readByEmail(final String email);

}
