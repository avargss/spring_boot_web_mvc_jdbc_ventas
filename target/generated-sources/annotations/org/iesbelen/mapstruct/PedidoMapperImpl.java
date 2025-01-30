package org.iesbelen.mapstruct;

import java.util.Date;
import javax.annotation.processing.Generated;
import org.iesbelen.dto.PedidoDTO;
import org.iesbelen.modelo.Pedido;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-27T14:23:21+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class PedidoMapperImpl implements PedidoMapper {

    @Override
    public PedidoDTO pedidoApedidoDTO(Pedido pedido, String nombre_cliente) {
        if ( pedido == null && nombre_cliente == null ) {
            return null;
        }

        int id = 0;
        double total = 0.0d;
        Date fecha = null;
        int id_cliente = 0;
        int id_comercial = 0;
        if ( pedido != null ) {
            id = pedido.getId();
            total = pedido.getTotal();
            fecha = pedido.getFecha();
            id_cliente = pedido.getId_cliente();
            id_comercial = pedido.getId_comercial();
        }
        String nombre_cliente1 = null;
        nombre_cliente1 = nombre_cliente;

        PedidoDTO pedidoDTO = new PedidoDTO( id, total, fecha, id_cliente, id_comercial, nombre_cliente1 );

        return pedidoDTO;
    }

    @Override
    public Pedido pedidoDTOaPedido(Pedido pedido) {
        if ( pedido == null ) {
            return null;
        }

        int id = 0;
        double total = 0.0d;
        Date fecha = null;
        int id_cliente = 0;
        int id_comercial = 0;

        id = pedido.getId();
        total = pedido.getTotal();
        fecha = pedido.getFecha();
        id_cliente = pedido.getId_cliente();
        id_comercial = pedido.getId_comercial();

        Pedido pedido1 = new Pedido( id, total, fecha, id_cliente, id_comercial );

        return pedido1;
    }
}
