
package com.hys.tienda.entity;

import jakarta.persistence.*;
import jakarta.servlet.annotation.HandlesTypes;
import java.io.Serializable;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NotFound;

/**
 *
 * @author Hugo
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "proveedores")
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Long idProveedor;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "contacto")
    private String contacto;

    @Column(name = "telefono")
    private String telefono;

    //@Enumerated(EnumType.STRING)
    @Column(name = "estado_proveedor", nullable=false)
    private String estadoProveedor;
    
     // ✅ Relación muchos a muchos con Producto
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
        name = "proveedor_producto", // nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor"), // FK de esta entidad
        inverseJoinColumns = @JoinColumn(name = "id_producto", referencedColumnName = "id_producto") // FK de la otra entidad
    )
    private List<Producto> productos = new ArrayList<>();
    
    
}
