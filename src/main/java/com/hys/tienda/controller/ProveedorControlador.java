
package com.hys.tienda.controller;

import com.hys.tienda.entity.Estado;
import com.hys.tienda.entity.Proveedor;
import com.hys.tienda.service.ProveedorService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/proveedor")
public class ProveedorControlador {
    
    @Autowired
    private ProveedorService proveedorService;
    
    @PostMapping("/agregar")
    public ResponseEntity<?> agregarProveedor(@RequestBody Proveedor proveedor){
        
     Proveedor nuevoProveedor =  proveedorService.gusrdarProveedor(proveedor);
       return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProveedor);
    }
      @GetMapping
    public ResponseEntity<List<Proveedor>> listarProveedores(){
        List<Proveedor> proveedores = proveedorService.listarProveedores();
        
       // proveedorService.gusrdarProveedor(proveedor);
        return ResponseEntity.ok(proveedores);
    }
    
    @GetMapping("buscar/nombre/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombre){
        Proveedor proveedor = proveedorService.encontrarProveedorPorNombre(nombre);
 
        if (proveedor != null) {
            return ResponseEntity.ok(proveedor);
        } else {
            return ResponseEntity.notFound().build();
        }
    
    }
    
       @GetMapping("buscar/id/{idProveedor}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long idProveedor){
        Proveedor proveedor = proveedorService.encontrarProveedorPorId(idProveedor);
 
        if (proveedor != null) {
            return ResponseEntity.ok(proveedor);
        } else {
            return ResponseEntity.notFound().build();
        }
    
    }
    
    @PutMapping("/actualizar/{idProveedor}")
    public ResponseEntity<?> actualizarProveedor(@PathVariable Long idProveedor, @RequestBody Proveedor proveedor){
        try {
             
        Proveedor proveedorActualizado = new Proveedor();
        
         proveedorActualizado.setIdProveedor(idProveedor);
        proveedorActualizado.setNombre(proveedor.getNombre());
        proveedorActualizado.setContacto(proveedor.getContacto());
        proveedorActualizado.setTelefono(proveedor.getTelefono());
        proveedorActualizado.setEstadoProveedor(proveedor.getEstadoProveedor());
        
        Proveedor proveedorBBDD = proveedorService.actualizarProveedor(idProveedor, proveedorActualizado);
        
        return ResponseEntity.ok(proveedorActualizado);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        
        
    }
    
    
    @DeleteMapping("/{idProveedor}")
    public ResponseEntity<?> eliminarProveedor(@PathVariable Long idProveedor){
        
        try {
            proveedorService.eliminarProveedor(idProveedor);
            
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    
    }
    @PutMapping("/estado/{idProveedor}")
    public ResponseEntity<?> cambiarEstadoProveedor(@PathVariable("idProveedor") Long idProveedor, @RequestBody String estadoProveedor){
        
        try {
           Proveedor proveedorEstadoActualizado = proveedorService.cambiarEstadoProveedor(idProveedor, estadoProveedor);
           
           return ResponseEntity.ok(proveedorEstadoActualizado);
           
        } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
       
        }
    }
    
    
    
    @GetMapping("/estado/{estadoProveedor}")
    public ResponseEntity<List<Proveedor>> listarProveedorPorEstado(@PathVariable String estadoProveedor){
        
        
        List<Proveedor> proveedores = proveedorService.obtenerProveedorPorEstado(estadoProveedor);
        
        return ResponseEntity.ok(proveedores);
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
