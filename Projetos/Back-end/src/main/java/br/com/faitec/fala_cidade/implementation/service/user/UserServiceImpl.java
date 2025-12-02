package br.com.faitec.fala_cidade.implementation.service.user;

import br.com.faitec.fala_cidade.domain.UserModel;
import br.com.faitec.fala_cidade.port.dao.user.UserDao;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements br.com.faitec.fala_cidade.port.service.user.UserService {

    private final UserDao userDao;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int create(UserModel entity) {

        final int invalidResponse = -1;
        if (entity == null){
            return invalidResponse;
        }
        if (entity.getRole() == null
                || entity.getFullname() == null
                || entity.getEmail() == null
                ||entity.getPassword() == null
                || entity.getFullname().isEmpty()
                || entity.getEmail().isEmpty()
                || isPasswordInvalid(entity.getPassword())
                || entity.getFullname().isBlank()
                || entity.getEmail().isBlank()
                ||entity.getPassword().isBlank()){
            return invalidResponse;
        }

        final int id = userDao.add(entity);

        return id;
    }

    private boolean isPasswordInvalid(final String password) {
        if (password.isEmpty()){
            return true;
        }
        if (password.length() < 3){
            return true;
        }
        return false;
    }

    @Override
    public void delete(int id) {
        if (id < 0){
            return;
        }

        userDao.remove(id);

    }

    @Override
    public UserModel findById(int id) {

        if (id< 0){
            return null;
        }

        UserModel entity = userDao.readById(id);

        return entity;
    }

    @Override
    public List<UserModel> findAll() {
        final List<UserModel> entities = userDao.readall();

        return entities;
    }

    @Override
    public void update(int id, UserModel entity) {

        if (id != entity.getId()){
            return;
        }

        UserModel userModel = findById(id);

        if (userModel == null){
            return;
        }

        userDao.updateInformation(id, entity);

    }

    @Override
    public UserModel findByEmail(String email) {
        if (email == null || email.isBlank()) {
            return null;
        }
        UserModel user = userDao.readByEmail(email);

        return user;
    }

    @Override
    public boolean updatePassword(int id, String oldPassword, String newPassword) {

        UserModel user = findById(id);
        if (user == null){
            return false;
        }

        if (!encoder.matches(oldPassword, user.getPassword())) {
            return false;
        }

        if (isPasswordInvalid(newPassword)){
            return false;
        }

        boolean response = userDao.updatePassword(id, newPassword);

        return response;
    }
}
