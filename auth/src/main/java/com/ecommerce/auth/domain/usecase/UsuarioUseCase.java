package com.ecommerce.auth.domain.usecase;
import com.ecommerce.auth.domain.model.Usuario;
import com.ecommerce.auth.domain.model.gateway.EncrypterGateway;
import com.ecommerce.auth.domain.model.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class UsuarioUseCase {

    private final UsuarioGateway usuarioGateway;
    private final EncrypterGateway encrypterGateway;


    public Usuario guardarUsuario(Usuario usuario) {
        if (usuario.getNombre() == null && usuario.getPassword() == null) {
            throw new NullPointerException("Nombre del usuario nulo");

        }

        if (usuario.getEdad() < 18) {
            System.out.print("Ojooo");

        }
        String passwordEncrypt = encrypterGateway.encrypt(usuario.getPassword());
        usuario.setPassword(passwordEncrypt);
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

        public Usuario actualizarUsuario(Usuario usuario) {

            if (usuario.getId() == null) {
                throw new IllegalArgumentException("El id es obligatorio");
            }

            String passwordEncrypt = encrypterGateway.encrypt(usuario.getPassword());
            usuario.setPassword(passwordEncrypt);
            return usuarioGateway.actualizarUsuario(usuario);

        }

        public Usuario buscarUsuarioPorEmail(String email, String password) {
        Usuario usuario = usuarioGateway.buscarUsuarioPorEmail(email);

        if (usuario == null) {
            throw new IllegalArgumentException("El usuario con el email" + email + "no existe");
        }
        if (!encrypterGateway.checkPass(password, usuario.getPassword())) {

            throw new IllegalArgumentException("Contraseña incorrecta");

        }
        return usuario;


        }

    }





