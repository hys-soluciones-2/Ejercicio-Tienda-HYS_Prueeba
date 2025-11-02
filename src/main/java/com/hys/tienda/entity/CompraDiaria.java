
package com.hys.tienda.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
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
@Table(name = "compra_diaria")
public class CompraDiaria implements Serializable{
    
     private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Long idCompra;

    @Column(name = "fecha_compra", nullable = false, unique = true)
    private LocalDate fechaCompra;

    @Column(name = "compra_del_dia_con_factura",nullable = false, precision = 12, scale = 2)
    private BigDecimal compraDelDiaConFactura;

    @Column(name = "compra_del_dia_sin_factura", nullable = false, precision = 12, scale = 2)
    private BigDecimal compraDelDiaSinFactura;

    // Constructor útil
    public CompraDiaria(LocalDate fechaCompra, BigDecimal compraDelDiaConFactura, BigDecimal compraDelDiaSinFactura) {
        this.fechaCompra = fechaCompra;
        this.compraDelDiaConFactura= compraDelDiaConFactura;
        this.compraDelDiaSinFactura= compraDelDiaSinFactura;
    }
      public CompraDiaria(LocalDate fecha, BigDecimal montoConFactura) {
        this.fechaCompra = fecha;
        this.compraDelDiaConFactura = montoConFactura;
    }
    
}
