package com.ecommerce.auth.infrastructure.driver_adapters.jpa_repository;

import com.ecommerce.auth.domain.model.Usuario;
import com.ecommerce.auth.domain.model.gateway.UsuarioGateway;
import com.ecommerce.auth.infrastructure.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UsuarioGatewayImpl implements UsuarioGateway {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioDataJpaRepository repository;

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        UsuarioData usuarioData = usuarioMapper.toData(usuario);
        return usuarioMapper.toUsuario(repository.save(usuarioData));
    }

    @Override
    public void eliminarUsuario(Long id) {

    }

    @Override
    public Usuario buscarUsuarioPorId(Long id) {
        return null;
    }
}
