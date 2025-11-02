package com.hys.tienda.controller;

import com.hys.tienda.dto.ActualizarCompraDTO;
import com.hys.tienda.dto.RegistroCompraDTO;
import com.hys.tienda.entity.CompraDiaria;
import com.hys.tienda.service.CompraDiariaService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RestController
@RequestMapping("/compras-diarias")
public class CompraDiariaController {

    @Autowired
    private CompraDiariaService compraDiariaService;

    /**
     * Registra una Compra del día (o suma si ya existe) POST /compras-diarias
     * Body: { "fecha": "2025-11-01", "montoConFactura": 800.00,
     * "montoSinFactura": 200.00 }
     */
    @PostMapping
    public ResponseEntity<CompraDiaria> registrarCompra(@RequestBody RegistroCompraDTO dto) {
        CompraDiaria compra = compraDiariaService.registrarCompraDiaria(
                dto.getFecha(),
                dto.getMontoConFactura(),
                dto.getMontoSinFactura()
        );
        return ResponseEntity.ok(compra);
    }

    @GetMapping("/total-acumulado-con")
    public ResponseEntity<BigDecimal> obtenerTotalConFactura() {
        BigDecimal total = compraDiariaService.obtenerTotalAcumuladoConFactura();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/total-acumulado-sin")
    public ResponseEntity<BigDecimal> obtenerTotalConFacturaSin() {
        BigDecimal total = compraDiariaService.obtenerTotalAcumuladoSinFactura();
        return ResponseEntity.ok(total);
    }

    /**
     * Obtiene todas las ventas en un rango de fechas GET
     * /ventas-diarias/rango?inicio=2025-10-01&fin=2025-10-31
     */
    @GetMapping("/rango")
    public ResponseEntity<List<CompraDiaria>> obtenerComprasEnRango(
            @RequestParam String inicio,
            @RequestParam String fin) {
        LocalDate fechaInicio = LocalDate.parse(inicio);
        LocalDate fechaFin = LocalDate.parse(fin);
        List<CompraDiaria> compras = compraDiariaService.obtenerCompraEnRango(fechaInicio, fechaFin);
        return ResponseEntity.ok(compras);
    }

    @GetMapping("listar")
    public ResponseEntity<List<CompraDiaria>> listarTodasLasCompras() {

        List<CompraDiaria> compras = compraDiariaService.listarTodasLasCompras();
        return ResponseEntity.ok(compras);
    }

    /**
     * Actualiza el monto de una venta diaria PUT
     * /ventas-diarias/fecha/2025-10-31 Body: { "montoConFactura": 800.00,
     * "montoSinFactura": 200.00 }
     */
    @PutMapping("/actualizar/fecha/{fecha}")
    public ResponseEntity<CompraDiaria> actualizarVentaDiaria(
            @PathVariable String fecha,
            @RequestBody ActualizarCompraDTO dto) {

        LocalDate fechaLocal = LocalDate.parse(fecha);
        CompraDiaria compraActualizada = compraDiariaService.actualizarCompraDiaria(fechaLocal, dto.getMontoConFactura(), dto.getMontoSinFactura());
        return ResponseEntity.ok(compraActualizada);
    }

    /**
     * Elimina una Compra diaria por fecha DELETE
     * /ventas-diarias/fecha/2025-10-31
     */
    @DeleteMapping("/eliminar/fecha/{fecha}")
    public ResponseEntity<Void> eliminarCompraPorFecha(@PathVariable String fecha) {
        try {
            LocalDate fechaLocal = LocalDate.parse(fecha);
            compraDiariaService.eliminarCompraPorFecha(fechaLocal);
            return ResponseEntity.noContent().build(); // 204
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().build(); // 400 si la fecha es inválida
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404 si no existe la compra
        }
    }

    @GetMapping("/sinFactura/fecha/{fecha}")
    public ResponseEntity<?> compraSinFacturaPorFecha(@PathVariable String fecha) {
        LocalDate fechaLocal = LocalDate.parse(fecha);
        CompraDiaria compra = compraDiariaService.obtenerCompraSinFacturaPorFecha(fechaLocal);
        BigDecimal sinFactura = compra.getCompraDelDiaSinFactura();

        return ResponseEntity.ok(sinFactura);
    }

    @GetMapping("/conFactura/fecha/{fecha}")
    public ResponseEntity<?> compraConFacturaPorFecha(@PathVariable String fecha) {
        LocalDate fechaLocal = LocalDate.parse(fecha);
        CompraDiaria compra = compraDiariaService.obtenerCompraConFacturaPorFecha(fechaLocal);
        BigDecimal conFactura = compra.getCompraDelDiaConFactura();

        return ResponseEntity.ok(conFactura);
    }

    /**
     * Obtiene la compra de una fecha específica (sin factura) GET
     * /compras-diarias/sin-factura/fecha/2025-10-31
     */
    @GetMapping("/compra/fecha/{fecha}")
    public ResponseEntity<CompraDiaria> obtenerCompraPorFecha(@PathVariable String fecha) {
        LocalDate fechaLocal = LocalDate.parse(fecha);
        CompraDiaria compra = compraDiariaService.obtenerCompraSinFacturaPorFecha(fechaLocal);
        return ResponseEntity.ok(compra);
    }

    /**
     * Obtiene el total de compras CON FACTURA en un rango de fechas GET
     * /compras-diarias/rango/con-factura?inicio=2025-10-01&fin=2025-10-31
     */
    @GetMapping("/rango/con-factura")
    public ResponseEntity<BigDecimal> obtenerTotalConFacturaEnRango(
            @RequestParam String inicio,
            @RequestParam String fin) {

        try {
            LocalDate fechaInicio = LocalDate.parse(inicio);
            LocalDate fechaFin = LocalDate.parse(fin);
            BigDecimal total = compraDiariaService.obtenerTotalCompraConFacturaEnRango(fechaInicio, fechaFin);
            return ResponseEntity.ok(total);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/rango/sin-factura")
    public ResponseEntity<BigDecimal> obtenerTotalSinFacturaEnRango(
            @RequestParam String inicio,
            @RequestParam String fin) {
        try {
            LocalDate fechaInicio = LocalDate.parse(inicio);
            LocalDate fechaFin = LocalDate.parse(fin);
            BigDecimal total = compraDiariaService.obtenerTotalCompraSinFacturaEnRango(fechaInicio, fechaFin);
            return ResponseEntity.ok(total);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

}
