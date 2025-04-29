package es.petclinic.models;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

public class EnumConverter implements Converter {
    
    /**
     * 
     * Convierte un valor dado al tipo enumerado especificado.
     *
     * @param <T> Tipo genérico que representa la enumeración a la que se convertirá el valor.
     * @param type Clase del tipo enumerado destino.
     * @param value Objeto que se intentará convertir al tipo enumerado especificado.
     * @return El valor convertido al tipo enumerado especificado, o null si el valor proporcionado es null.
     * @throws ConversionException Si la conversión no puede realizarse debido a que el valor no corresponde con ninguna constante del Enum.
    */
    @Override
    public <T> T convert(Class<T> type, Object value) {
        // Si el valor es nulo, devolver null
        if (value == null) {
            return null;
        }

        // Si el valor ya es del tipo Enum, lo devolvemos sin cambios
        if (type.isEnum() && type.isInstance(value)) {
            return type.cast(value);
        }

        // Si el valor es un String, intentamos convertirlo a Enum
        if (value instanceof String) {
            String enumValueName = (String) value;
            for (Object constant : type.getEnumConstants()) {
                if (((Enum<?>) constant).name().equalsIgnoreCase(enumValueName)) {
                    return type.cast(constant);
                }
            }
        }

        throw new ConversionException(
            String.format("Error al convertir '%s' a la enumeración %s", value, type.getName())
        );
    }
}
