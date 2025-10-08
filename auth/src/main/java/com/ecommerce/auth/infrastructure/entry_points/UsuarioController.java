package com.ecommerce.auth.infrastructure.entry_points;

import com.ecommerce.auth.domain.model.Usuario;
import com.ecommerce.auth.domain.usecase.UsuarioUseCase;
import com.ecommerce.auth.infrastructure.driver_adapters.jpa_repository.UsuarioData;
import com.ecommerce.auth.infrastructure.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ecommerce/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioUseCase usuarioUseCase;

    @PostMapping("/save")
    public ResponseEntity<Usuario> saveUsuario(@RequestBody UsuarioData usuarioData) {

        Usuario usuarioConvertido = usuarioMapper.toUsuario(usuarioData);
        Usuario usuario = usuarioUseCase.guardarUsuario(usuarioConvertido);

        if (usuario.getId() != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
        return new ResponseEntity<>(usuario, HttpStatus.NOT_FOUND);


    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findByIdUsuarios(@PathVariable Long id) {

        Usuario usuario = usuarioUseCase.buscarUsuarioPorId(id);

        if (usuario.getId() != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK); //200//
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteByIdUsuarios(@PathVariable Long id) {
//
//        Usuario usuario = usuarioUseCase.eliminarUsuario(id);
//
//        return new ResponseEntity<>(null, HttpStatus.OK);
//
//    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuarioById(@PathVariable Long id) {
        try {
            usuarioUseCase.eliminarUsuario(id);
            return new ResponseEntity<>("Usuario Eliminado correctamente", HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>("Hubo un error", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Usuario> actualizarUsuario(@RequestBody UsuarioData usuarioData) {
        try {
            Usuario usuario = usuarioMapper.toUsuario(usuarioData);
            Usuario usuarioValidadoActualizado = usuarioUseCase.actualizarUsuario(usuario);
            return new ResponseEntity<>(usuarioValidadoActualizado, HttpStatus.OK);

        } catch (Exception error) {

            return ResponseEntity.notFound().build();


        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> buscarUsuarioPorEmail(@RequestParam String email, @RequestParam String password) {
        try {
            Usuario usuario = usuarioUseCase.buscarUsuarioPorEmail(email, password);
            return ResponseEntity.ok(usuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se encuentra el email");
        }
    }
}



// @GetMapping ("/login/{email}")
    //public ResponseEntity<Usuario> findByEmail(@PathVariable String email) {
    //return usuarioUseCase.buscarUsuarioPorEmail(email)
    //.map(usuario -> ResponseEntity.ok(usuario))
    //.orElse(ResponseEntity.notFound().build());
    //}

    //@PostMapping("/login")
    //public ResponseEntity<String> login(@RequestBody String email, String password) {

    //String resultado = usuarioUseCase.logear(email, password);
    //if (resultado.equals("Usuario encontrado")) {
    //return new ResponseEntity<>("Usuario encontrado", HttpStatus.OK);
    //} else {
    //return new ResponseEntity<>(resultado, HttpStatus.UNAUTHORIZED);






