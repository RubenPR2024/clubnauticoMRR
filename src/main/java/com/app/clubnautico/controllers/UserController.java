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
import com.app.clubnautico.models.UserModel;
import com.app.clubnautico.services.UserServices;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServices userService; // Inyecta la instancia de UserServices que contiene la lógica de negocio relacionada con los usuarios

    @GetMapping
    public ArrayList<UserModel> getUsers() { // Obtiene una lista de todos los usuarios llamando al método correspondiente en la clase de servicio de usuarios
        return this.userService.getUsers();
    }

    @PostMapping
    public UsuarioDTO saveUser(@RequestBody UsuarioDTO userDTO) {
        // Crea una instancia de UserModel y asigna los valores del DTO al modelo de usuario
        UserModel user = new UserModel();
        user.setDni(userDTO.getDni());
        user.setNombre(userDTO.getNombre());

        // Guarda el usuario en la base de datos a través de la capa de servicios y obtiene el usuario guardado
        UserModel savedUser = this.userService.saveUser(userDTO);

        // Convierte el UserModel guardado en un UsuarioDTO para enviarlo como respuesta
        UsuarioDTO savedUserDTO = new UsuarioDTO();
        savedUserDTO.setDni(savedUser.getDni());
        savedUserDTO.setNombre(savedUser.getNombre());

        // Retorna el DTO del usuario guardado
        return savedUserDTO;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UsuarioDTO> getUserById(@PathVariable Long id) {
        // Recibe el ID del usuario como parámetro de la URL y lo pasa a la capa de servicios para obtener el usuario correspondiente
        Optional<UsuarioDTO> userOptional = this.userService.getUserById(id);
        if (userOptional.isPresent()) {
            // Si se encuentra el usuario, devuelve un ResponseEntity con el DTO del usuario y el código de estado OK (200)
            return ResponseEntity.ok(userOptional.get());
        } else {
            // Si el usuario no se encuentra, devuelve un ResponseEntity con el código de estado Not Found (404)
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/{id}")
    public UserModel updateUserById(@RequestBody UserModel request, @PathVariable Long id) {
        // Recibe el cuerpo del DTO del usuario y el ID, y los pasa a la capa de servicios para actualizar el usuario correspondiente
        return this.userService.UpdateUserById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        // Recibe el ID del usuario como parámetro de la URL y lo pasa a la capa de servicios para eliminar el usuario
        boolean ok = this.userService.deleteUser(id);
        // Devuelve un mensaje indicando si se eliminó correctamente o si ocurrió un error
        if (ok) {
            return "User with id " + id + " deleted";
        } else {
            return "Error, we have a problem";
        }
    }
}
