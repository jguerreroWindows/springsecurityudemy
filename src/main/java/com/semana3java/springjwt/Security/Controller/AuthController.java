package com.semana3java.springjwt.Security.Controller;

import com.semana3java.springjwt.Entity.Rol;
import com.semana3java.springjwt.Entity.Usuario;
import com.semana3java.springjwt.Enums.RolEnum;
import com.semana3java.springjwt.Security.Dto.AuthRequest;
import com.semana3java.springjwt.Security.Dto.AuthResponse;
import com.semana3java.springjwt.Security.Dto.RegistroRequest;
import com.semana3java.springjwt.Security.Repository.RolRepository;
import com.semana3java.springjwt.Security.Services.JwtUtil;
import com.semana3java.springjwt.Security.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<?> iniciarsesion(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );

            UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authRequest.getUsername());

            String jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthResponse(jwt));

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("USUARIO O CONTRASEÑA INCORRECTOS");
        }
    }


    @PostMapping("/registro")
    public ResponseEntity<?> registroUsuario(@RequestBody RegistroRequest registroRequest) {
        if (usuarioService.buscarPorNombre(registroRequest.getUsername()) != null) {
            return ResponseEntity.badRequest().body("El usuario ya existe");
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(registroRequest.getUsername());
        usuario.setPassword(passwordEncoder.encode(registroRequest.getPassword()));
        Set<Rol> roles = new HashSet<>();
        if (registroRequest.getRoles() != null) {
            for (RolEnum rolEnum : registroRequest.getRoles()) {
                Rol rolObj = rolRepository.findByNombre(rolEnum.name());
                roles.add(rolObj);
            }
        }
        usuario.setRoles(roles);
        if (roles.isEmpty()) {
            Rol userRole = rolRepository.findByNombre(RolEnum.ROLE_USER.getRol());
            roles.add(userRole);
            usuario.setRoles(roles);
        }
        usuarioService.guardarUsuario(usuario);
        return ResponseEntity.ok().body("{\"message\":\"Usuario registrado\"}");
    }
}
