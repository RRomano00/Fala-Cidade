package br.com.faitec.fala_cidade.port.service.user;

import br.com.faitec.fala_cidade.domain.UserModel;

public interface ReadByEmailService {

    UserModel findByEmail(final String email);

}
