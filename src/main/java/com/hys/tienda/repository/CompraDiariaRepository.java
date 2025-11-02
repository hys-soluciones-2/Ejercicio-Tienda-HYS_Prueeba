
package com.hys.tienda.repository;

import com.hys.tienda.entity.CompraDiaria;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;




/**
 *
 * @author Hugo
 */
@Repository
public interface CompraDiariaRepository extends JpaRepository<CompraDiaria, Long>{
    
    Optional<CompraDiaria> findByFechaCompra(LocalDate fecha);
   
 // Total acumulado de TODAS las compras con factura
    @Query("SELECT COALESCE(SUM(v.compraDelDiaConFactura), 0) FROM CompraDiaria v")
    BigDecimal sumTotalAcumuladoConFactura();

     // Total acumulado de TODAS las compras con factura
    @Query("SELECT COALESCE(SUM(v.compraDelDiaSinFactura), 0) FROM CompraDiaria v")
    BigDecimal sumTotalAcumuladoSinFactura();

    // / Buscar ventas en un rango de fechas
    List<CompraDiaria> findByFechaCompraBetween(LocalDate fechaInicio, LocalDate fechaFin);


    // Total en un rango de fechas (útil para mensual/anual)
    @Query("SELECT COALESCE(SUM(v.compraDelDiaConFactura), 0) FROM CompraDiaria v WHERE v.fechaCompra BETWEEN :inicio AND :fin")
    BigDecimal sumTotalEnRangoConFactura(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);


     // Total en un rango de fechas (útil para mensual/anual)
    @Query("SELECT COALESCE(SUM(v.compraDelDiaSinFactura), 0) FROM CompraDiaria v WHERE v.fechaCompra BETWEEN :inicio AND :fin")
    BigDecimal sumTotalEnRangoSinFactura(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);



}
