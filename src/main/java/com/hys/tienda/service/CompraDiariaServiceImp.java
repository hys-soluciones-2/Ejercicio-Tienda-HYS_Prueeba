package com.hys.tienda.service;

import com.hys.tienda.dto.RegistroCompraDTO;
import com.hys.tienda.entity.CompraDiaria;
import com.hys.tienda.repository.CompraDiariaRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hugo
 */
@Service
@Transactional
public class CompraDiariaServiceImp implements CompraDiariaService {

    @Autowired
    private CompraDiariaRepository CompraRepository;

    @Override
    public CompraDiaria registrarCompraDiaria(LocalDate fecha, BigDecimal montoConFactura, BigDecimal montoSinFactura) {

        // Validar que al menos uno no sea null o <= 0
//        if ((montoConFactura == null || montoConFactura.compareTo(BigDecimal.ZERO) <= 0)
//                && (montoSinFactura == null || montoSinFactura.compareTo(BigDecimal.ZERO) <= 0)) {
//            throw new RuntimeException("Debe proporcionar al menos un monto válido (con o sin factura)");
//        }

        // Convertir null a ZERO
        BigDecimal conFactura = (montoConFactura != null && montoConFactura.compareTo(BigDecimal.ZERO) > 0)
                ? montoConFactura : BigDecimal.ZERO;
        BigDecimal sinFactura = (montoSinFactura != null && montoSinFactura.compareTo(BigDecimal.ZERO) > 0)
                ? montoSinFactura : BigDecimal.ZERO;

        Optional<CompraDiaria> existente = CompraRepository.findByFechaCompra(fecha);
        CompraDiaria compra;

        if (existente.isPresent()) {
            compra = existente.get();
            compra.setCompraDelDiaConFactura(compra.getCompraDelDiaConFactura().add(conFactura));
            compra.setCompraDelDiaSinFactura(compra.getCompraDelDiaSinFactura().add(sinFactura));
        } else {
            compra = new CompraDiaria(fecha, conFactura, sinFactura); // Usa el constructor de 3 parámetros
        }

        return CompraRepository.save(compra);

    }

    /**
     * Obtiene el total acumulado histórico
     */
    @Override
    public BigDecimal obtenerTotalAcumuladoConFactura() {

        return CompraRepository.sumTotalAcumuladoConFactura();

    }

    @Override
    public BigDecimal obtenerTotalAcumuladoSinFactura() {

        return CompraRepository.sumTotalAcumuladoSinFactura();

    }

    @Override
    public List<CompraDiaria> obtenerCompraEnRango(LocalDate fechaInicio, LocalDate fechaFin) {

        return CompraRepository.findByFechaCompraBetween(fechaInicio, fechaFin);
    }

    @Override
    public List<CompraDiaria> listarTodasLasCompras() {
        return CompraRepository.findAll();
    }

    @Override
    public CompraDiaria actualizarCompraDiaria(LocalDate fecha, BigDecimal nuevoMonto, BigDecimal nuevoMontoSin) {
        CompraDiaria compra = CompraRepository.findByFechaCompra(fecha)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada para actualizar"));
        // Solo validar y actualizar si el monto NO es null
        if (nuevoMonto != null) {
            if (nuevoMonto.compareTo(BigDecimal.ZERO) < 0) {
                throw new RuntimeException("El monto con factura no puede ser negativo");
            }
            compra.setCompraDelDiaConFactura(nuevoMonto);
        }

        if (nuevoMontoSin != null) {
            if (nuevoMontoSin.compareTo(BigDecimal.ZERO) < 0) {
                throw new RuntimeException("El monto sin factura no puede ser negativo");
            }
            compra.setCompraDelDiaSinFactura(nuevoMontoSin);
        }

        return CompraRepository.save(compra);
    }

    @Override
    public void eliminarCompraPorFecha(LocalDate fecha) {
        CompraDiaria compra = CompraRepository.findByFechaCompra(fecha)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada para eliminar"));
        CompraRepository.delete(compra);
    }

    @Override
    public CompraDiaria obtenerCompraSinFacturaPorFecha(LocalDate fecha) {
        return CompraRepository.findByFechaCompra(fecha)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada para la fecha: " + fecha));
    }

    @Override
    public CompraDiaria obtenerCompraConFacturaPorFecha(LocalDate fecha) {
        return CompraRepository.findByFechaCompra(fecha)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada para la fecha: " + fecha));
    }

    @Override
    public CompraDiaria obtenerCompraPorFecha(LocalDate fecha) {
        return CompraRepository.findByFechaCompra(fecha)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada para la fecha: " + fecha));

    }

    @Override
    public BigDecimal obtenerTotalCompraConFacturaEnRango(LocalDate fechaInicio, LocalDate fechaFin) {

        return CompraRepository.sumTotalEnRangoConFactura(fechaInicio, fechaFin);

    }

    @Override
    public BigDecimal obtenerTotalCompraSinFacturaEnRango(LocalDate fechaInicio, LocalDate fechaFin) {

        return CompraRepository.sumTotalEnRangoSinFactura(fechaInicio, fechaFin);

    }
    //*******************************************************

//***********************************************************
}
