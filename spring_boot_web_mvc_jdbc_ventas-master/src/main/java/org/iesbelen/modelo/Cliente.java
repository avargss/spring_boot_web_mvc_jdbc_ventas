package org.iesbelen.modelo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Por favor, introduzca nombre.")
    @Size(max=30, message = "Nombre como máximo de 30 caracteres.")
    private String nombre;

    @NotBlank(message = "Por favor, introduzca su primer apellido.")
    @Size(max=30, message = "Nombre como máximo de 30 caracteres.")
    private String apellido1;
    private String apellido2;

    @NotBlank(message = "Por favor, introduzca su ciudad.")
    @Size(min = 100, max=1000, message = "${cliente.error}")
    private String ciudad;

    @NotBlank
    private int categoria;

    public Cliente() {

    }

}