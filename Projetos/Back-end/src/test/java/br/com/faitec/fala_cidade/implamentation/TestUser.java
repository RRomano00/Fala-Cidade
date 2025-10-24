package br.com.faitec.fala_cidade.implamentation;

import br.com.faitec.fala_cidade.domain.UserModel;
import br.com.faitec.fala_cidade.implementation.service.user.UserServiceImpl;
import br.com.faitec.fala_cidade.port.dao.user.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TestUser {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService = new UserServiceImpl(userDao);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userDao);
    }

    @Test
    public void testCreate_ShouldCallDaoAdd_WhenValidUser() {
        UserModel user = new UserModel();
        user.setFullname("John");
        user.setEmail("john@test.com");
        user.setPassword("1234");
        user.setRole(UserModel.UserRole.USER);


        when(userDao.add(any(UserModel.class))).thenReturn(1);

        int result = userService.create(user);

        assertEquals(1, result);
    }

    @Test
    public void testCreate_ShouldCallDaoAdd_WhenInvalidName() {
        UserModel user = new UserModel();
        user.setFullname("");
        user.setEmail("john@test.com");
        user.setPassword("1234");
        user.setRole(UserModel.UserRole.USER);

        int result = userService.create(user);

        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldCallDaoAdd_WhenBlankName() {
        UserModel user = new UserModel();
        user.setFullname("  ");
        user.setEmail("john@test.com");
        user.setPassword("1234");
        user.setRole(UserModel.UserRole.USER);

        int result = userService.create(user);

        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldCallDaoAdd_WhenInvalidEmail() {
        UserModel user = new UserModel();
        user.setFullname("Jonh");
        user.setEmail("");
        user.setPassword("1234");
        user.setRole(UserModel.UserRole.USER);

        int result = userService.create(user);

        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldCallDaoAdd_WhenBlankEmail() {
        UserModel user = new UserModel();
        user.setFullname("Jonh");
        user.setEmail("  ");
        user.setPassword("1234");
        user.setRole(UserModel.UserRole.USER);

        int result = userService.create(user);

        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldCallDaoAdd_WhenInvalidPassword() {
        UserModel user = new UserModel();
        user.setFullname("Jonh");
        user.setEmail("john@test.com");
        user.setPassword("12");
        user.setRole(UserModel.UserRole.USER);

        int result = userService.create(user);

        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldCallDaoAdd_WhenBlankPassword() {
        UserModel user = new UserModel();
        user.setFullname("Jonh");
        user.setEmail("john@test.com");
        user.setPassword("    ");
        user.setRole(UserModel.UserRole.USER);

        int result = userService.create(user);

        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldCallDaoAdd_WhenInvalidRole() {
        UserModel user = new UserModel();
        user.setFullname("Jonh");
        user.setEmail("john@test.com");
        user.setPassword("1234");
        user.setRole(null);

        int result = userService.create(user);

        assertEquals(-1, result);
    }
}