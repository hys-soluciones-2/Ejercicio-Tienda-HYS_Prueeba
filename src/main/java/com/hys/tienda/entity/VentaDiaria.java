
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
@Table(name = "venta_diaria")
public class VentaDiaria implements Serializable{
    
   private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long idVenta;

    @Column(name = "fecha_venta", nullable = false, unique = true)
    private LocalDate fechaVenta;

    @Column(name = "venta_del_dia", nullable = false, precision = 12, scale = 2)
    private BigDecimal ventaDelDia;

    // Constructor útil
    public VentaDiaria(LocalDate fechaVenta, BigDecimal ventaDelDia) {
        this.fechaVenta = fechaVenta;
        this.ventaDelDia = ventaDelDia;
    }
}
