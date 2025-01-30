package org.iesbelen.dao;

import org.iesbelen.modelo.Pedido;

import java.util.List;

public interface PedidoDAO {

    public List<Pedido> getAll();

    public List<Pedido> getComercialById(int id);
}
