package com.ecommerce.auth.infrastructure.driver_adapters.jpa_repository;

import com.ecommerce.auth.domain.model.Usuario;
import com.ecommerce.auth.domain.model.gateway.UsuarioGateway;
import com.ecommerce.auth.infrastructure.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
        repository.deleteById(id);
    }

    @Override
    public Usuario buscarUsuarioPorId(Long id) {
        //return usuarioMapper.toUsuario(repository.findById(id).get());

        return repository.findById(id)
                .map(usuarioData -> usuarioMapper.toUsuario(usuarioData))
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        UsuarioData  usuarioDataActualizar = usuarioMapper.toData(usuario);

        if(!repository.existsById(usuarioDataActualizar.getId())){

            throw new RuntimeException("Usuario con id" + usuarioDataActualizar.getId() + "no existe");

        }
        return usuarioMapper.toUsuario(repository.save(usuarioDataActualizar));
    }

    @Override
    public Usuario buscarUsuarioPorEmail(String email) {

        UsuarioData usuarioData = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuarioMapper.toUsuario(usuarioData);




    }

    //@Override
    //public Optional<Usuario> logear(String email) {
    //return repository.findByEmail(email)
    //.map(usuarioMapper::toUsuario);
    //}

}


