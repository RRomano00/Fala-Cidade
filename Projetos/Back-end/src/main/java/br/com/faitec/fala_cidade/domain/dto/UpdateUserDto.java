package br.com.faitec.fala_cidade.domain.dto;


import br.com.faitec.fala_cidade.domain.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {

    private int id;
    private String fullname;

    public UserModel toUserModel(){
        final UserModel entity = new UserModel();
        entity.setId(id);
        entity.setFullname(fullname);
        return entity;
    }
}
