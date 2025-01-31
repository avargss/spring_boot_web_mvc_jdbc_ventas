package org.iesbelen.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
public class Pedido {

    private int id;
    private double total;
    private Date fecha;
    private int id_cliente;
    private int id_comercial;

}
