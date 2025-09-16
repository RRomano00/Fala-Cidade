package br.com.faitec.fala_cidade.implementation.service.authentication;

import br.com.faitec.fala_cidade.domain.UserModel;
import br.com.faitec.fala_cidade.port.service.user.UserService;
import br.com.faitec.fala_cidade.port.service.authentication.AuthenticationService;

public class BasicAuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    public BasicAuthenticationServiceImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserModel authenticate(String email, String password) {

        if (email == null || email.isEmpty() || password == null || password.isEmpty()){
            return null;
        }
        UserModel user = userService.findByEmail(email);

        if (user == null){
            return null;
        }

        if (user.getPassword().equals(password)){
            return user;
        }

        return null;
    }
}
