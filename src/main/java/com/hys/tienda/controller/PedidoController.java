
package com.hys.tienda.controller;

import com.hys.tienda.dto.CrearPedidoDTO;
import com.hys.tienda.entity.Pedido;
import com.hys.tienda.service.PedidoService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hugo
 */
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody CrearPedidoDTO dto) {
        Pedido pedido = pedidoService.crearPedido(dto);
        return ResponseEntity.ok(pedido);
    }
    
    //*******************************************************
    
     @PatchMapping("/{id}/recibir")
    public ResponseEntity<?> recibirPedido(@PathVariable Long id) {
       
        Pedido pedido = pedidoService.cambiarEstadoPedido(id);
      

        return ResponseEntity.ok(pedido);
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<Pedido>> listarTodosLosPedidos(){
        
        List<Pedido> pedidos = pedidoService.listarPedidos();
        
       return ResponseEntity.ok(pedidos);
    }
    
     @GetMapping("/pendientes")
    public ResponseEntity<List<Pedido>> listarLosPedidosPendientes(){
        
        List<Pedido> pedidos = pedidoService.listarPedidosPendientes();
        
       return ResponseEntity.ok(pedidos);
    }
    
    
       
     @GetMapping("/recibido")
    public ResponseEntity<List<Pedido>> listarLosPedidosRecibidos(){
        
        List<Pedido> pedidos = pedidoService.listarPedidosRecibidos();
        
       return ResponseEntity.ok(pedidos);
    }
    
    
    
    
}

