/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hys.tienda.service;


import com.hys.tienda.dto.CrearPedidoDTO;
import com.hys.tienda.dto.DetallePedidoDTO;
import com.hys.tienda.entity.DetallePedido;
import com.hys.tienda.entity.Estado;
import com.hys.tienda.entity.Pedido;
import com.hys.tienda.entity.Producto;
import com.hys.tienda.entity.Proveedor;
import com.hys.tienda.repository.PedidoRepository;
import com.hys.tienda.repository.ProductosRepository;
import com.hys.tienda.repository.ProveedorRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hugo
 */
@Service
@Transactional
public class PedidoService {

    private static final String ESTADO_DISPONIBLE = "DISPONIBLE";

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private ProductosRepository productoRepository;

    public Pedido crearPedido(CrearPedidoDTO dto) {
        Proveedor proveedor = proveedorRepository.findById(dto.getIdProveedor())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        
//        // 2. Validar que esté disponible
        if (!"DISPONIBLE".equalsIgnoreCase(proveedor.getEstadoProveedor())) {
            throw new RuntimeException("El proveedor no está disponible para realizar pedidos");
        } else {
        }
        
//        if (proveedor.getEstadoProveedor() != Estado.DISPONIBLE) {
//            throw new RuntimeException("El proveedor no está disponible");
//        }

        Pedido pedido = new Pedido();
        pedido.setProveedor(proveedor);
        pedido.setFechaPedido(dto.getFechaPedido());
        pedido.setEstado("pendiente");

        BigDecimal total = BigDecimal.ZERO;

        for (DetallePedidoDTO itemDTO : dto.getDetalles()) {
            // 1. Buscar la entidad Producto
            Producto producto = productoRepository.findById(itemDTO.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            // 2. Crear la ENTIDAD DetallePedido (no el DTO)
            DetallePedido detalle = new DetallePedido();
            detalle.setProducto(producto); // ← aquí usas setProducto()
            detalle.setCantidad(itemDTO.getCantidad());
            detalle.setCostoUnitario(itemDTO.getCostoUnitario());

            // 3. Calcular subtotal
            BigDecimal subtotal = itemDTO.getCostoUnitario()
                    .multiply(BigDecimal.valueOf(itemDTO.getCantidad()));
            detalle.setSubtotal(subtotal); // ← subtotal pertenece a la entidad

            // 4. Asociar al pedido
            pedido.addDetalle(detalle);
            total = total.add(subtotal);
        }

        pedido.setTotalCosto(total);
        return pedidoRepository.save(pedido);
        
    }

    
    //*********************************************************
    
    
      // Método de mapeo (puedes usar MapStruct o hacerlo manual)
    //public Pedido cambiarEstadoPedido(Long idPedido, String nuevoEstado) {
      public Pedido cambiarEstadoPedido(Long idPedido) {
    
        // Implementación básica (mejor usa un mapper)
       
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
      

          if ("recibido".equalsIgnoreCase(pedido.getEstado())) {
              throw new RuntimeException("El pedido ya fue recibido");
          }

          pedido.setEstado("recibido"); // ← valor fijo
          pedido.setFechaRecepcion(LocalDate.now());

          return pedidoRepository.save(pedido);
    }
    
    
    public List<Pedido> listarPedidos (){
        List<Pedido> Pedidos = pedidoRepository.findAll();
        
        return Pedidos;
    }
    
     public List<Pedido> listarPedidosPendientes (){
//        List<Pedido> pedidos = pedidoRepository.findAll();
//        
//        List<Pedido> pedidosPendientes = new ArrayList<>();
//        
//        for(Pedido pedido: pedidos){
//            if ("pendiente".equals(pedido.getEstado()))
//                 pedidosPendientes.add(pedido);
//        }
//        return pedidosPendientes;

        return pedidoRepository.findByEstado("pendiente");
    }
    
    
     public List<Pedido> listarPedidosRecibidos (){

        return pedidoRepository.findByEstado("recibido");
    }
    
    
    
    
    
    
    
    
    
}