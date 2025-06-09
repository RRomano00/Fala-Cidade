package br.com.faitec.fala_cidade.port.service.authentication;

import br.com.faitec.fala_cidade.domain.UserModel;

public interface AthenticationService {

    UserModel authenticate(final String email, final String password);

}
