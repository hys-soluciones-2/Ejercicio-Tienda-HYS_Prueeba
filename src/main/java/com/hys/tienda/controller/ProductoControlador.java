
package com.hys.tienda.controller;

import com.hys.tienda.entity.Estado;
import com.hys.tienda.entity.Producto;
import com.hys.tienda.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hugo
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/producto")
public class ProductoControlador {
    
    @Autowired
    private ProductoService productoService;
    
    @PostMapping("/agregar")
    public ResponseEntity<?> agregarProducto(@RequestBody Producto producto){
        
        Producto nuevoProducto = productoService.gusrdarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
        
    }
    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos(){
        List<Producto> productos = productoService.listarProductos();
        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombre){
        
        Producto producto = productoService.encontrarProductoPorNombre(nombre);
        if(producto != null){
            return ResponseEntity.ok(producto);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
     @GetMapping("/buscar/id/{idProducto}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long idProducto){
        
        Producto producto = productoService.encontrarProductoPorId(idProducto);
        if(producto != null){
            return ResponseEntity.ok(producto);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/actualizar/{idProducto}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long idProducto, @RequestBody Producto producto){
        try {
             Producto productoActualizado = new Producto();
             
        productoActualizado.setIdProducto(idProducto);
        productoActualizado.setNombreProducto(producto.getNombreProducto());
        productoActualizado.setDescripcion(producto.getDescripcion());
        productoActualizado.setEstadoProducto(producto.getEstadoProducto());
        productoActualizado.setPrecio(producto.getPrecio());
        
        //Guardo el producto actualizado en la BBDD
        Producto productoBBDD = productoService.actualizarProducto(idProducto, productoActualizado);
        return ResponseEntity.ok(productoActualizado);
   
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
       
    }
    
    @DeleteMapping("/{idProducto}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long idProducto){
        
        try {
            productoService.eliminarProducto(idProducto);
            
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    
    }
    
    @PutMapping("/estado/{idProducto}")
    public ResponseEntity<?> cambiarEstadoProducto(@PathVariable Long idProducto, @RequestBody Estado estadoProducto){
        try {
            Producto productoEstadoActualizado = productoService.cambiarEstadoProducto(idProducto, estadoProducto);
            return ResponseEntity.ok(productoEstadoActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
   @GetMapping("/estado/{estadoProducto}")
   public ResponseEntity<List<Producto>> listarProductoPorEstado(@PathVariable Estado estadoProducto){
       
       List<Producto> productos = productoService.obtenerProductoPorEstado(estadoProducto);
       
       return ResponseEntity.ok(productos);
       
   }
    
    
    
    
    
    
    
}
