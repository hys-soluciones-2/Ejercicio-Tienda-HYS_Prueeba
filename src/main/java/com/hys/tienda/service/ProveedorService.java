/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hys.tienda.service;

import com.hys.tienda.entity.Estado;
import com.hys.tienda.entity.Proveedor;
import java.util.List;

/**
 *
 * @author Hugo
 */
public interface ProveedorService {
    
    public List<Proveedor> listarProveedores();

    public Proveedor gusrdarProveedor(Proveedor proveedor);

    public Proveedor encontrarProveedorPorId(Long idProveedor);
    
    public Proveedor encontrarProveedorPorNombre(String nombreProveedor);
    
    public Proveedor actualizarProveedor(Long idProveedor,Proveedor proveedor);
    
    public void eliminarProveedor(Long idProveedor);
    
    public List<Proveedor> listaEstado(String estadoProveedor);
    
    public Proveedor cambiarEstadoProveedor(Long idProveedor, String nuevoEstado);
    
    public List<Proveedor> obtenerProveedorPorEstado(String estadoProveedor);
}
