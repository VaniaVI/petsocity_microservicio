package com.petsocity.petsocity.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.petsocity.petsocity.model.Usuario;
import com.petsocity.petsocity.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorIdUsuario(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario crearUsuario(Usuario usuario) {
        if (usuario.getId() != null) {
            throw new RuntimeException("El ID debe ser nulo");
        }
        if (usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new IllegalArgumentException("El correo ingresado ya esta registrado");
        }
        if (!usuario.getNombre().matches("^[A-Za-zÁÉÍÓÚÑáéíóúñ ]+$")) {
        throw new IllegalArgumentException("El primer nombre solo debe contener letras");
        }
        if (!usuario.getApellido().matches("^[A-Za-zÁÉÍÓÚÑáéíóúñ ]+$")) {
        throw new IllegalArgumentException("El primer apellido solo debe contener letras");
        }
        if (!usuario.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
        throw new IllegalArgumentException("El correo debe tener un formato válido");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Long id, Usuario datosActualizados) {
        return usuarioRepository.findById(id).map(usuario -> {
            if (datosActualizados.getNombre() != null) {
                usuario.setNombre(datosActualizados.getNombre());
            }
            if (datosActualizados.getApellido() != null) {
                usuario.setApellido(datosActualizados.getApellido());
            }
            if (datosActualizados.getEmail() != null) {
                usuario.setEmail(datosActualizados.getEmail());
            }
            if (datosActualizados.getContrasenia() != null){
                usuario.setContrasenia(datosActualizados.getContrasenia());
            }
            if (datosActualizados.getTelefono() != null) {
                usuario.setTelefono(datosActualizados.getTelefono());
            }
            if (datosActualizados.getDireccion() != null) {
                usuario.setDireccion(datosActualizados.getDireccion());
            }
            if (datosActualizados.getRegion() != null) {
                usuario.setRegion(datosActualizados.getRegion());
            }
            if (datosActualizados.getComuna() != null) {
                usuario.setComuna(datosActualizados.getComuna());
            }

            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public boolean eliminarUsuario(Long id) {
    Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
