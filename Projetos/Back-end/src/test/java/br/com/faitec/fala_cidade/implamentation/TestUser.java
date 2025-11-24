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
    public void testCreate_ShouldReturnError_WhenInvalidName() {
        UserModel user = new UserModel();
        user.setFullname("");
        user.setEmail("john@test.com");
        user.setPassword("1234");
        user.setRole(UserModel.UserRole.USER);

        int result = userService.create(user);

        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldReturnError_WhenBlankName() {
        UserModel user = new UserModel();
        user.setFullname("  ");
        user.setEmail("john@test.com");
        user.setPassword("1234");
        user.setRole(UserModel.UserRole.USER);

        int result = userService.create(user);

        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldReturnError_WheNullName() {
        UserModel user = new UserModel();
        user.setFullname(null);
        user.setEmail("john@test.com");
        user.setPassword("1234");
        user.setRole(UserModel.UserRole.USER);

        int result = userService.create(user);

        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldReturnError_WhenInvalidEmail() {
        UserModel user = new UserModel();
        user.setFullname("Jonh");
        user.setEmail("");
        user.setPassword("1234");
        user.setRole(UserModel.UserRole.USER);

        int result = userService.create(user);

        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldReturnError_WhenBlankEmail() {
        UserModel user = new UserModel();
        user.setFullname("Jonh");
        user.setEmail("  ");
        user.setPassword("1234");
        user.setRole(UserModel.UserRole.USER);

        int result = userService.create(user);

        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldReturnError_WhenEmailIsNull() {
        UserModel user = new UserModel();
        user.setFullname("Jonh");
        user.setEmail(null);
        user.setPassword("1234");
        user.setRole(UserModel.UserRole.USER);

        int result = userService.create(user);

        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldReturnError_WhenInvalidPassword() {
        UserModel user = new UserModel();
        user.setFullname("Jonh");
        user.setEmail("john@test.com");
        user.setPassword("12");
        user.setRole(UserModel.UserRole.USER);

        int result = userService.create(user);

        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldReturnError_WhenBlankPassword() {
        UserModel user = new UserModel();
        user.setFullname("Jonh");
        user.setEmail("john@test.com");
        user.setPassword("    ");
        user.setRole(UserModel.UserRole.USER);

        int result = userService.create(user);

        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldReturnError_WhenEmpityPassword() {
        UserModel user = new UserModel();
        user.setFullname("Jonh");
        user.setEmail("john@test.com");
        user.setPassword("");
        user.setRole(UserModel.UserRole.USER);

        int result = userService.create(user);

        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldReturnError_WhenNullPassword() {
        UserModel user = new UserModel();
        user.setFullname("Jonh");
        user.setEmail("john@test.com");
        user.setPassword(null);
        user.setRole(UserModel.UserRole.USER);

        int result = userService.create(user);

        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldReturnError_WhenInvalidRole() {
        UserModel user = new UserModel();
        user.setFullname("Jonh");
        user.setEmail("john@test.com");
        user.setPassword("1234");
        user.setRole(null);

        int result = userService.create(user);

        assertEquals(-1, result);
    }

    @Test
    public void testFindById_ShouldReturnUser_WhenIdIsValid() {
        UserModel userEsperado = new UserModel();
        userEsperado.setId(1);
        userEsperado.setFullname("John");
        userEsperado.setEmail("john@test.com");

        when(userDao.readById(1)).thenReturn(userEsperado);

        UserModel userResultado = userService.findById(1);

        assertNotNull(userResultado);
        assertEquals(1, userResultado.getId());
        assertEquals("John", userResultado.getFullname());
    }

    @Test
    public void testFindById_ShouldReturnNull_WhenIdIsNegative() {
        UserModel userResultado = userService.findById(-1);
        assertNull(userResultado);
    }

    @Test
    public void testFindById_ShouldReturnNull_WhenUserNotFound() {
        when(userDao.readById(99)).thenReturn(null);
        UserModel userResultado = userService.findById(99);
        assertNull(userResultado);
    }

    @Test
    public void testFindByEmail_ShouldReturnUser_WhenEmailIsValid() {
        UserModel userEsperado = new UserModel();
        userEsperado.setId(1);
        userEsperado.setEmail("john@test.com");

        when(userDao.readByEmail("john@test.com")).thenReturn(userEsperado);

        UserModel userResultado = userService.findByEmail("john@test.com");

        assertNotNull(userResultado);
        assertEquals("john@test.com", userResultado.getEmail());
    }

    @Test
    public void testFindByEmail_ShouldReturnNull_WhenEmailIsEmpty() {
        UserModel userResultado = userService.findByEmail("");
        assertNull(userResultado);
    }

    @Test
    public void testFindByEmail_ShouldReturnNull_WhenEmailIsNull() {
        UserModel userResultado = userService.findByEmail(null);
        assertNull(userResultado);
    }

    @Test
    public void testUpdatePassword_ShouldReturnFalse_WhenUserNotFound() {
        when(userDao.readById(99)).thenReturn(null);
        boolean resultado = userService.updatePassword(99, "senhaAntiga", "senhaNova");
        assertFalse(resultado);
    }

    @Test
    public void testUpdatePassword_ShouldReturnFalse_WhenNewPasswordIsInvalid() {
        UserModel user = new UserModel();
        user.setId(1);
        user.setPassword("senhaHashedQualquer");

        when(userDao.readById(1)).thenReturn(user);

        boolean resultado = userService.updatePassword(1, "senhaAntiga", "aa");

        assertFalse(resultado);
    }

}