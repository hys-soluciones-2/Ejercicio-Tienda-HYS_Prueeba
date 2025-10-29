
package com.hys.tienda.controller;

import com.hys.tienda.entity.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hugo
 */
@RestController
@RequestMapping("/api/messages")
public class MessageController {
    
    
    private List<Message> mensajes = new ArrayList<>();

    public MessageController() {
       mensajes.add(new Message(1, "Suscribete en la Tecnologia avanza")); 
       mensajes.add(new Message(2, "Comparte en tus redes sociales")); 
    }
    
    @GetMapping
    public List<Message> ListarMensajes(){
        return mensajes;
    }
    
    @GetMapping("/{id}")
    public Message obtenerMensajePorId(@PathVariable int id){
        
        Optional<Message> message = mensajes.stream()
                .filter( m -> m.getId() == id)
                .findFirst();
        
        return message.orElse(null);
    }
    
    @PostMapping
    public Message crearMensaje(@RequestBody Message message){
        
        mensajes.add(message);
        
        return message;
    }
    @DeleteMapping("/{id}")
    public void eliminarMensaje(@PathVariable int id){
        
        mensajes.removeIf(m -> m.getId() == id);
    }
    
}
