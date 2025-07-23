package com.tuempresa.gestioninventario.api.repository;

import com.tuempresa.gestioninventario.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {



}
