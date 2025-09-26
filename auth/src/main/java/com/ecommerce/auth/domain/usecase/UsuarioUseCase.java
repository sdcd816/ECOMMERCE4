package com.ecommerce.auth.domain.usecase;
import com.ecommerce.auth.domain.model.Usuario;
import com.ecommerce.auth.domain.model.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioUseCase {

    private final UsuarioGateway usuarioGateway;


    public Usuario guardarUsuario(Usuario usuario) {
        if (usuario.getId() == null) {
            throw new NullPointerException("Id del usuario nulo");

        }

        if (usuario.getId() < 18) {
            System.out.print("Ojooo");

        }

        return usuarioGateway.guardarUsuario(usuario);
    }

    public Usuario eliminarUsuario(Long id) {
        try {

            usuarioGateway.eliminarUsuario(id);

        } catch (Exception error) {

            System.out.println(error.getMessage());

        }
        return null;
    }


        public Usuario buscarUsuarioPorId(Long id){

            try {
                return usuarioGateway.buscarUsuarioPorId(id);

            } catch (Exception error) {

                System.out.println(error.getMessage());
                return new Usuario();

            }

        }

    }

