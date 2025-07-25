package com.tuempresa.gestioninventario.api.controller;

import com.tuempresa.gestioninventario.api.model.Usuario;
import com.tuempresa.gestioninventario.api.repository.UsuarioRepository;
import com.tuempresa.gestioninventario.api.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> usuarioPorID(@PathVariable Long id){
        try{
            Usuario usuario = usuarioService.usuarioPorID(id);
            return ResponseEntity.ok(usuario);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario){
        Usuario usuarioCreado = usuarioService.crearUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> borrarUsuario(@PathVariable Long id){
        try{
            Usuario usuario = usuarioService.borrarUsuario(id);
            return  ResponseEntity.ok(usuario);
        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }


}
