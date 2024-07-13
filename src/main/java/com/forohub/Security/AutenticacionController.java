package com.forohub.Security;

import com.forohub.Security.DatosAutenticacionUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> autenticarUsuario(@RequestBody DatosAutenticacionUsuario datosAutenticacionUsuario) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            datosAutenticacionUsuario.getLogin(),
                            datosAutenticacionUsuario.getClave()
                    )
            );
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
