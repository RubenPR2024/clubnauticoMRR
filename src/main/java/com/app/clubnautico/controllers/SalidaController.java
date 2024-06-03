package com.app.clubnautico.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.clubnautico.dto.SalidaDTO;
import com.app.clubnautico.services.SalidaService;

@RestController
@RequestMapping("/salidas")
public class SalidaController {

    @Autowired
    private SalidaService salidaService; //Inyecta la instancia de SalidaService que contiene la lógica de negocio relacionada con las salidas

    @GetMapping
    public List<SalidaDTO> getAllSalidas() { //Obtiene una lista de todas las salidas llamando al método correspondiente en la clase de servicio de salidas
        return salidaService.getAllSalidas();
    }

    @PostMapping
    public ResponseEntity<SalidaDTO> saveSalida(@RequestBody SalidaDTO salidaDTO) {
        //Recibe un DTO del cuerpo de la solicitud y lo pasa a la clase SalidaService para guardarlo en la base de datos y devuelve el DTO de la salida guardada con el código 201 (CREATED)
        SalidaDTO savedSalida = salidaService.saveSalida(salidaDTO);
        return new ResponseEntity<>(savedSalida, HttpStatus.CREATED);
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
