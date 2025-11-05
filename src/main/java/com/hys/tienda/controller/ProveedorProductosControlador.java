
package com.hys.tienda.controller;

import com.hys.tienda.dto.AsociarProductoProveedorDTO;
import com.hys.tienda.entity.Producto;
import com.hys.tienda.entity.Proveedor;
import com.hys.tienda.service.ProductoService;
import com.hys.tienda.service.ProveedorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hugo
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/relaciones")
public class ProveedorProductosControlador {
   @Autowired
    private ProductoService productoService;
    
    /**
     * Asociar un producto con un proveedor
     * POST /relaciones/asociar
     */
    @PostMapping("/asociar")
    public ResponseEntity<?> asociarProductoProveedor(@RequestBody AsociarProductoProveedorDTO asociacion) {
       // Producto producto;
        try {
           Producto producto = productoService.asociarProveedorAProducto(
                asociacion.getIdProducto(), 
                asociacion.getIdProveedor()
            );
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    /**
     * Desasociar un producto de un proveedor
     * DELETE /relaciones/desasociar/{idProducto}/{idProveedor}
     */
    @DeleteMapping("/desasociar/{idProducto}/{idProveedor}")
    public ResponseEntity<?> desasociarProductoProveedor(
            @PathVariable Long idProducto, 
            @PathVariable Long idProveedor) {
        try {
            Producto producto = productoService.desasociarProveedorDeProducto(idProducto, idProveedor);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    /**
     * Obtener todos los proveedores de un producto específico
     * GET /relaciones/producto/{idProducto}/proveedores
     */
    @GetMapping("/producto/{idProducto}/proveedores")
    public ResponseEntity<?> obtenerProveedoresDeProducto(@PathVariable Long idProducto) {
        try {
            List<Proveedor> proveedores = productoService.obtenerProveedoresDeProducto(idProducto);
            return ResponseEntity.ok(proveedores);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    /**
     * Obtener todos los productos de un proveedor específico
     * GET /relaciones/proveedor/{idProveedor}/productos
     */
    @GetMapping("/proveedor/{idProveedor}/productos")
    public ResponseEntity<?> obtenerProductosDeProveedor(@PathVariable Long idProveedor) {
        try {
            List<Producto> productos = productoService.obtenerProductosDeProveedor(idProveedor);
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    /**
     * Obtener información completa de un producto con sus proveedores
     * GET /relaciones/producto/{idProducto}/completo
     */
    @GetMapping("/producto/{idProducto}/completo")
    public ResponseEntity<?> obtenerProductoCompleto(@PathVariable Long idProducto) {
        try {
            Producto producto = productoService.encontrarProductoPorId(idProducto);
            if (producto != null) {
                // Cargar los proveedores asociados
                List<Proveedor> proveedores = productoService.obtenerProveedoresDeProducto(idProducto);
                producto.setProveedores(proveedores);
                return ResponseEntity.ok(producto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

