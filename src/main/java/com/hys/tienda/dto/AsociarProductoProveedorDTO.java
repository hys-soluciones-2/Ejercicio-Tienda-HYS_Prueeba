/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hys.tienda.dto;

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
public class AsociarProductoProveedorDTO {
    
    private Long idProducto;
    private Long idProveedor;
}

