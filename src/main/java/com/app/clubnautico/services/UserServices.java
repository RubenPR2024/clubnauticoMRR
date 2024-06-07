package com.app.clubnautico.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.clubnautico.dto.BarcoDTO;
import com.app.clubnautico.dto.SalidaDTO;
import com.app.clubnautico.dto.UsuarioDTO;
import com.app.clubnautico.dto.UsuarioDTOconListas;
import com.app.clubnautico.models.UserModel;
import com.app.clubnautico.repositories.UserRepository;

@Service
public class UserServices {

	@Autowired
	UserRepository userRepository; // Inyección de instancia del repositorio de UserRepository para interactuar con la base de datos

	@Autowired
    private ModelMapper modelMapper;
	
	public ArrayList<UserModel> getUsers() {
		// Retorna una lista de todos los usuarios almacenados en la base de datos
		return (ArrayList<UserModel>) userRepository.findAll();
	}

	public UsuarioDTO saveUser(UsuarioDTO userDTO) {
		//Convertimos el DTO a entidad para que interactue con la base de datos.
		UserModel user = modelMapper.map(userDTO, UserModel.class);
		//Guardamos la entidad en la base de datos.
		UserModel usuarioGuardado = userRepository.save(user);
		//Convertimos la entidad a DTO para devolver la respuesta
		UsuarioDTO usuarioGuardadoDTO = modelMapper.map(usuarioGuardado, UsuarioDTO.class);
		
		return usuarioGuardadoDTO;
	}

	public Optional<UsuarioDTOconListas> getUserById(Long id) {
//		// Busca un usuario por ID en la base de datos
//		Optional<UserModel> userOptional = userRepository.findById(id);
//		if (userOptional.isPresent()) {
//			// Si el usuario existe, convierte la entidad a DTO usando modelMapper
//			UsuarioDTOconListas userDTO = modelMapper.map(userOptional.get(), UsuarioDTOconListas.class);
//			// Devuelve un Optional que contiene el DTO de usuario
//			return Optional.of(userDTO);
//		} else {
//			// Si el usuario no existe, devuelve un Optional vacío
//			return Optional.empty();
//		}
		 Optional<UserModel> userOptional = userRepository.findById(id);
	        if (userOptional.isPresent()) {
	            UserModel userModel = userOptional.get();
	            UsuarioDTOconListas userDTO = modelMapper.map(userModel, UsuarioDTOconListas.class);

	            // Mapear las listas de barcos y salidas manualmente si no se hace automáticamente
	            userDTO.setBarcos(userModel.getBarco().stream()
	                    .map(barco -> modelMapper.map(barco, BarcoDTO.class))
	                    .collect(Collectors.toList()));
	            userDTO.setSalidas(userModel.getSalida().stream()
	                    .map(salida -> modelMapper.map(salida, SalidaDTO.class))
	                    .collect(Collectors.toList()));

	            return Optional.of(userDTO);
	        } else {
	            return Optional.empty();
	        }
	}

	public Optional<UsuarioDTO> UpdateUserById(UsuarioDTO userDTO, Long id) {
		// Buscamos el usuario por ID
		Optional<UserModel> userOptional = userRepository.findById(id);
		// Comprobamos si el usuario existe
		if (userOptional.isPresent()) {
			//Si existe el usuario, actualizamos los campos con los valores que tiene el DTO.
			UserModel user = userOptional.get();
			modelMapper.map(userDTO, user);
			//Guardamos los cambios en la base de datos
			UserModel updateUser = userRepository.save(user);
			//Convertimos la entidad actualizada a DTO
			UsuarioDTO updateUserDTO = modelMapper.map(updateUser, UsuarioDTO.class);
			//Devolvemos el usuario actualizado en formato DTO
			return Optional.of(updateUserDTO);
		} else {
			return Optional.empty();
		}
	}

	public Boolean deleteUser(Long id) {
		try {
			//Verificamos si el usuario existe
			Optional<UserModel> userOptional = userRepository.findById(id);
			if (userOptional.isPresent()) {
				userRepository.deleteById(id);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// Retorna false si ocurre un error al eliminar el usuario
			return false;
		}
	}
}
