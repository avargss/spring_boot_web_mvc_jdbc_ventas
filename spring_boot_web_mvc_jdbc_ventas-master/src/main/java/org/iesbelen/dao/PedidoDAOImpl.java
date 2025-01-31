package org.iesbelen.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesbelen.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class PedidoDAOImpl implements PedidoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int countPedidos() {
        int contarPedidos = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM pedido", Integer.class
        );

        log.info("Hay {} pedidos.", contarPedidos);
        return contarPedidos;
    }

    @Override
    public List<Pedido> getComercialById(int id) {

        List<Pedido> listPedidoByComercialId = jdbcTemplate.query(

                "SELECT * FROM pedido where id_comercial = ?",
                (rs, rowNum) -> new Pedido(rs.getInt("id"),
                        rs.getDouble("total"),
                        rs.getDate("fecha"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_comercial"))
                , id

        );

        log.info("Devueltos {} registros.", listPedidoByComercialId.size());

        return listPedidoByComercialId;

    }
}
