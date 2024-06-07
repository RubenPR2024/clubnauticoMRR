package com.app.clubnautico.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.app.clubnautico.dto.BarcoDTO;
import com.app.clubnautico.dto.SalidaDTO;
import com.app.clubnautico.dto.UsuarioDTOconListas;
import com.app.clubnautico.models.BarcoModel;
import com.app.clubnautico.models.SalidaModel;
import com.app.clubnautico.models.UserModel;

@Configuration
public class ApplicationConfig {
	@Bean
    public ModelMapper modelMapper() {
	       ModelMapper modelMapper = new ModelMapper();

	       	modelMapper.createTypeMap(BarcoModel.class, BarcoDTO.class)
           		.addMapping(src -> src.getUsuario().getId(), BarcoDTO::setNumSocio);

	        modelMapper.createTypeMap(SalidaModel.class, SalidaDTO.class)
	            .addMapping(src -> src.getUsuario().getId(), SalidaDTO::setNumPatron);

	        modelMapper.createTypeMap(UserModel.class, UsuarioDTOconListas.class)
            .addMapping(UserModel::getBarco, UsuarioDTOconListas::setBarcos)
            .addMapping(UserModel::getSalida, UsuarioDTOconListas::setSalidas);

	        return modelMapper;
	    }
    }
