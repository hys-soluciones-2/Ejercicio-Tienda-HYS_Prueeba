/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hys.tienda.repository;

import com.hys.tienda.entity.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hugo
 */
@Repository
public interface PedidoRepository  extends JpaRepository<Pedido, Long>{
    
    List<Pedido> findByEstado(String estado);
}
