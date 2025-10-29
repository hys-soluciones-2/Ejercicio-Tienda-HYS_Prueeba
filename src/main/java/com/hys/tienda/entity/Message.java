
package com.hys.tienda.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Hugo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    
    private int id;
    private String content;
}
