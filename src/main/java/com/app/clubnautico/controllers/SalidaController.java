package com.app.clubnautico.controllers;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
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

import com.app.clubnautico.dto.SalidaDTO;
import com.app.clubnautico.models.SalidaModel;
import com.app.clubnautico.models.UserModel;
import com.app.clubnautico.repositories.SalidaRepository;
import com.app.clubnautico.repositories.UserRepository;
import com.app.clubnautico.services.SalidaService;

@RestController
@RequestMapping("/salidas")
public class SalidaController {

    @Autowired
    private SalidaService salidaService; //Inyecta la instancia de SalidaService que contiene la lógica de negocio relacionada con las salidas

    @Autowired
    private SalidaRepository salidaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @GetMapping
    public List<SalidaDTO> getAllSalidas() { //Obtiene una lista de todas las salidas llamando al método correspondiente en la clase de servicio de salidas
        return salidaService.getAllSalidas();
    }

    @PostMapping
    public SalidaDTO saveSalida(@RequestBody SalidaDTO salidaDTO) {
    	SalidaModel salida = modelMapper.map(salidaDTO, SalidaModel.class);

        String fechaEntrada = salidaDTO.getFechaEntrada();
        String fechaSalida = salidaDTO.getFechaSalida();

        salida.setFechaEntrada(fechaEntrada);
        salida.setFechaSalida(fechaSalida);

        Optional<UserModel> numPatron = userRepository.findById(salidaDTO.getNumPatron());
        if(numPatron.isPresent()) {
            salida.setUsuario(numPatron.get());
        } else {
            throw new RuntimeException("Patrón no encontrado");
        }
        
        SalidaModel savedSalida = salidaRepository.save(salida);
        return modelMapper.map(savedSalida, SalidaDTO.class);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SalidaDTO> getSalidaById(@PathVariable Integer id) {
        //Recoge la ID del parámetro de la URL, la pasa a la capa de servicios de salidas para obtener la salida correspondiente
        Optional<SalidaDTO> salidaOptional = salidaService.getSalidaById(id);
        //En el caso de encontrar la salida, responde con un ResponseEntity con el DTO de la salida y el código de estado OK (200); en caso contrario, responde con un ResponseEntity con el código de estado Not Found (404)
        return salidaOptional.map(salidaDTO -> ResponseEntity.ok().body(salidaDTO))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SalidaDTO> updateSalida(@RequestBody SalidaDTO salidaDTO, @PathVariable Integer id) {
        //Recibe el cuerpo del DTO de la salida y una ID, los pasa a la capa de servicios para actualizar la salida correspondiente y devuelve un ResponseEntity con el DTO de la salida actualizada y el código de estado OK (200)
        SalidaDTO updatedSalida = salidaService.updateSalida(salidaDTO, id);
        return ResponseEntity.ok().body(updatedSalida);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteSalidaById(@PathVariable Integer id) {
        //Recoge la ID del parámetro de la URL y la pasa a la capa de servicios para eliminar la salida correspondiente
        salidaService.deleteSalida(id);
        //Devuelve un ResponseEntity sin contenido y con el código de estado No Content (204)
        return ResponseEntity.noContent().build();
    }
}
