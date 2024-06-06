package modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.app.clubnautico.dto.BarcoDTO;
import com.app.clubnautico.models.BarcoModel;

@Configuration
public class ApplicationConfig {
	@Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(BarcoModel.class, BarcoDTO.class)
        .addMapping(src -> ((Object) src.getUsuario()).getId(), BarcoDTO::setNumSocio);

        return modelMapper;
    }
}
