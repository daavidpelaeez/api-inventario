package com.tuempresa.gestioninventario.api.controller;

import com.tuempresa.gestioninventario.api.model.Producto;
import com.tuempresa.gestioninventario.api.repository.ProductoRepository;
import com.tuempresa.gestioninventario.api.service.ProductoService;
import com.tuempresa.gestioninventario.api.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> obtenerProductos(){
        return productoService.obtenerProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto>  ProductoPorID(@PathVariable Long id){
        try {
            Producto producto = productoService.obtenerProductoPorId(id);
            return ResponseEntity.ok(producto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Producto> crearProductos(@RequestBody Producto producto){
        Producto productoCreado = productoService.crearProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> eliminarProducto(@PathVariable Long id) {
        try{
            Producto productoEliminado = productoService.eliminarProducto(id);
            return ResponseEntity.ok(productoEliminado);
        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

}
