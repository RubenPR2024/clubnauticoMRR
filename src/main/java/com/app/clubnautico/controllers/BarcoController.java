package com.app.clubnautico.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.clubnautico.dto.BarcoDTO;
import com.app.clubnautico.services.BarcoService;

@RestController
@RequestMapping("/barcos")
public class BarcoController {


    @Autowired
    private BarcoService barcoService; //Inyecta la instancia de BarcoService que contiene la lógica de negocio relacionada con los barcos

    @GetMapping
    public List<BarcoDTO> getAllBarcos() { //Llamamos a la clase servicio de barcos para obtener una lista de todos los barcos
        return barcoService.getAllBarcos();
    }

    @PostMapping
    public BarcoDTO saveBarco(@RequestBody BarcoDTO barcoDTO) {
        return barcoService.saveBarco(barcoDTO);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BarcoDTO> getBarcoById(@PathVariable Integer id) {
    	//Recoge la ID del parametro, lo pasa a la capa de servicios de barco para obtener el barco correspondiente.
        Optional<BarcoDTO> barcoOptional = barcoService.getBarcoById(id);
        //En el caso de encontrar el barco, responde con un OK, en caso contrario, con Not found.
        return barcoOptional.map(barcoDTO -> ResponseEntity.ok().body(barcoDTO))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BarcoDTO> updateBarco(@RequestBody BarcoDTO barcoDTO, @PathVariable Integer id) {
    	//Toma el cuerpo del DTO y un idea, lo pasa a la capa de servicio para actualizar el barco correspondiente
    	//devolviendo un Ok si se ha modificado
        BarcoDTO updatedBarco = barcoService.updateBarco(barcoDTO, id);
        return ResponseEntity.ok().body(updatedBarco);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteBarcoById(@PathVariable Integer id) {
    	//Recoge el ID del parametro y lo pasa a la capa de servicio para eliminar el barco.
        barcoService.deleteBarco(id);
        return ResponseEntity.noContent().build();
    }
}
