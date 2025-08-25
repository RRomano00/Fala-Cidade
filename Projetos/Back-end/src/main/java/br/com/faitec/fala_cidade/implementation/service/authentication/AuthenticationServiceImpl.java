package br.com.faitec.fala_cidade.implementation.service.authentication;

import br.com.faitec.fala_cidade.domain.UserModel;
import br.com.faitec.fala_cidade.port.dao.user.UserDao;
import br.com.faitec.fala_cidade.port.service.authentication.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserDao userDao;

    public AuthenticationServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public UserModel authenticate(String email, String password) {

        if (email == null || email.isEmpty() || password == null || password.isEmpty()){
            return null;
        }
        UserModel user = userDao.readByEmail(email);

        if (user == null){
            return null;
        }

        if (user.getPassword().equals(password)){
            return user;
        }

        return null;
    }
}
