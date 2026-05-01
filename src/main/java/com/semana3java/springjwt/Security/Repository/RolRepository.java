package com.semana3java.springjwt.Security.Repository;

import com.semana3java.springjwt.Entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol,Long> {

    Rol findByNombre(String nombre);

    boolean existsByNombre(String nombre);

}
