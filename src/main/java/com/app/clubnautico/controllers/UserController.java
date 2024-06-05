package com.app.clubnautico.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.clubnautico.dto.UsuarioDTO;
import com.app.clubnautico.dto.UsuarioDTOconListas;
import com.app.clubnautico.models.UserModel;
import com.app.clubnautico.services.UserServices;

@RestController
@RequestMapping("/users")
public class UserController {

	/*APUNTES:
	 * Optional<UsuarioDTO>
Descripción: Optional<UsuarioDTO> es un contenedor que puede contener un objeto UsuarioDTO o estar vacío.
Propósito: Se usa para devolver resultados de métodos en los que un UsuarioDTO podría no estar presente. Esto evita el retorno de null y proporciona una manera más segura de manejar la ausencia de valor.
	 * Optional<UserModel>
Descripción: Optional<UserModel> es un contenedor que puede contener un objeto UserModel o estar vacío.
Propósito: Se usa para encapsular el resultado de consultas a la base de datos donde un UserModel podría no existir. Esto proporciona una manera más clara y segura de manejar la ausencia de resultados en lugar de devolver null.
	 * */
	
    @Autowired
    private UserServices userService; // Inyecta la instancia de UserServices que contiene la lógica de negocio relacionada con los usuarios
    
    @GetMapping
    public ArrayList<UserModel> getUsers() { // Obtiene una lista de todos los usuarios llamando al método correspondiente en la clase de servicio de usuarios
        return userService.getUsers();
    }

    @PostMapping
    public UsuarioDTO saveUser(@RequestBody UsuarioDTO userDTO) {
        //Guardamos el usuario y devuelve el DTO del usuario guardado (ya se encarga la capa de servicio de hacerlo todo)
    	return userService.saveUser(userDTO);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UsuarioDTOconListas> getUserById(@PathVariable Long id) {
        // Recibe el ID del usuario como parámetro y lo pasa a la capa de servicios para obtener el usuario correspondiente
        Optional<UsuarioDTOconListas> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            // Si se encuentra el usuario, devuelve un ResponseEntity con el DTO del usuario y el código de estado OK (200)
            return ResponseEntity.ok(userOptional.get());
        } else {
            // Si el usuario no se encuentra, devuelve un ResponseEntity con el código de estado Not Found (404)
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UsuarioDTO> updateUserById(@RequestBody UsuarioDTO userDTO, @PathVariable Long id) {
        Optional<UsuarioDTO> updateUserOptional = userService.UpdateUserById(userDTO, id);
        if(updateUserOptional.isPresent()) {
        	return ResponseEntity.ok(updateUserOptional.get());
        } else {
        	return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        // Recibe el ID del usuario como parámetroy lo pasa a la capa de servicios para eliminar el usuario
        boolean ok = userService.deleteUser(id);
        // Devuelve un mensaje indicando si se eliminó correctamente o si ocurrió un error
        if (ok) {
            return ResponseEntity.ok("User with id " + id + " deleted");
        } else {
            return ResponseEntity.status(500).body("Error, we have a problem");
        }
    }
}
