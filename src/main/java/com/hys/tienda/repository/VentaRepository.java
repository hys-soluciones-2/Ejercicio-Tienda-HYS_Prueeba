/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hys.tienda.repository;

import com.hys.tienda.entity.VentaDiaria;
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
public interface VentaRepository extends JpaRepository<VentaDiaria, Long>{
   
   Optional<VentaDiaria> findByFechaVenta(LocalDate fecha);

    // Total acumulado de TODAS las ventas
    @Query("SELECT COALESCE(SUM(v.ventaDelDia), 0) FROM VentaDiaria v")
    BigDecimal sumTotalAcumulado();

    // / Buscar ventas en un rango de fechas
    List<VentaDiaria> findByFechaVentaBetween(LocalDate fechaInicio, LocalDate fechaFin);


    // Total en un rango de fechas (útil para mensual/anual)
    @Query("SELECT COALESCE(SUM(v.ventaDelDia), 0) FROM VentaDiaria v WHERE v.fechaVenta BETWEEN :inicio AND :fin")
    BigDecimal sumTotalEnRango(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);
}