
package com.hys.tienda.repository;

import com.hys.tienda.entity.Estado;
import com.hys.tienda.entity.Proveedor;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hugo
 */
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long>{
      Optional<Proveedor> findByNombre(String nombre);
      
      List<Proveedor> findByEstadoProveedor(String nuevoEstado);

}
