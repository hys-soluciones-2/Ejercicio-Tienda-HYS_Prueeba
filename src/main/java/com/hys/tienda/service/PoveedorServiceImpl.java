
package com.hys.tienda.service;

import com.hys.tienda.entity.Estado;
import com.hys.tienda.entity.Proveedor;
import com.hys.tienda.repository.ProveedorRepository;
import java.util.List;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hugo
 */
@Service
public class PoveedorServiceImpl implements ProveedorService{

    @Autowired
    private ProveedorRepository proveedorRepository;
    
    @Override
    public List<Proveedor> listarProveedores() {
       
        return (List<Proveedor>) proveedorRepository.findAll();
    
    }

    @Override
    public Proveedor gusrdarProveedor(Proveedor proveedor) {
    
      return  proveedorRepository.save(proveedor);
    
    }

    @Override
    public Proveedor encontrarProveedorPorId(Long idProveedor) {
       
        return proveedorRepository.findById(idProveedor).orElse(null);
    
    }

    
     @Override
    public Proveedor encontrarProveedorPorNombre(String nombreProveedor) {
//        List<Proveedor> listaProveedores = proveedorRepository.findAll();
//        for (Proveedor proveedor : listaProveedores) {
//            if (nombreProveedor.equalsIgnoreCase(proveedor.getNombre())) {
//                return proveedor;
//            }
//        }
//        
//        return null;

        return proveedorRepository.findByNombre(nombreProveedor).orElse(null);
    }
    
    
    @Override
    @SneakyThrows
    public void eliminarProveedor(Long idProveedor) {
    
         Proveedor proveedorExistente =  proveedorRepository.findById(idProveedor).orElseThrow(()-> new Exception("Proveedor con ID: " + idProveedor + " No encontrado" ));
    
        proveedorRepository.deleteById(idProveedor);
    
    }

    @Override
    @SneakyThrows
    public Proveedor actualizarProveedor(Long idProveedor, Proveedor proveedor) {
   
        Proveedor proveedorExistente = proveedorRepository.findById(idProveedor).orElseThrow(()-> new Exception("Proveedor con ID: " + idProveedor + " No encontrado" ));
    
        proveedorExistente.setNombre(proveedor.getNombre());
        proveedorExistente.setContacto(proveedor.getContacto());
        proveedorExistente.setTelefono(proveedor.getTelefono());
        
        return proveedorRepository.save(proveedorExistente);
        
        
    }

    @Override
    public List<Proveedor> listaEstado(String estadoProveedor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    @SneakyThrows
    public Proveedor cambiarEstadoProveedor(Long idProveedor, String nuevoEstado) {
        Proveedor proveedorExistente = proveedorRepository.findById(idProveedor).orElseThrow(()-> new Exception("Proveedor con ID: " + idProveedor + " No encontrado" ));
        
        proveedorExistente.setEstadoProveedor(nuevoEstado);
            return proveedorRepository.save(proveedorExistente);
    }

    @Override
    public List<Proveedor> obtenerProveedorPorEstado(String estadoProveedor) {
       return proveedorRepository.findByEstadoProveedor(estadoProveedor);
    
    }

    
}
