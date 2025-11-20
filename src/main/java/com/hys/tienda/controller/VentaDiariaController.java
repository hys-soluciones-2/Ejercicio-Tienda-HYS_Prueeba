/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hys.tienda.controller;

import com.hys.tienda.dto.ActualizarVentaDTO;
import com.hys.tienda.dto.RegistroVentaDTO;
import com.hys.tienda.entity.VentaDiaria;
import com.hys.tienda.service.VentaDiariaService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hugo
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/ventas-diarias")
public class VentaDiariaController {
    
    
     @Autowired
    private VentaDiariaService ventaDiariaService;

    /**
     * Registra una venta del día (o suma si ya existe)
     * POST /ventas-diarias
     * Body: { "fecha": "2025-10-30", "monto": 1500.00 }
     */
    @PostMapping
    public ResponseEntity<VentaDiaria> registrarVentaDiaria(@RequestBody RegistroVentaDTO dto) {
        VentaDiaria venta = ventaDiariaService.registrarVentaDiaria(dto.getFecha(), dto.getMonto());
        return ResponseEntity.ok(venta);
    }

    /**
     * Obtiene el total acumulado de TODAS las ventas registradas.
     * 
     * Ejemplo de respuesta: 45000.00
     */
    @GetMapping("/total-acumulado")
    public ResponseEntity<BigDecimal> obtenerTotalAcumulado() {
        BigDecimal total = ventaDiariaService.obtenerTotalAcumulado();
        return ResponseEntity.ok(total);
    }

    /**
     * Obtiene el total vendido en el mes actual.
     * 
     * Ejemplo de respuesta: 12500.00
     */
    @GetMapping("/total-mes-actual")
    public ResponseEntity<BigDecimal> obtenerTotalMesActual() {
        BigDecimal total = ventaDiariaService.obtenerTotalMesActual();
        return ResponseEntity.ok(total);
    }

    /**
     * Lista todas las ventas diarias registradas (útil para reportes).
     */
    @GetMapping
    public ResponseEntity<List<VentaDiaria>> listarTodasLasVentas() {
        List<VentaDiaria> ventas = ventaDiariaService.listarTodasLasVentas();
        // Ordenar por fechaCompra descendente
        ventas.sort((c1, c2) -> c2.getFechaVenta().compareTo(c1.getFechaVenta()));

        return ResponseEntity.ok(ventas);
    }

    /**
     * Obtiene la venta de un día específico.
     */
    @GetMapping("/dia/{fecha}")
    public ResponseEntity<VentaDiaria> obtenerVentaPorFecha(@PathVariable String fecha) {
        LocalDate fechaLocal = LocalDate.parse(fecha);
        VentaDiaria venta = ventaDiariaService.obtenerVentaPorFecha(fechaLocal);
        if (venta != null) {
            return ResponseEntity.ok(venta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
 * Elimina una venta diaria por fecha DELETE
     * /ventas-diarias/fecha/2025-10-31
     */
    @DeleteMapping("/fecha/{fecha}")
    public ResponseEntity<Void> eliminarVentaPorFecha(@PathVariable String fecha) {
        LocalDate fechaLocal = LocalDate.parse(fecha);
        ventaDiariaService.eliminarVentaPorFecha(fechaLocal);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    /**
     * Obtiene todas las ventas en un rango de fechas GET
     * /ventas-diarias/rango?inicio=2025-10-01&fin=2025-10-31
     */
    @GetMapping("/rango")
    public ResponseEntity<List<VentaDiaria>> obtenerVentasEnRango(
            @RequestParam String inicio,
            @RequestParam String fin) {
        LocalDate fechaInicio = LocalDate.parse(inicio);
        LocalDate fechaFin = LocalDate.parse(fin);
        List<VentaDiaria> ventas = ventaDiariaService.obtenerVentasEnRango(fechaInicio, fechaFin);
        return ResponseEntity.ok(ventas);
    }

    /**
     * Obtiene el TOTAL vendido en un rango de fechas GET
     * /ventas-diarias/rango/total?inicio=2025-10-01&fin=2025-10-31
     */
    @GetMapping("/rango/total")
    public ResponseEntity<BigDecimal> obtenerTotalEnRango(
            @RequestParam String inicio,
            @RequestParam String fin) {
        LocalDate fechaInicio = LocalDate.parse(inicio);
        LocalDate fechaFin = LocalDate.parse(fin);
        BigDecimal total = ventaDiariaService.obtenerTotalEnRango(fechaInicio, fechaFin);
        return ResponseEntity.ok(total);
    }
    
    /**
     * Actualiza el monto de una venta diaria PUT
     * /ventas-diarias/fecha/2025-10-31 Body: { "monto": 2800.00 }
     */
    @PutMapping("/fecha/{fecha}")
    public ResponseEntity<VentaDiaria> actualizarVentaDiaria(
            @PathVariable String fecha,
            @RequestBody ActualizarVentaDTO dto) {

        LocalDate fechaLocal = LocalDate.parse(fecha);
        VentaDiaria ventaActualizada = ventaDiariaService.actualizarVentaDiaria(fechaLocal, dto.getMonto());
        return ResponseEntity.ok(ventaActualizada);
    }

    
    
    
    
    
    
}
