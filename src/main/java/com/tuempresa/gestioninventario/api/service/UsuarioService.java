package com.tuempresa.gestioninventario.api.service;

import com.tuempresa.gestioninventario.api.model.Producto;
import com.tuempresa.gestioninventario.api.model.Usuario;
import com.tuempresa.gestioninventario.api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> obtenerUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario usuarioPorID(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id: " + id));
    }

    public Usuario crearUsuario(Usuario usuario){
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public Usuario borrarUsuario(Long id){
       Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

       if(usuarioExistente.isPresent()){
           usuarioRepository.deleteById(id);
           return usuarioExistente.get();
       }else{
           throw new EntityNotFoundException("Usuario no encontrado con id: " + id);
       }
    }




}
