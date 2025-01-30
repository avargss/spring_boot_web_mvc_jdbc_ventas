package org.iesbelen.dao;

import java.util.List;
import java.util.Optional;

import org.iesbelen.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

//Anotación lombok para logging (traza) de la aplicación
@Slf4j
//Un Repository es un componente y a su vez un estereotipo de Spring 
//que forma parte de la ‘capa de persistencia’.
@Repository
public class ClienteDAOImpl implements ClienteDAO {

    //Plantilla jdbc inyectada automáticamente por el framework Spring, gracias a la anotación @Autowired.
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Inserta en base de datos el nuevo Cliente, actualizando el id en el bean Cliente.
     */
    @Override
    public synchronized void create(Cliente cliente) {

        jdbcTemplate.update("INSERT INTO cliente (nombre, apellido1, apellido2, ciudad, categoría) VALUES (?, ?, ?, ?, ?)", cliente.getNombre(), cliente.getApellido1(), cliente.getApellido2(), cliente.getCiudad(), cliente.getCategoria());
    }

    /**
     * Devuelve lista con todos los Clientes.
     */
    @Override
    public List<Cliente> getAll() {

        List<Cliente> listCli = jdbcTemplate.query(
                "SELECT * FROM cliente",
                (rs, rowNum) -> new Cliente(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getString("ciudad"),
                        rs.getInt("categoría")
                )
        );

        log.info("Devueltos {} registros.", listCli.size());

        return listCli;
    }

    /**
     * Devuelve Optional de Cliente con el ID dado.
     */
    @Override
    public Optional<Cliente> find(long id) {

        Cliente cli = jdbcTemplate
                .queryForObject("SELECT * FROM cliente WHERE id = ?"
                        , (rs, rowNum) -> new Cliente(rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("apellido1"),
                                rs.getString("apellido2"),
                                rs.getString("ciudad"),
                                rs.getInt("categoría"))
                        , id
                );

        if (cli != null) {
            return Optional.of(cli);
        } else {
            log.info("Cliente no encontrado.");
            return Optional.empty();
        }
    }

    /**
     * Actualiza Cliente con campos del bean Cliente según ID del mismo.
     */
    @Override
    public void update(Cliente cliente) {

        int rows = jdbcTemplate.update("""
                        UPDATE cliente SET 
                        				nombre = ?, 
                        				apellido1 = ?, 
                        				apellido2 = ?,
                        				ciudad = ?,
                        				categoría = ?  
                        		WHERE id = ?
                        """, cliente.getNombre()
                , cliente.getApellido1()
                , cliente.getApellido2()
                , cliente.getCiudad()
                , cliente.getCategoria()
                , cliente.getId());

        log.info("Update de Cliente con {} registros actualizados.", rows);
    }

    /**
     * Borra Cliente con ID proporcionado.
     */
    @Override
    public void delete(long id) {

        int rows = jdbcTemplate.update("DELETE FROM cliente WHERE id = ?", id);

        log.info("Delete de Cliente con {} registros eliminados.", rows);
    }
}
