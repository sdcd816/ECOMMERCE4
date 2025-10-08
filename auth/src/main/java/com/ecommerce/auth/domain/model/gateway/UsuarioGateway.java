package com.ecommerce.auth.domain.model.gateway;
import com.ecommerce.auth.domain.model.Usuario;

import java.util.Optional;


public interface UsuarioGateway {

    Usuario guardarUsuario(Usuario usuario);
    void eliminarUsuario (Long id);
    Usuario buscarUsuarioPorId (Long id);
    Usuario actualizarUsuario (Usuario usuario);
    Usuario buscarUsuarioPorEmail (String email);

    //Optional<Usuario> logear (String email);


}
