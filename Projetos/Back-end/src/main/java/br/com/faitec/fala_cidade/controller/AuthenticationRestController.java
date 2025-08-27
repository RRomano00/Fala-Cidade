package br.com.faitec.fala_cidade.controller;

import br.com.faitec.fala_cidade.domain.UserModel;
import br.com.faitec.fala_cidade.domain.dto.AuthenticationDto;
import br.com.faitec.fala_cidade.port.service.authentication.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authenticate")
public class AuthenticationRestController {

    private final AuthenticationService authenticationService;

    public AuthenticationRestController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<UserModel> authenticate(@RequestBody final AuthenticationDto authenticationDto){

        UserModel authenticateUser = authenticationService.authenticate(authenticationDto.getEmail(), authenticationDto.getPassword());

        if (authenticateUser == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(authenticateUser);
    }
}
