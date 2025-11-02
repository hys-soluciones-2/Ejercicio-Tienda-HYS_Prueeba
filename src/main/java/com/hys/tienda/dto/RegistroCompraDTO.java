
package com.hys.tienda.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Hugo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroCompraDTO {
  
   private LocalDate fecha;
    private BigDecimal montoConFactura;   // puede ser null
    private BigDecimal montoSinFactura;   // puede ser null

    // getters y setters
    

  

  
    

}
