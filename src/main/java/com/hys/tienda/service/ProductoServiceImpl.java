
package com.hys.tienda.service;

import com.hys.tienda.entity.Estado;
import com.hys.tienda.entity.Producto;
import com.hys.tienda.entity.Proveedor;
import com.hys.tienda.repository.ProductosRepository;
import com.hys.tienda.repository.ProveedorRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hugo
 */
@Service
public class ProductoServiceImpl implements ProductoService{
    
    @Autowired
    private ProductosRepository productoRepository;

     @Autowired
    private ProveedorRepository proveedorRepository;
     
    @Override
    public List<Producto> listarProductos() {
        
        return (List<Producto>) productoRepository.findAll();
    
    }

    @Override
    public Producto gusrdarProducto(Producto producto) {
        
        return productoRepository.save(producto);
    
    }

    @Override
    public Producto encontrarProductoPorId(Long idProducto) {
    
            return productoRepository.findById(idProducto).orElse(null);
    
    }

    @Override
    public Producto encontrarProductoPorNombre(String nombreProducto) {
       
        return productoRepository.findByNombreProducto(nombreProducto).orElse(null);
    
    }

    @Override
    @SneakyThrows
    public Producto actualizarProducto(Long idProducto, Producto producto) {
     
        Producto productoExistente = productoRepository.findById(idProducto).orElseThrow(()-> new Exception("Producto con ID: " + idProducto + " No encontrado" ));
    
        productoExistente.setNombreProducto(producto.getNombreProducto());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setEstadoProducto(producto.getEstadoProducto());
        
        return productoRepository.save(productoExistente);
    }

    @Override
    @SneakyThrows
    public void eliminarProducto(Long idProducto) {
        
    
        Producto productoExistente = productoRepository.findById(idProducto).orElseThrow(()-> new Exception("Producto con ID: " + idProducto + " No encontrado" ));
    
        productoRepository.deleteById(idProducto);
    
    }

   

    @Override
    @SneakyThrows
    public Producto cambiarEstadoProducto(Long idProducto, Estado nuevoEstado) {
        
        
        Producto productoExistente = productoRepository.findById(idProducto).orElseThrow(()-> new Exception("Producto con ID: " + idProducto + " No encontrado" ));
    
        productoExistente.setEstadoProducto(nuevoEstado);
        return productoRepository.save(productoExistente);
    }

    @Override
    public List<Producto> obtenerProductoPorEstado(Estado estadoProducto) {
        
        return productoRepository.findByEstadoProducto(estadoProducto);
    
    }
    // Implementación de métodos para manejar relaciones con Proveedores

    /**
     *
     * @param idProducto
     * @param idProveedor
     * @return
     */
    
    @Override
    @Transactional
    @SneakyThrows
    public Producto asociarProveedorAProducto(Long idProducto, Long idProveedor) {
        // Buscar el producto
        Producto producto = productoRepository.findById(idProducto)
            .orElseThrow(() -> new Exception("Producto con ID: " + idProducto + " no encontrado"));
        
        // Buscar el proveedor
        Proveedor proveedor = proveedorRepository.findById(idProveedor)
            .orElseThrow(() -> new Exception("Proveedor con ID: " + idProveedor + " no encontrado"));
        
        // Asociar el proveedor al producto
        if (!producto.getProveedores().contains(proveedor)) {
            producto.getProveedores().add(proveedor);
            proveedor.getProductos().add(producto);
        }
        
        return productoRepository.save(producto);
    }
    
    @Override
    @Transactional
    @SneakyThrows
    public Producto desasociarProveedorDeProducto(Long idProducto, Long idProveedor) {
        // Buscar el producto
        Producto producto = productoRepository.findById(idProducto)
            .orElseThrow(() -> new Exception("Producto con ID: " + idProducto + " no encontrado"));
        
        // Buscar el proveedor
        Proveedor proveedor = proveedorRepository.findById(idProveedor)
            .orElseThrow(() -> new Exception("Proveedor con ID: " + idProveedor + " no encontrado"));
        
        // Desasociar el proveedor del producto
        producto.getProveedores().remove(proveedor);
        proveedor.getProductos().remove(producto);
        
        return productoRepository.save(producto);
    }
    
    @Override
    @SneakyThrows
    public List<Proveedor> obtenerProveedoresDeProducto(Long idProducto) {
        Producto producto = productoRepository.findById(idProducto)
            .orElseThrow(() -> new Exception("Producto con ID: " + idProducto + " no encontrado"));
        
        return producto.getProveedores();
    }
    
    @Override
    @SneakyThrows
    public List<Producto> obtenerProductosDeProveedor(Long idProveedor) {
        Proveedor proveedor = proveedorRepository.findById(idProveedor)
            .orElseThrow(() -> new Exception("Proveedor con ID: " + idProveedor + " no encontrado"));
        
        return proveedor.getProductos();
    }
    
    
    
}
