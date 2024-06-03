package com.app.clubnautico.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.clubnautico.dto.SalidaDTO;
import com.app.clubnautico.models.SalidaModel;
import com.app.clubnautico.repositories.SalidaRepository;

@Service
public class SalidaService {

    @Autowired
    private SalidaRepository salidaRepository; // Inyección de instancia del repositorio de SalidaRepository para interactuar con la base de datos

    public List<SalidaDTO> getAllSalidas() {
        // Obtiene una lista de todas las salidas almacenadas en la base de datos
        List<SalidaModel> salidas = salidaRepository.findAll();
        // Convierte cada modelo de salida a DTO y devuelve la lista resultante
        return salidas.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public SalidaDTO saveSalida(SalidaDTO salidaDTO) {
        // Convierte el DTO de salida a un modelo de salida para interactuar con la base de datos
        SalidaModel salida = convertToEntity(salidaDTO);
        // Guarda el modelo de salida en la base de datos
        SalidaModel savedSalida = salidaRepository.save(salida);
        // Convierte el modelo de salida guardado a DTO para generar la respuesta
        return convertToDTO(savedSalida);
    }

    public Optional<SalidaDTO> getSalidaById(Integer id) {
        // Busca una salida por ID en la base de datos
        Optional<SalidaModel> salidaOptional = salidaRepository.findById(id);
        // Si la salida existe, la convierte a DTO para generar la respuesta, en caso contrario, devuelve null
        return salidaOptional.map(this::convertToDTO);
    }

    public SalidaDTO updateSalida(SalidaDTO salidaDTO, Integer id) {
        // Busca la salida por ID, si no la encuentra, se lanza una excepción
        SalidaModel existingSalida = salidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salida not found with id " + id));
        // Copia las propiedades del DTO de salida al modelo de salida
        BeanUtils.copyProperties(salidaDTO, existingSalida);
        // Guarda el modelo de salida actualizado en la base de datos
        SalidaModel updatedSalida = salidaRepository.save(existingSalida);
        // Convierte la salida actualizada a DTO
        return convertToDTO(updatedSalida);
    }

    public void deleteSalida(Integer id) {
        // Elimina la salida de la base de datos
        salidaRepository.deleteById(id);
    }

    // Métodos auxiliares para la conversión entre SalidaDTO y SalidaModel
    private SalidaDTO convertToDTO(SalidaModel salida) {
        // Crea una instancia de SalidaDTO y copia las propiedades del modelo de salida
        SalidaDTO salidaDTO = new SalidaDTO();
        BeanUtils.copyProperties(salida, salidaDTO);
        // Retorna el DTO de salida resultante
        return salidaDTO;
    }

    private SalidaModel convertToEntity(SalidaDTO salidaDTO) {
        // Crea una instancia de SalidaModel y copia las propiedades del DTO de salida
        SalidaModel salida = new SalidaModel();
        BeanUtils.copyProperties(salidaDTO, salida);
        // Retorna el modelo de salida resultante
        return salida;
    }
}
