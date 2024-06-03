package com.app.clubnautico.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.clubnautico.dto.UsuarioDTO;
import com.app.clubnautico.models.UserModel;
import com.app.clubnautico.repositories.UserRepository;

@Service
public class UserServices {

	@Autowired
	UserRepository userRepository; // Inyección de instancia del repositorio de UserRepository para interactuar con la base de datos

	public ArrayList<UserModel> getUsers() {
		// Retorna una lista de todos los usuarios almacenados en la base de datos
		return (ArrayList<UserModel>) userRepository.findAll();
	}

	public UserModel saveUser(UsuarioDTO userDTO) {
		// Crea una instancia de UserModel y asigna los valores del DTO de usuario al modelo de usuario
		UserModel user = new UserModel();
		user.setDni(userDTO.getDni());
		user.setNombre(userDTO.getNombre());
		// Asigna otros campos
		// Guarda el usuario en la base de datos y lo devuelve
		return userRepository.save(user);
	}

	public Optional<UsuarioDTO> getUserById(Long id) {
		// Busca un usuario por ID en la base de datos
		Optional<UserModel> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			// Si el usuario existe, crea un DTO de usuario y asigna los valores del usuario al DTO
			UserModel user = userOptional.get();
			UsuarioDTO userDTO = new UsuarioDTO();
			//Aqui podemos definir lo que queremos que nos devuelva la aplicacion como respuesta, en este caso solo devuelve DNI y nombre aunque hayan mas datos.
			userDTO.setDni(user.getDni());
			userDTO.setNombre(user.getNombre());
			
			// Devuelve un Optional que contiene el DTO de usuario
			return Optional.of(userDTO);
		} else {
			// Si el usuario no existe, devuelve un Optional vacío
			return Optional.empty();
		}
	}

	public UserModel UpdateUserById(UserModel request, Long id) {
		// Busca el usuario por ID
		UserModel user = userRepository.findById(id).get();
		// Actualiza los campos del usuario con los valores del modelo de usuario recibido
		user.setDni(request.getDni());
		user.setDireccion(request.getDireccion());
		user.setEmail(request.getEmail());
		// Retorna el usuario actualizado
		return user;
	}

	public Boolean deleteUser(Long id) {
		try {
			// Elimina el usuario de la base de datos por ID
			userRepository.deleteById(id);
			// Retorna true si se elimina correctamente
			return true;
		} catch (Exception e) {
			// Retorna false si ocurre un error al eliminar el usuario
			return false;
		}
	}
}
