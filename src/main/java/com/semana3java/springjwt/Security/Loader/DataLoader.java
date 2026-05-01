package com.semana3java.springjwt.Security.Loader;

import com.semana3java.springjwt.Entity.Rol;
import com.semana3java.springjwt.Security.Repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Autowired
    private RolRepository rolRepository;

    @Bean
    public CommandLineRunner initData(){
        return args -> {
            cargarRolesxDefecto();
        };
    }

    private void cargarRolesxDefecto(){
        if (!rolRepository.existsByNombre("ROLE_USER")) {
            Rol userRol = new Rol();
            userRol.setNombre("ROLE_USER");
            rolRepository.save(userRol);

        }
        if (!rolRepository.existsByNombre("ROLE_ADMIN")) {
            Rol adminRol = new Rol();
            adminRol.setNombre("ROLE_ADMIN");
            rolRepository.save(adminRol);

        }
    }

}
