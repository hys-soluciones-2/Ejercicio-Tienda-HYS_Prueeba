
package com.hys.tienda.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Hugo
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="productos")
public class Producto implements Serializable{
    
       private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_producto")
    private Long idProducto;
    
   @Column(name="nombre_producto", nullable=false, length=100)
    private String nombreProducto;
    
   @Column(name="descripcion")
    private String descripcion;
   
   
    @Column(name = "estado_producto", nullable=false)
    private Estado estadoProducto;

     @Column(name= "precio")
    private Double precio;

    // ✅ Relación inversa muchos a muchos
    @ManyToMany(mappedBy = "productos")
    // @JsonBackReference
    @JsonIgnore // o @JsonBackReference si usas pares
    private List<Proveedor> proveedores = new ArrayList<>();
   
    
}
