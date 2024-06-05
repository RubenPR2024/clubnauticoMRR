package com.app.clubnautico.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.clubnautico.dto.BarcoDTO;
import com.app.clubnautico.models.BarcoModel;
import com.app.clubnautico.models.SalidaModel;
import com.app.clubnautico.models.UserModel;
import com.app.clubnautico.repositories.BarcoRepository;
import com.app.clubnautico.repositories.UserRepository;

@Service
public class BarcoService {
	//Cuando convertimos a entidad se interactua con la base de datos ya que las entidades representan la estructura de datos almacenada en la base de datos
	//y se utilizan para operaciones CRUD
	
	//Por otro lado, la conversion a DTO es para generar respuestas o enviar informacion a través de la interfaz de la aplicación
	
	
    @Autowired
    private BarcoRepository barcoRepository; //Inyección de instancia del repositorio de BarcoRepository para interactuar con la base de datos.
    
    @Autowired
    private UserRepository userRepository;
    
    private final ModelMapper modelMapper = new ModelMapper();

    public List<BarcoDTO> getAllBarcos() {
    	//Con findAll obtiene la lista de BarcoModel.
        List<BarcoModel> barcos = barcoRepository.findAll();
        //Convierte cada modelo a DTO y devuelve la lista resultante
        return barcos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public BarcoDTO saveBarco(BarcoDTO barcoDTO) {
    	/*//Convierte el DTO a model para interactuar con la base de datos
        BarcoModel barco = convertToEntity(barcoDTO);
        //Guarda el modelo en la base de datos
        BarcoModel savedBarco = barcoRepository.save(barco);
        //Se reconvierte a DTO para generar la respuesta del servidor
        return convertToDTO(savedBarco);*/
    	BarcoModel barco = modelMapper.map(barcoDTO, BarcoModel.class);
        Optional<UserModel> propietarioOptional = userRepository.findById(barcoDTO.getPropietarioId());
        if (propietarioOptional.isPresent()) {
            barco.setUsuario(propietarioOptional.get());
        } else {
            throw new RuntimeException("Propietario no encontrado");
        }        
        BarcoModel savedBarco = barcoRepository.save(barco);
        return modelMapper.map(savedBarco, BarcoDTO.class);

    }

    public Optional<BarcoDTO> getBarcoById(Integer id) {
    	//Busca un varco por ID en la base de datos
        Optional<BarcoModel> barcoOptional = barcoRepository.findById(id);
        //Si el barco existe, convertimos a DTO para generar la respuesta, en caso contrario, devuelve null.
        return barcoOptional.map(this::convertToDTO);
    }

    public BarcoDTO updateBarco(BarcoDTO barcoDTO, Integer id) {
//    	//Busca el barco por ID, si no lo encuentra, se lanza una excepcion
//        BarcoModel existingBarco = barcoRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Barco not found with id " + id));
//        //Copiamos las propiedades del DTO al modelo
//        BeanUtils.copyProperties(barcoDTO, existingBarco);
//        //Guardamos el modelo actualizado en la base de datos
//        BarcoModel updatedBarco = barcoRepository.save(existingBarco);
//        //Convertimos la respuesta a DTO.
//        return convertToDTO(updatedBarco);
    	 BarcoModel existingBarco = barcoRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Barco not found with id " + id));
         
         Optional<UserModel> propietarioOptional = userRepository.findById(barcoDTO.getPropietarioId());
         if (propietarioOptional.isPresent()) {
             existingBarco.setUsuario(propietarioOptional.get());
         } else {
             throw new RuntimeException("Propietario no encontrado");
         }

         // Copiamos las propiedades que no son colecciones
         existingBarco.setMatricula(barcoDTO.getMatricula());
         existingBarco.setNombre(barcoDTO.getNombre());
         existingBarco.setnAmarre(barcoDTO.getnAmarre());
         existingBarco.setCuota(barcoDTO.getCuota());

         // Actualizamos la colección de salidas
         existingBarco.getSalida().clear();
         List<SalidaModel> salidas = barcoDTO.getSalida().stream()
                 .map(dto -> modelMapper.map(dto, SalidaModel.class))
                 .collect(Collectors.toList());
         existingBarco.getSalida().addAll(salidas);

         BarcoModel updatedBarco = barcoRepository.save(existingBarco);
         return modelMapper.map(updatedBarco, BarcoDTO.class);
    }

    public void deleteBarco(Integer id) {
    	//Eliminamos el barco de la base de datos.
        barcoRepository.deleteById(id);
    }

    //CON MODEL MAPPER ESTOS MÉTODOS NO HARÍAN FALTA
    private BarcoDTO convertToDTO(BarcoModel barco) {
    	//Creamos una instancia de BarcoDTO
        BarcoDTO barcoDTO = new BarcoDTO();
        //Copiamos las propiedades del modelo al DTO
        BeanUtils.copyProperties(barco, barcoDTO);
        //Devuelve el BarcoDTO resultante
        return barcoDTO;
    }
}
