
package com.hys.tienda.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Hugo
 */
//@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidoDTO {
    
    private Long idProducto;
    private Integer cantidad;
    private BigDecimal costoUnitario;

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(BigDecimal costoUnitar) {
        this.costoUnitario = costoUnitar;
    }
    
    
}
