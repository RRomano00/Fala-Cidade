package br.com.faitec.fala_cidade.port.dao.user;

public interface UpdatePasswordDao {

    boolean updatePassword(final int id, final String newPassword);

}
