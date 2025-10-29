
package com.hys.tienda.service;

import com.hys.tienda.entity.Estado;
import com.hys.tienda.entity.Producto;
import com.hys.tienda.entity.Proveedor;
import java.util.List;

/**
 *
 * @author Hugo
 */
public interface ProductoService {
    
    public List<Producto> listarProductos();
    
     public Producto gusrdarProducto(Producto producto);

    public Producto encontrarProductoPorId(Long idProducto);
    
    public Producto encontrarProductoPorNombre(String nombreProducto);
    
    public Producto actualizarProducto(Long idProducto,Producto producto);
    
    public void eliminarProducto(Long idProducto);
    
   
    public Producto cambiarEstadoProducto(Long idProducto, Estado nuevoEstado);
    
    public List<Producto> obtenerProductoPorEstado(Estado estadoProducto);

    // Métodos para manejar relaciones con Proveedores
    public Producto asociarProveedorAProducto(Long idProducto, Long idProveedor);
    
    public Producto desasociarProveedorDeProducto(Long idProducto, Long idProveedor);
    
    public List<Proveedor> obtenerProveedoresDeProducto(Long idProducto);
    
    public List<Producto> obtenerProductosDeProveedor(Long idProveedor);

}
