/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hys.tienda.dto;

import com.hys.tienda.entity.Estado;
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
public class ProveedorResumenDTO {
    private Long idProveedor;
    private String nombre;
    private String contacto;
    private String telefono;
    private Estado estadoProveedor;
}
