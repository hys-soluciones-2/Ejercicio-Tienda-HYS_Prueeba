
package com.hys.tienda.service;

import com.hys.tienda.entity.CompraDiaria;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hugo
 */
public interface CompraDiariaService {
    
    public CompraDiaria registrarCompraDiaria(LocalDate fecha, BigDecimal montoConFactura, BigDecimal montoSinFactura);

    @Transactional(readOnly = true)
    public List<CompraDiaria> obtenerCompraEnRango(LocalDate fechaInicio, LocalDate fechaFin);

    @Transactional(readOnly = true)
    public BigDecimal obtenerTotalAcumuladoConFactura();
    
    @Transactional(readOnly = true)
    public BigDecimal obtenerTotalAcumuladoSinFactura();
    
      // En ComprasDiariaService.java

    @Transactional(readOnly = true)
    public List<CompraDiaria> listarTodasLasCompras(); 
    
    
     @Transactional
    public CompraDiaria actualizarCompraDiaria(LocalDate fecha, BigDecimal nuevoMonto, BigDecimal nuevoMontosin);
    
    
    /**
     * Elimina una Compra diaria por fecha
     */
    @Transactional
    public void eliminarCompraPorFecha(LocalDate fecha);
    
     @Transactional(readOnly = true)
     public CompraDiaria obtenerCompraSinFacturaPorFecha(LocalDate fecha);
     
       @Transactional(readOnly = true)
     public CompraDiaria obtenerCompraConFacturaPorFecha(LocalDate fecha);
     
    
     @Transactional(readOnly = true)
     public CompraDiaria obtenerCompraPorFecha(LocalDate fecha);
    
    //********************************************************
    @Transactional(readOnly = true)
    public BigDecimal obtenerTotalCompraConFacturaEnRango(LocalDate fechaInicio, LocalDate fechaFin);

    @Transactional(readOnly = true)
    public BigDecimal obtenerTotalCompraSinFacturaEnRango(LocalDate fechaInicio, LocalDate fechaFin);
   
   
    
    
    
 
  
   
    
    
    
    
    
}
