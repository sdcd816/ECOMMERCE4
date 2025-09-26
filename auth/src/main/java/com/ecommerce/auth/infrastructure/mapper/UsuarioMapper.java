package com.ecommerce.auth.infrastructure.mapper;

import com.ecommerce.auth.domain.model.Usuario;
import com.ecommerce.auth.infrastructure.driver_adapters.jpa_repository.UsuarioData;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toUsuario (UsuarioData usuariodata) {
        return new Usuario(
                usuariodata.getId(),
                usuariodata.getNombre(),
                usuariodata.getMail(),
                usuariodata.getPassword(),
                usuariodata.getRole(),
                usuariodata.getEdad()
        );

    }

    public UsuarioData toData (Usuario usuario) {
        return new UsuarioData(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getMail(),
                usuario.getPassword(),
                usuario.getRole(),
                usuario.getEdad()
        );
    }


}
