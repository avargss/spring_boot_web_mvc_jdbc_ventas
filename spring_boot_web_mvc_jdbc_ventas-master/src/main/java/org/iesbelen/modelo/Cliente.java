package org.iesbelen.modelo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//La anotación @Data de lombok proporcionará el código de:
//getters/setters, toString, equals y hashCode
//propio de los objetos POJOS o tipo Beans

@Data
@Getter
@Setter

//Para generar un constructor con lombok con todos los args
@AllArgsConstructor
public class Cliente {

    private long id;

    @NotBlank(message = "{error.nombre}")
    @Size(max =30, message = "{error.nombre.size.max}")
    private String nombre;

    @NotBlank(message = "{error.apellido}")
    @Size(max =30, message = "{error.apellido.size.max}")
    private String apellido1;
    private String apellido2;

    @NotBlank(message = "{error.ciudad}")
    @Size(max =50, message = "{error.ciudad.size.max}")
    private String ciudad;

    @Min(value = 100, message = "{error.min.categoria}")
    @Max(value = 1000, message = "{error.max.categoria}")
    private int categoria;

    public Cliente() {

    }
}