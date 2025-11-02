
package com.hys.tienda.dto;

import java.math.BigDecimal;

/**
 *
 * @author Hugo
 */
public class ActualizarCompraDTO {
     private BigDecimal montoConFactura;
     private BigDecimal montoSinFactura;

    // Getter y setter
    public BigDecimal getMontoConFactura() {
        return montoConFactura;
    }

    public void setMontoConFactura(BigDecimal montoConFactura) {
        this.montoConFactura = montoConFactura;
    }
    // Getter y setter
    public BigDecimal getMontoSinFactura() {
        return montoSinFactura;
    }

    public void setMontoSinFactura(BigDecimal montoSinFactura) {
        this.montoSinFactura = montoSinFactura;
    }
}
