package modelMapper;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
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

	        // Configuración de UserModel a UsuarioDTOconListas
	        TypeMap<UserModel, UsuarioDTOconListas> userTypeMap = modelMapper.createTypeMap(UserModel.class, UsuarioDTOconListas.class);
	        userTypeMap.addMappings(mapper -> {
	            mapper.skip(UsuarioDTOconListas::setBarcos);
	            mapper.skip(UsuarioDTOconListas::setSalidas);
	        }).setPostConverter(context -> {
	            UserModel source = context.getSource();
	            UsuarioDTOconListas destination = context.getDestination();
	            destination.setBarcos(
	                source.getBarco().stream()
	                    .map(barco -> {
	                        BarcoDTO barcoDTO = modelMapper.map(barco, BarcoDTO.class);
	                        barcoDTO.setPropietarioId(source.getId());
	                        return barcoDTO;
	                    })
	                    .collect(Collectors.toList())
	            );
	            destination.setSalidas(
	                source.getSalida().stream()
	                    .map(salida -> {
	                        SalidaDTO salidaDTO = modelMapper.map(salida, SalidaDTO.class);
	                        salidaDTO.setUsuarioId(source.getId());
	                        return salidaDTO;
	                    })
	                    .collect(Collectors.toList())
	            );
	            return destination;
	        });

	        // Configuración de BarcoModel a BarcoDTO
	        modelMapper.createTypeMap(BarcoModel.class, BarcoDTO.class)
	                .addMapping(src -> src.getUsuario().getId(), BarcoDTO::setPropietarioId);

	        // Configuración de SalidaModel a SalidaDTO
	        modelMapper.createTypeMap(SalidaModel.class, SalidaDTO.class)
	                .addMapping(src -> src.getUsuario().getId(), SalidaDTO::setUsuarioId);

	        return modelMapper;
	    }
}
