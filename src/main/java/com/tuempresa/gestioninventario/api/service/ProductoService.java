package com.tuempresa.gestioninventario.api.service;

import com.tuempresa.gestioninventario.api.model.Producto;
import com.tuempresa.gestioninventario.api.model.Usuario;
import com.tuempresa.gestioninventario.api.repository.ProductoRepository;
import com.tuempresa.gestioninventario.api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> obtenerProductos(){
        return productoRepository.findAll();
    }

    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con id: " + id));
    }


    public Producto crearProducto(Producto producto){
        return productoRepository.save(producto);
    }

    public Producto eliminarProducto(Long id){
    Optional<Producto> productoExistente = productoRepository.findById(id);

        if(productoExistente.isPresent()){
            productoRepository.deleteById(id);
            return productoExistente.get();
        }else{
            throw new EntityNotFoundException("Producto no encontrado con id: " + id);
        }
    }
}
