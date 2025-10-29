
package com.hys.tienda.repository;

import com.hys.tienda.entity.Estado;
import com.hys.tienda.entity.Producto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hugo
 */
@Repository
public interface ProductosRepository extends JpaRepository<Producto, Long>{
    
    Optional<Producto> findByNombreProducto(String nombre);
      
      List<Producto> findByEstadoProducto(Estado nuevoEstado);

}
