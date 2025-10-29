
package com.hys.tienda.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Hugo
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="pedido")
public class Pedido implements Serializable{
    
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_pedido")
    private Long idPedido;
   
  

   @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedor proveedor;
    
    private LocalDate fechaPedido;
    private LocalDate fechaRecepcion;
    @Column(name = "estado")
    private String estado; // "pendiente", "recibido"
    private BigDecimal totalCosto;

    // ✅ Relación con los detalles
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles = new ArrayList<>();

    // métodos utilitarios
    public void addDetalle(DetallePedido detalle) {
        detalles.add(detalle);
        detalle.setPedido(this);
    }

      // getter
    public String getEstado() {
        return estado;
    }
    
    
    
    
    
  /*
    url de postman:
    //https://web.postman.co/workspace/My-Workspace~89f8348b-c2b6-4f72-8dea-38e6424640a1/overview
    */  
    
    
}
