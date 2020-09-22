package Localizacion;

import javax.persistence.*;

@Converter
public class CiudadConverter implements AttributeConverter<Ciudad, String> {
    @Override
    public String convertToDatabaseColumn(Ciudad ciudad) {
        return ciudad.getIdAPI();
    }

    @Override
    public Ciudad convertToEntityAttribute(String id) {
        return Localizacion.servicio().obtenerCiudad(id);
    }
}
