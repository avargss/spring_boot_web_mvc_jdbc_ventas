package org.iesbelen.dao;

import org.iesbelen.modelo.Pedido;

import java.util.List;

public interface PedidoDAO {

    public int countPedidos();

    public List<Pedido> getComercialById(int id);
}
