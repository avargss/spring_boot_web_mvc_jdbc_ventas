package org.iesbelen.service;

import org.iesbelen.dao.ClienteDAO;
import org.iesbelen.dao.ComercialDAO;
import org.iesbelen.dao.PedidoDAO;
import org.iesbelen.dto.PedidoDTO;
import org.iesbelen.mapstruct.PedidoMapper;
import org.iesbelen.modelo.Cliente;
import org.iesbelen.modelo.Comercial;
import org.iesbelen.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoDAO pedidoDAO;

    @Autowired
    private ClienteDAO clienteDAO;

    @Autowired
    private PedidoMapper pedidoMapper;

    public int contarTodosLosPedidos() {
        return pedidoDAO.countPedidos();
    }

    public List<PedidoDTO> listPedidosDTO(int idComercial) {

        List<Cliente> clientes = clienteDAO.getAll();
        List<Pedido> pedidos = pedidoDAO.getComercialById(idComercial);
        pedidos.sort((a, b) -> b.getFecha().compareTo(a.getFecha()));

        List<PedidoDTO> pedidosDTO = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            int idC = pedido.getId_cliente();
            for (Cliente cliente : clientes) {
                if (cliente.getId() == idC) {
                    pedidosDTO.add(pedidoMapper.pedidoApedidoDTO(pedido, cliente.getNombre() + " " + cliente.getApellido1()
                            + " " + (cliente.getApellido2() != null ? cliente.getApellido2() : "")));
                }
            }
        }
        System.out.println("Pasa por aquí" + pedidosDTO);
        return pedidosDTO;
    }

    public double mediaPedidos(int total, int particular) {

        double media;

        System.out.println(particular);

        media = (double) total / particular;
        media = media * 100;
        return media;

    }
}