package com.ecommerce.auth.domain.model.gateway;
import com.ecommerce.auth.domain.model.Usuario;


public interface UsuarioGateway {

    Usuario guardarUsuario(Usuario usuario);
    void eliminarUsuario (Long id);
    Usuario buscarUsuarioPorId (Long id);
}
