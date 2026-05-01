package com.semana3java.springjwt.Security.Services;

import com.semana3java.springjwt.Entity.Rol;
import com.semana3java.springjwt.Entity.Usuario;
import com.semana3java.springjwt.Security.Repository.RolRepository;
import com.semana3java.springjwt.Security.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario buscarPorNombre(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        usuario.setUsername(usuario.getUsername());
        usuario.setRoles(usuario.getRoles());
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        for (Rol rol : usuario.getRoles()) {
            rol.getUsuarios().add(usuarioGuardado);
        }
        return usuarioGuardado;
    }
}

