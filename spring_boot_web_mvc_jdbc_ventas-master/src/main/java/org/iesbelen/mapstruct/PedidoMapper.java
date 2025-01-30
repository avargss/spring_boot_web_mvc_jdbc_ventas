package org.iesbelen.mapstruct;

import org.iesbelen.dto.PedidoDTO;
import org.iesbelen.modelo.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    @Mapping(target = "nombre_cliente", source = "nombre_cliente")
    public PedidoDTO pedidoApedidoDTO(Pedido pedido, String nombre_cliente);

    public Pedido pedidoDTOaPedido(Pedido pedido);
}
