/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hys.tienda.service;

import com.hys.tienda.entity.VentaDiaria;
import com.hys.tienda.repository.VentaRepository;
//
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hugo
 */
@Service
@Transactional
public class VentaDiariaService {
    
     @Autowired
    private VentaRepository ventaDiariaRepository;

     /**
     * Registra o actualiza la venta del día
     */
    public VentaDiaria registrarVentaDiaria(LocalDate fecha, BigDecimal monto) {
        // Buscar si ya existe
        Optional<VentaDiaria> existente = ventaDiariaRepository.findByFechaVenta(fecha);

        //**************************************************
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("El monto debe ser mayor que cero");
        }
        
        //**************************************************
        VentaDiaria venta;
        if (existente.isPresent()) {
            // Sumar al día existente
            venta = existente.get();
            venta.setVentaDelDia(venta.getVentaDelDia().add(monto));
        } else {
            // Nuevo registro
            venta = new VentaDiaria(fecha, monto);
        }

        return ventaDiariaRepository.save(venta);
    }

    /**
     * Obtiene el total acumulado histórico
     */
    @Transactional(readOnly = true)
    public BigDecimal obtenerTotalAcumulado() {
        return ventaDiariaRepository.sumTotalAcumulado();
    }

    /**
     * Obtiene el total del mes actual
     */
    @Transactional(readOnly = true)
    public BigDecimal obtenerTotalMesActual() {
        LocalDate hoy = LocalDate.now();
        LocalDate inicioMes = hoy.withDayOfMonth(1);
        LocalDate finMes = hoy.withDayOfMonth(hoy.lengthOfMonth());
        return ventaDiariaRepository.sumTotalEnRango(inicioMes, finMes);
    }
    // En VentaDiariaService.java

    @Transactional(readOnly = true)
    public List<VentaDiaria> listarTodasLasVentas() {
        return ventaDiariaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public VentaDiaria obtenerVentaPorFecha(LocalDate fecha) {
        return ventaDiariaRepository.findByFechaVenta(fecha).orElse(null);
    }
    
    /**
     * Elimina una venta diaria por fecha
     */
    @Transactional
    public void eliminarVentaPorFecha(LocalDate fecha) {
        VentaDiaria venta = ventaDiariaRepository.findByFechaVenta(fecha)
                .orElseThrow(() -> new RuntimeException("No se encontró venta para la fecha: " + fecha));
        ventaDiariaRepository.delete(venta);
    }

    /**
     * Obtiene todas las ventas en un rango de fechas
     */
    @Transactional(readOnly = true)
    public List<VentaDiaria> obtenerVentasEnRango(LocalDate fechaInicio, LocalDate fechaFin) {
        return ventaDiariaRepository.findByFechaVentaBetween(fechaInicio, fechaFin);
    }

    /**
     * Obtiene el total vendido en un rango de fechas
     */
    @Transactional(readOnly = true)
    public BigDecimal obtenerTotalEnRango(LocalDate fechaInicio, LocalDate fechaFin) {
        return ventaDiariaRepository.sumTotalEnRango(fechaInicio, fechaFin);
    }

    /**
     * Actualiza el monto de una venta diaria existente
     */
    @Transactional
    public VentaDiaria actualizarVentaDiaria(LocalDate fecha, BigDecimal nuevoMonto) {
        VentaDiaria venta = ventaDiariaRepository.findByFechaVenta(fecha)
                .orElseThrow(() -> new RuntimeException("No se encontró venta para la fecha: " + fecha));

        if (nuevoMonto == null || nuevoMonto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("El monto debe ser mayor que cero");
        }
        venta.setVentaDelDia(nuevoMonto);
        return ventaDiariaRepository.save(venta);
    }













}
